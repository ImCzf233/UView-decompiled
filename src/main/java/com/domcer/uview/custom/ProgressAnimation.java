// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.util.MathHelper
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Pre
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.domcer.uview.custom;

import com.domcer.uview.module.impl.game.Bossbar;
import com.domcer.uview.skin.DelayedTask;
import java.text.DecimalFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ProgressAnimation {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.0%");
    private final int x;
    private final int y;
    private final int color;
    private final PercentagePosition percentagePosition;
    private float progress;
    private int ticks;

    public ProgressAnimation(int x, int y, int color, PercentagePosition percentagePosition) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.percentagePosition = percentagePosition;
    }

    public void start() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    public void setProgress(float progress) {
        this.progress = MathHelper.clamp_float((float)progress, (float)0.0f, (float)1.0f);
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Pre event) {
        if (event.type == RenderGameOverlayEvent.ElementType.HOTBAR) {
            if (this.isFinish()) {
                MinecraftForge.EVENT_BUS.unregister((Object)this);
            } else {
                this.play();
            }
        }
    }

    public boolean isFinish() {
        return this.progress >= 1.0f && this.ticks >= 20;
    }

    public void play() {
        if (this.progress >= 1.0f) {
            ++this.ticks;
        }
        int widthLocation = 182;
        int healthScale = (int)(this.progress * (float)(widthLocation + 1));
        GlStateManager.pushMatrix();
        GlStateManager.color((float)((float)(this.color >> 16 & 0xFF) / 255.0f), (float)((float)(this.color >> 8 & 0xFF) / 255.0f), (float)((float)(this.color & 0xFF) / 255.0f), (float)((float)(this.color >> 24 & 0xFF) / 255.0f));
        GlStateManager.translate((double)this.x, (double)this.y, (double)0.0);
        GlStateManager.scale((float)1.1f, (float)1.1f, (float)1.1f);
        Minecraft.getMinecraft().getTextureManager().bindTexture(Gui.icons);
        Bossbar.guiIngame.drawTexturedModalRect(0, 0, 0, 74, widthLocation, 5);
        Bossbar.guiIngame.drawTexturedModalRect(0, 0, 0, 74, widthLocation, 5);
        if (healthScale > 0) {
            Bossbar.guiIngame.drawTexturedModalRect(0, 0, 0, 79, healthScale, 5);
        }
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.popMatrix();
        if (this.percentagePosition != PercentagePosition.HIDE) {
            GlStateManager.pushMatrix();
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            String text = DECIMAL_FORMAT.format(this.progress);
            int fontWidth = Minecraft.getMinecraft().fontRendererObj.getStringWidth(text);
            if (this.percentagePosition == PercentagePosition.LEFT) {
                GlStateManager.translate((double)(this.x - 2 - fontWidth), (double)(this.y - 2), (double)0.0);
            } else if (this.percentagePosition == PercentagePosition.TOP) {
                GlStateManager.translate((double)((float)this.x + ((float)widthLocation * 1.1f - (float)fontWidth) / 2.0f), (double)(this.y - 4 - Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT), (double)0.0);
            } else if (this.percentagePosition == PercentagePosition.RIGHT) {
                GlStateManager.translate((double)((float)this.x + (float)widthLocation * 1.1f + 2.0f), (double)(this.y - 2), (double)0.0);
            } else if (this.percentagePosition == PercentagePosition.BOTTOM) {
                GlStateManager.translate((double)((float)this.x + ((float)widthLocation * 1.1f - (float)fontWidth) / 2.0f), (double)(this.y + 4), (double)0.0);
            }
            GlStateManager.scale((float)1.0f, (float)1.0f, (float)1.0f);
            Minecraft.getMinecraft().fontRendererObj.drawString(text, 0.0f, 0.0f, -1, true);
            GlStateManager.popMatrix();
        }
    }

    public static ProgressAnimation play(int x, int y, int color) {
        return ProgressAnimation.play(x, y, color, PercentagePosition.HIDE);
    }

    public static ProgressAnimation play(int x, int y, int color, PercentagePosition percentagePosition) {
        ProgressAnimation result = new ProgressAnimation(x, y, color, percentagePosition);
        Minecraft.getMinecraft().addScheduledTask(() -> new DelayedTask(result::start));
        return result;
    }

    public static enum PercentagePosition {
        LEFT,
        TOP,
        RIGHT,
        BOTTOM,
        HIDE;

    }
}

