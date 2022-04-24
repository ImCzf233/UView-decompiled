// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Pre
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.domcer.uview.module.impl.hud.itemcounter;

import com.domcer.uview.module.AbstractModule;
import com.domcer.uview.module.IRenderer;
import com.domcer.uview.settings.UViewSettings;
import com.domcer.uview.settings.gui.GuiItemCounterSetting;
import com.domcer.uview.settings.types.ItemCounterSetting;
import com.domcer.uview.settings.types.NumberSetting;
import com.domcer.uview.util.ChromaUtils;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemCounter
extends AbstractModule
implements IRenderer {
    public static final ItemCounter INSTANCE = new ItemCounter();
    private final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void onLoad() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Pre event) {
        if (this.isDisplable() && UViewSettings.itemCounterEnabled.value().booleanValue() && event.type == RenderGameOverlayEvent.ElementType.TEXT) {
            this.renderLive(UViewSettings.itemCounterX.value().floatValue() * (float)event.resolution.getScaledWidth(), UViewSettings.itemCounterY.value().floatValue() * (float)event.resolution.getScaledHeight());
        }
    }

    @Override
    public boolean isDisplable() {
        return super.isDisplable() || Minecraft.getMinecraft().currentScreen instanceof GuiItemCounterSetting;
    }

    @Override
    public NumberSetting getX() {
        return UViewSettings.itemCounterX;
    }

    @Override
    public NumberSetting getY() {
        return UViewSettings.itemCounterY;
    }

    @Override
    public NumberSetting getScale() {
        return UViewSettings.itemCounterScale;
    }

    @Override
    public int getHeight() {
        if (UViewSettings.itemCounterhorizontalAlignment.value().booleanValue()) {
            return (int)(26.0f * this.getScale().value().floatValue());
        }
        return (int)(32.0f * this.getScale().value().floatValue());
    }

    @Override
    public int getWidth() {
        if (UViewSettings.itemCounterhorizontalAlignment.value().booleanValue()) {
            return (int)(32.0f * this.getScale().value().floatValue());
        }
        return (int)(26.0f * this.getScale().value().floatValue());
    }

    @Override
    public boolean isGuiEnabled() {
        return UViewSettings.itemCounterEnabled.value();
    }

    public void renderLive(float x, float y) {
        EntityPlayerSP thePlayer = Minecraft.getMinecraft().thePlayer;
        if (thePlayer == null) {
            return;
        }
        boolean horizontal = UViewSettings.itemCounterhorizontalAlignment.value();
        int i = 0;
        Iterator iterator = UViewSettings.itemCounterItems.value().iterator();
        while (iterator.hasNext()) {
            ItemCounterSetting.DisplayItem displayItem = (ItemCounterSetting.DisplayItem)iterator.next();
            Item item = Item.getByNameOrId((String)displayItem.itemName);
            String seedName = ((ResourceLocation)Item.itemRegistry.getNameForObject((Item) item)).toString();
            int c = 0;
            for (ItemStack is : thePlayer.inventory.mainInventory) {
                if (is == null || !((ResourceLocation)Item.itemRegistry.getNameForObject((Item) is.getItem())).toString().equalsIgnoreCase(seedName)) continue;
                c += is.stackSize;
            }
            if (c == 0 && displayItem.hideNone) continue;
            Vector vector = this.getPositionForNthPiece(i++, x, y, horizontal);
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)vector.getX(), (float)vector.getY(), (float)0.0f);
            GlStateManager.scale((float)this.getScale().value().floatValue(), (float)this.getScale().value().floatValue(), (float)this.getScale().value().floatValue());
            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
            renderItem.zLevel = 200.0f;
            RenderHelper.enableGUIStandardItemLighting();
            ItemStack itemStack = new ItemStack(item, 1);
            renderItem.renderItemAndEffectIntoGUI(itemStack, 0, 0);
            GlStateManager.popMatrix();
            String text = "x" + c;
            Gui.drawRect((int)((int)(vector.getX() + (horizontal ? (float)(16 - Minecraft.getMinecraft().fontRendererObj.getStringWidth(text)) / 2.0f : 16.0f) * UViewSettings.itemCounterScale.value().floatValue())), (int)((int)(vector.getY() + (float)(horizontal ? 16 : 7) * UViewSettings.itemCounterScale.value().floatValue())), (int)((int)(vector.getX() + (horizontal ? (float)(16 - Minecraft.getMinecraft().fontRendererObj.getStringWidth(text)) / 2.0f : 16.0f) * UViewSettings.itemCounterScale.value().floatValue() + (float)Minecraft.getMinecraft().fontRendererObj.getStringWidth(text) * this.getScale().value().floatValue())), (int)((int)(vector.getY() + (float)(horizontal ? 16 : 7) * UViewSettings.itemCounterScale.value().floatValue() + (float)Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT * this.getScale().value().floatValue())), (int)UViewSettings.itemCounterBackgroundColor.value());
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)(vector.getX() + (horizontal ? (float)(16 - Minecraft.getMinecraft().fontRendererObj.getStringWidth(text)) / 2.0f : 16.0f) * UViewSettings.itemCounterScale.value().floatValue()), (float)(vector.getY() + (float)(horizontal ? 16 : 7) * UViewSettings.itemCounterScale.value().floatValue()), (float)0.0f);
            GlStateManager.scale((float)this.getScale().value().floatValue(), (float)this.getScale().value().floatValue(), (float)this.getScale().value().floatValue());
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            if (UViewSettings.itemCounterColor.isChroma().booleanValue()) {
                ChromaUtils.drawChromaString(text, 0.0f, 0.0f, false, true);
            } else {
                this.mc.fontRendererObj.drawStringWithShadow(text, 0.0f, 0.0f, UViewSettings.itemCounterColor.value().intValue());
            }
            GlStateManager.popMatrix();
        }
    }

    @Override
    public void render(Minecraft minecraft, float x, float y) {
        EntityPlayerSP thePlayer = Minecraft.getMinecraft().thePlayer;
        if (thePlayer == null) {
            return;
        }
        boolean horizontal = UViewSettings.itemCounterhorizontalAlignment.value();
        int i = 0;
        Iterator iterator = UViewSettings.itemCounterItems.defaultValue().iterator();
        while (iterator.hasNext()) {
            ItemCounterSetting.DisplayItem displayItem = (ItemCounterSetting.DisplayItem)iterator.next();
            Item item = Item.getByNameOrId((String)displayItem.itemName);
            if (displayItem.hideNone) continue;
            int c = 0;
            Vector vector = this.getPositionForNthPiece(i++, x, y, horizontal);
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)vector.getX(), (float)vector.getY(), (float)0.0f);
            GlStateManager.scale((float)this.getScale().value().floatValue(), (float)this.getScale().value().floatValue(), (float)this.getScale().value().floatValue());
            RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
            renderItem.zLevel = 200.0f;
            RenderHelper.enableGUIStandardItemLighting();
            ItemStack itemStack = new ItemStack(item, 1);
            renderItem.renderItemAndEffectIntoGUI(itemStack, 0, 0);
            GlStateManager.popMatrix();
            String text = "x" + c;
            Gui.drawRect((int)((int)(vector.getX() + (horizontal ? (float)(16 - Minecraft.getMinecraft().fontRendererObj.getStringWidth(text)) / 2.0f : 16.0f) * UViewSettings.itemCounterScale.value().floatValue())), (int)((int)(vector.getY() + (float)(horizontal ? 16 : 7) * UViewSettings.itemCounterScale.value().floatValue())), (int)((int)(vector.getX() + (horizontal ? (float)(16 - Minecraft.getMinecraft().fontRendererObj.getStringWidth(text)) / 2.0f : 16.0f) * UViewSettings.itemCounterScale.value().floatValue() + (float)Minecraft.getMinecraft().fontRendererObj.getStringWidth(text) * this.getScale().value().floatValue())), (int)((int)(vector.getY() + (float)(horizontal ? 16 : 7) * UViewSettings.itemCounterScale.value().floatValue() + (float)Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT * this.getScale().value().floatValue())), (int)UViewSettings.itemCounterBackgroundColor.value());
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)(vector.getX() + (horizontal ? (float)(16 - Minecraft.getMinecraft().fontRendererObj.getStringWidth(text)) / 2.0f : 16.0f) * UViewSettings.itemCounterScale.value().floatValue()), (float)(vector.getY() + (float)(horizontal ? 16 : 7) * UViewSettings.itemCounterScale.value().floatValue()), (float)0.0f);
            GlStateManager.scale((float)this.getScale().value().floatValue(), (float)this.getScale().value().floatValue(), (float)this.getScale().value().floatValue());
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            if (UViewSettings.itemCounterColor.isChroma().booleanValue()) {
                ChromaUtils.drawChromaString(text, 0.0f, 0.0f, false, true);
            } else {
                this.mc.fontRendererObj.drawStringWithShadow(text, 0.0f, 0.0f, UViewSettings.itemCounterColor.value().intValue());
            }
            GlStateManager.popMatrix();
        }
    }

    private Vector getPositionForNthPiece(int n, float x, float y, boolean vertical) {
        if (vertical) {
            x += (float)(16 * n) * this.getScale().value().floatValue();
        } else {
            y += (float)(16 * n) * this.getScale().value().floatValue();
        }
        return new Vector(x, y);
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

