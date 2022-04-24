// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiIngame
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.boss.BossStatus
 *  net.minecraftforge.client.GuiIngameForge
 *  net.minecraftforge.common.MinecraftForge
 */
package com.domcer.uview.module.impl.game;

import com.domcer.uview.module.AbstractModule;
import com.domcer.uview.module.IRenderer;
import com.domcer.uview.settings.UViewSettings;
import com.domcer.uview.settings.types.NumberSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.boss.BossStatus;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.common.MinecraftForge;

public class Bossbar
extends AbstractModule
implements IRenderer {
    public static final Bossbar INSTANCE = new Bossbar();
    public static final GuiIngame guiIngame = new GuiIngame(Minecraft.getMinecraft());
    private String previousBossName;
    private int previousStatusBarTime;
    private float previousHealthScale;
    public static String customName = null;
    public static int customColor = -5111669;
    public static float customHealthScale = 0.5f;

    @Override
    public void onLoad() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    public static void store() {
        if (BossStatus.bossName != null && !BossStatus.bossName.equals("Boss\u8840\u6761")) {
            Bossbar.INSTANCE.previousBossName = BossStatus.bossName;
            Bossbar.INSTANCE.previousStatusBarTime = BossStatus.statusBarTime;
            Bossbar.INSTANCE.previousHealthScale = BossStatus.healthScale;
        }
        BossStatus.bossName = "Boss\u8840\u6761";
        BossStatus.statusBarTime = Integer.MAX_VALUE;
        BossStatus.healthScale = 1.0f;
    }

    public static void restore() {
        BossStatus.bossName = Bossbar.INSTANCE.previousBossName;
        BossStatus.statusBarTime = Bossbar.INSTANCE.previousStatusBarTime;
        BossStatus.healthScale = Bossbar.INSTANCE.previousHealthScale;
    }

    public static void renderBossbarHealth() {
        if (customName != null) {
            FontRenderer fontRenderer = Minecraft.getMinecraft().fontRendererObj;
            ScaledResolution resolution = new ScaledResolution(Minecraft.getMinecraft());
            double scaledWidth = resolution.getScaledWidth();
            double scaledHeight = resolution.getScaledHeight();
            int widthLocation = 182;
            if (UViewSettings.bossbarProgress.value().booleanValue()) {
                int healthScale = (int)(customHealthScale * (float)(widthLocation + 1));
                GlStateManager.pushMatrix();
                GlStateManager.color((float)((float)(customColor >> 16 & 0xFF) / 255.0f), (float)((float)(customColor >> 8 & 0xFF) / 255.0f), (float)((float)(customColor & 0xFF) / 255.0f), (float)((float)(customColor >> 24 & 0xFF) / 255.0f));
                GlStateManager.translate((double)((double)UViewSettings.bossbarX.value().floatValue() * scaledWidth), (double)((double)UViewSettings.bossbarY.value().floatValue() * scaledHeight + 10.0 * (double)UViewSettings.bossbarScale.value().floatValue()), (double)0.0);
                GlStateManager.scale((float)UViewSettings.bossbarScale.value().floatValue(), (float)UViewSettings.bossbarScale.value().floatValue(), (float)UViewSettings.bossbarScale.value().floatValue());
                Minecraft.getMinecraft().getTextureManager().bindTexture(Gui.icons);
                guiIngame.drawTexturedModalRect(0, 0, 0, 74, widthLocation, 5);
                guiIngame.drawTexturedModalRect(0, 0, 0, 74, widthLocation, 5);
                if (healthScale > 0) {
                    guiIngame.drawTexturedModalRect(0, 0, 0, 79, healthScale, 5);
                }
                GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GlStateManager.popMatrix();
            }
            if (UViewSettings.bossbarText.value().booleanValue()) {
                GlStateManager.pushMatrix();
                GlStateManager.translate((double)(scaledWidth * (double)UViewSettings.bossbarX.value().floatValue() + (double)((float)widthLocation / 2.0f * UViewSettings.bossbarScale.value().floatValue())), (double)(scaledHeight * (double)UViewSettings.bossbarY.value().floatValue()), (double)0.0);
                GlStateManager.scale((float)UViewSettings.bossbarScale.value().floatValue(), (float)UViewSettings.bossbarScale.value().floatValue(), (float)UViewSettings.bossbarScale.value().floatValue());
                GlStateManager.translate((float)((float)(-fontRenderer.getStringWidth(customName)) / 2.0f), (float)0.0f, (float)0.0f);
                fontRenderer.drawStringWithShadow(customName, 0.0f, 0.0f, -1);
                GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GlStateManager.popMatrix();
            }
        } else if (BossStatus.bossName != null && BossStatus.statusBarTime > 0 && UViewSettings.bossbarEnabled.value().booleanValue() && GuiIngameForge.renderBossHealth) {
            --BossStatus.statusBarTime;
            FontRenderer fontRenderer = Minecraft.getMinecraft().fontRendererObj;
            ScaledResolution resolution = new ScaledResolution(Minecraft.getMinecraft());
            double scaledWidth = resolution.getScaledWidth();
            double scaledHeight = resolution.getScaledHeight();
            int widthLocation = 182;
            if (UViewSettings.bossbarProgress.value().booleanValue()) {
                int healthScale = (int)(BossStatus.healthScale * (float)(widthLocation + 1));
                GlStateManager.pushMatrix();
                GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GlStateManager.translate((double)((double)UViewSettings.bossbarX.value().floatValue() * scaledWidth), (double)((double)UViewSettings.bossbarY.value().floatValue() * scaledHeight + 10.0 * (double)UViewSettings.bossbarScale.value().floatValue()), (double)0.0);
                GlStateManager.scale((float)UViewSettings.bossbarScale.value().floatValue(), (float)UViewSettings.bossbarScale.value().floatValue(), (float)UViewSettings.bossbarScale.value().floatValue());
                Minecraft.getMinecraft().getTextureManager().bindTexture(Gui.icons);
                guiIngame.drawTexturedModalRect(0, 0, 0, 74, widthLocation, 5);
                guiIngame.drawTexturedModalRect(0, 0, 0, 74, widthLocation, 5);
                if (healthScale > 0) {
                    guiIngame.drawTexturedModalRect(0, 0, 0, 79, healthScale, 5);
                }
                GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GlStateManager.popMatrix();
            }
            if (UViewSettings.bossbarText.value().booleanValue()) {
                GlStateManager.pushMatrix();
                GlStateManager.translate((double)(scaledWidth * (double)UViewSettings.bossbarX.value().floatValue() + (double)((float)widthLocation / 2.0f * UViewSettings.bossbarScale.value().floatValue())), (double)(scaledHeight * (double)UViewSettings.bossbarY.value().floatValue()), (double)0.0);
                GlStateManager.scale((float)UViewSettings.bossbarScale.value().floatValue(), (float)UViewSettings.bossbarScale.value().floatValue(), (float)UViewSettings.bossbarScale.value().floatValue());
                GlStateManager.translate((float)((float)(-fontRenderer.getStringWidth(BossStatus.bossName)) / 2.0f), (float)0.0f, (float)0.0f);
                fontRenderer.drawStringWithShadow(BossStatus.bossName, 0.0f, 0.0f, -1);
                GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GlStateManager.popMatrix();
            }
        }
    }

    @Override
    public NumberSetting getX() {
        return UViewSettings.bossbarX;
    }

    @Override
    public NumberSetting getY() {
        return UViewSettings.bossbarY;
    }

    @Override
    public NumberSetting getScale() {
        return UViewSettings.bossbarScale;
    }

    @Override
    public int getHeight() {
        if (UViewSettings.bossbarText.value().booleanValue() && !UViewSettings.bossbarProgress.value().booleanValue()) {
            return (int)(8.0f * UViewSettings.bossbarScale.value().floatValue());
        }
        return (int)(16.0f * UViewSettings.bossbarScale.value().floatValue());
    }

    @Override
    public int getWidth() {
        return (int)(182.0f * UViewSettings.bossbarScale.value().floatValue());
    }

    @Override
    public boolean isGuiEnabled() {
        return UViewSettings.bossbarEnabled.value();
    }

    @Override
    public void render(Minecraft minecraft, float x, float y) {
        Bossbar.store();
        Bossbar.renderBossbarHealth();
    }

    public void clear() {
        customName = null;
        customColor = -5111669;
        customHealthScale = 1.0f;
    }
}

