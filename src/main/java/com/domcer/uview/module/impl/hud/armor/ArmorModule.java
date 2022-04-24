// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiChat
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Pre
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  org.lwjgl.opengl.GL11
 */
package com.domcer.uview.module.impl.hud.armor;

import com.domcer.uview.module.AbstractModule;
import com.domcer.uview.module.IRenderer;
import com.domcer.uview.settings.UViewSettings;
import com.domcer.uview.settings.types.NumberSetting;
import com.domcer.uview.util.ChromaUtils;
import java.text.DecimalFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class ArmorModule
extends AbstractModule
implements IRenderer {
    public static final ArmorModule INSTANCE = new ArmorModule();
    private final DecimalFormat df = new DecimalFormat("0%");
    private final ItemStack[] example = new ItemStack[]{new ItemStack(Item.getItemById((int)309)), new ItemStack(Item.getItemById((int)304)), new ItemStack(Item.getItemById((int)307)), new ItemStack(Item.getItemById((int)310))};

    @Override
    public void onLoad() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Pre event) {
        if (event.type == RenderGameOverlayEvent.ElementType.TEXT) {
            this.renderLive(event.resolution);
        }
    }

    public void renderLive(ScaledResolution sr) {
        Minecraft mc = Minecraft.getMinecraft();
        if (this.isDisplable() && UViewSettings.armorHudEnabled.value().booleanValue()) {
            this.renderItems(mc.thePlayer.inventory.armorInventory, (float)((double)UViewSettings.armorHudX.value().floatValue() * sr.getScaledWidth_double()), (float)((double)UViewSettings.armorHudY.value().floatValue() * sr.getScaledHeight_double()));
        }
    }

    @Override
    public boolean isDisplable() {
        return super.isDisplable() || Minecraft.getMinecraft().currentScreen instanceof GuiChat;
    }

    private void renderItems(ItemStack[] items, float x, float y) {
        boolean vertical = UViewSettings.armorHudhorizontalAlignment.value();
        for (int i = 0; i < 4; ++i) {
            String text;
            ItemStack item = items[Math.abs(3 - i)];
            if (item == null) continue;
            Vector pos = this.getPositionForNthPiece(i, x, y, vertical);
            this.drawItemStack(item, (int)pos.getX(), (int)pos.getY());
            if (!UViewSettings.armorHudDurability.value().booleanValue()) continue;
            FontRenderer fontRenderer = Minecraft.getMinecraft().fontRendererObj;
            String string = text = UViewSettings.armorHudDurabilityPercentage.value() != false ? this.df.format((float)(item.getMaxDamage() - item.getItemDamage()) * 1.0f / (float)item.getMaxDamage()) : item.getMaxDamage() - item.getItemDamage() + "";
            if (vertical) {
                if (UViewSettings.armorHudDurabilityColor.isChroma().booleanValue()) {
                    ChromaUtils.drawChromaString(text, pos.getX() + 8.0f - (float)fontRenderer.getStringWidth(text) / 2.0f, pos.getY() + 15.0f, false, true);
                    continue;
                }
                fontRenderer.drawString(text, (int)pos.getX() + 8 - fontRenderer.getStringWidth(text) / 2, (int)(pos.getY() + 15.0f), UViewSettings.armorHudDurabilityColor.value().intValue());
                continue;
            }
            if (UViewSettings.armorHudDurabilityColor.isChroma().booleanValue()) {
                ChromaUtils.drawChromaString(text, pos.getX() + 8.0f - (float)fontRenderer.getStringWidth(text) / 2.0f, pos.getY() + 15.0f, false, true);
                continue;
            }
            fontRenderer.drawString(text, (int)pos.getX() + 8 - fontRenderer.getStringWidth(text) / 2, (int)(pos.getY() + 15.0f), UViewSettings.armorHudDurabilityColor.value().intValue());
        }
    }

    private Vector getPositionForNthPiece(int n, float x, float y, boolean vertical) {
        if (vertical) {
            x += (float)((15 + UViewSettings.armorHudSpacing.value().intValue()) * n) * this.getScale().value().floatValue();
        } else {
            y += (float)((15 + UViewSettings.armorHudSpacing.value().intValue() + (UViewSettings.armorHudDurability.value() != false ? 6 : 0)) * n) * this.getScale().value().floatValue();
        }
        return new Vector(x, y);
    }

    private void drawItemStack(ItemStack stack, int x, int y) {
        GL11.glPushMatrix();
        RenderHelper.enableGUIStandardItemLighting();
        RenderItem itemRender = Minecraft.getMinecraft().getRenderItem();
        GlStateManager.translate((float)x, (float)y, (float)0.0f);
        float prevZ = itemRender.zLevel;
        itemRender.zLevel = 200.0f;
        FontRenderer font = null;
        if (stack != null) {
            font = stack.getItem().getFontRenderer(stack);
        }
        if (font == null) {
            font = Minecraft.getMinecraft().fontRendererObj;
        }
        GlStateManager.scale((float)this.getScale().value().floatValue(), (float)this.getScale().value().floatValue(), (float)this.getScale().value().floatValue());
        itemRender.renderItemAndEffectIntoGUI(stack, 0, 0);
        itemRender.renderItemOverlayIntoGUI(font, stack, 0, 0, "");
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableLighting();
        GL11.glPopMatrix();
        itemRender.zLevel = prevZ;
    }

    @Override
    public NumberSetting getX() {
        return UViewSettings.armorHudX;
    }

    @Override
    public NumberSetting getY() {
        return UViewSettings.armorHudY;
    }

    @Override
    public NumberSetting getScale() {
        return UViewSettings.armorHudScale;
    }

    @Override
    public int getHeight() {
        if (UViewSettings.armorHudhorizontalAlignment.value().booleanValue()) {
            return (int)((float)(17 + (UViewSettings.armorHudDurability.value() != false ? 7 : 0)) * this.getScale().value().floatValue());
        }
        return (int)((float)(62 + UViewSettings.armorHudSpacing.value().intValue() * 3 + (UViewSettings.armorHudDurability.value() != false ? 7 : 0) * 4) * this.getScale().value().floatValue());
    }

    @Override
    public int getWidth() {
        if (UViewSettings.armorHudhorizontalAlignment.value().booleanValue()) {
            return (int)((float)(62 + UViewSettings.armorHudSpacing.value().intValue() * 3) * this.getScale().value().floatValue());
        }
        return (int)(17.0f * this.getScale().value().floatValue());
    }

    @Override
    public boolean isGuiEnabled() {
        return UViewSettings.armorHudEnabled.value();
    }

    @Override
    public void render(Minecraft minecraft, float x, float y) {
        this.renderItems(this.example, x, y);
    }

    private static class Vector {
        private float x;
        private float y;

        public float getX() {
            return this.x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return this.y;
        }

        public void setY(float y) {
            this.y = y;
        }

        public Vector(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }
}

