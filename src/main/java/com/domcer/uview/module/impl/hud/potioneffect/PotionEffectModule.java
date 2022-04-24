// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiChat
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.WorldRenderer
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Pre
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  org.lwjgl.opengl.GL11
 */
package com.domcer.uview.module.impl.hud.potioneffect;

import com.domcer.uview.module.AbstractModule;
import com.domcer.uview.module.IRenderer;
import com.domcer.uview.settings.UViewSettings;
import com.domcer.uview.settings.types.NumberSetting;
import com.domcer.uview.util.ChromaUtils;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class PotionEffectModule
extends AbstractModule
implements IRenderer {
    public static final PotionEffectModule INSTANCE = new PotionEffectModule();
    private final ResourceLocation inventoryBackground = new ResourceLocation("textures/gui/container/inventory.png");
    private final List<PotionEffect> collection = Lists.newArrayList();

    @Override
    public void onLoad() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.collection.add(new PotionEffect(10, 130, 2));
        this.collection.add(new PotionEffect(5, 10, 1));
        this.collection.add(new PotionEffect(16, 25, 1));
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Pre event) {
        if (event.type == RenderGameOverlayEvent.ElementType.HOTBAR) {
            this.renderLive(event.resolution);
        }
    }

    public void renderLive(ScaledResolution sr) {
        if (this.isDisplable() && UViewSettings.potionEffectHudEnabled.value().booleanValue()) {
            this.drawActivePotionEffects((float)((double)UViewSettings.potionEffectHudX.value().floatValue() * sr.getScaledWidth_double()), (float)((double)UViewSettings.potionEffectHudY.value().floatValue() * sr.getScaledHeight_double()));
        }
    }

    @Override
    public boolean isDisplable() {
        return super.isDisplable() || Minecraft.getMinecraft().currentScreen instanceof GuiChat;
    }

    private void drawActivePotionEffects(float x, float y) {
        Collection collection = Minecraft.getMinecraft().thePlayer.getActivePotionEffects();
        this.drawPotionEffects(collection, (int)x, (int)y);
    }

    private void drawPotionEffects(Collection<PotionEffect> collection, int x, int y) {
        Minecraft mc = Minecraft.getMinecraft();
        if (!collection.isEmpty()) {
            float scale = UViewSettings.potionEffectHudScale.value().floatValue();
            double scale_back = 1.0 / (double)scale;
            GlStateManager.pushMatrix();
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GlStateManager.disableLighting();
            int l = (int)(33.0f * scale);
            if (collection.size() > 5) {
                collection = collection.stream().limit(5L).collect(Collectors.toList());
            }
            for (PotionEffect potioneffect : collection) {
                GlStateManager.scale((float)scale, (float)scale, (float)scale);
                Potion potion = Potion.potionTypes[potioneffect.getPotionID()];
                if (!potion.shouldRender(potioneffect)) continue;
                GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                mc.getTextureManager().bindTexture(this.inventoryBackground);
                if (UViewSettings.potionEffectHudRenderBackground.value().booleanValue()) {
                    boolean smallRect = UViewSettings.potionEffectHudShowDuration.value() == false && UViewSettings.potionEffectHudShowName.value() == false;
                    this.drawTexturedModalRect((int)(scale_back * (double)x), (int)(scale_back * (double)y), 0, 166, smallRect ? 32 : 140, 32);
                }
                if (potion.hasStatusIcon() && UViewSettings.potionEffectHudShowIcon.value().booleanValue()) {
                    int i1 = potion.getStatusIconIndex();
                    this.drawTexturedModalRect((int)(scale_back * (double)x + 6.0), (int)(scale_back * (double)y + 7.0), i1 % 8 * 18, 198 + i1 / 8 * 18, 18, 18);
                }
                potion.renderInventoryEffect((int)(scale_back * (double)x), (int)(scale_back * (double)y), potioneffect, mc);
                if (!potion.shouldRenderInvText(potioneffect)) continue;
                if (UViewSettings.potionEffectHudShowName.value().booleanValue()) {
                    String s1 = I18n.format((String)potion.getName(), (Object[])new Object[0]);
                    if (potioneffect.getAmplifier() == 1) {
                        s1 = s1 + " " + I18n.format((String)"enchantment.level.2", (Object[])new Object[0]);
                    } else if (potioneffect.getAmplifier() == 2) {
                        s1 = s1 + " " + I18n.format((String)"enchantment.level.3", (Object[])new Object[0]);
                    } else if (potioneffect.getAmplifier() == 3) {
                        s1 = s1 + " " + I18n.format((String)"enchantment.level.4", (Object[])new Object[0]);
                    }
                    if (UViewSettings.potionEffectHudTextColor.isChroma().booleanValue()) {
                        ChromaUtils.drawChromaString(s1, (float)scale_back * (float)x + 10.0f + 18.0f, (float)scale_back * (float)y + 6.0f, false, true);
                    } else {
                        mc.fontRendererObj.drawStringWithShadow(s1, (float)scale_back * (float)x + 10.0f + 18.0f, (float)scale_back * (float)y + 6.0f, UViewSettings.potionEffectHudTextColor.value().intValue());
                    }
                }
                String s = Potion.getDurationString((PotionEffect)potioneffect);
                int offset = mc.fontRendererObj.FONT_HEIGHT + 1;
                if (UViewSettings.potionEffectHudShowDuration.value().booleanValue()) {
                    if (UViewSettings.potionEffectHudDurationColor.isChroma().booleanValue()) {
                        ChromaUtils.drawChromaString(s, (float)scale_back * (float)x + 10.0f + 18.0f, (float)scale_back * (float)y + 6.0f + (float)offset, false, true);
                    } else {
                        mc.fontRendererObj.drawStringWithShadow(s, (float)scale_back * (float)x + 10.0f + 18.0f, (float)scale_back * (float)y + 6.0f + (float)offset, UViewSettings.potionEffectHudDurationColor.value().intValue());
                    }
                }
                GlStateManager.scale((double)scale_back, (double)scale_back, (double)scale_back);
                y += l;
            }
            GlStateManager.popMatrix();
        }
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height) {
        float f = 0.00390625f;
        float f1 = 0.00390625f;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos((double)x, (double)(y + height), 0.0).tex((double)((float)textureX * f), (double)((float)(textureY + height) * f1)).endVertex();
        worldrenderer.pos((double)(x + width), (double)(y + height), 0.0).tex((double)((float)(textureX + width) * f), (double)((float)(textureY + height) * f1)).endVertex();
        worldrenderer.pos((double)(x + width), (double)y, 0.0).tex((double)((float)(textureX + width) * f), (double)((float)textureY * f1)).endVertex();
        worldrenderer.pos((double)x, (double)y, 0.0).tex((double)((float)textureX * f), (double)((float)textureY * f1)).endVertex();
        tessellator.draw();
    }

    @Override
    public NumberSetting getX() {
        return UViewSettings.potionEffectHudX;
    }

    @Override
    public NumberSetting getY() {
        return UViewSettings.potionEffectHudY;
    }

    @Override
    public NumberSetting getScale() {
        return UViewSettings.potionEffectHudScale;
    }

    @Override
    public int getHeight() {
        return (int)(98.0f * UViewSettings.potionEffectHudScale.value().floatValue());
    }

    @Override
    public int getWidth() {
        return (int)((float)(UViewSettings.potionEffectHudShowDuration.value() == false && UViewSettings.potionEffectHudShowName.value() == false ? 32 : 120) * UViewSettings.potionEffectHudScale.value().floatValue());
    }

    @Override
    public boolean isGuiEnabled() {
        return UViewSettings.potionEffectHudEnabled.value();
    }

    @Override
    public void render(Minecraft minecraft, float x, float y) {
        this.drawPotionEffects(this.collection, (int)x, (int)y);
    }
}

