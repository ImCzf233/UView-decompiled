// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Pre
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.domcer.uview.custom;

import com.domcer.uview.resource.ResourceConstants;
import com.domcer.uview.skin.DelayedTask;
import com.domcer.uview.util.RendererUtils;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WarningAnimation {
    private int WIDTH = 150;
    private int HEIGHT = 60;
    private int ICON_SIZE = 30;
    private float ROUNDING = 5.0f;
    private float SCALE = 1.0f;
    private final int fadeIn;
    private final int duration;
    private final int fadeOut;
    private final String title;
    private final List<String> subTitles;
    private int ticks;
    private boolean cancelled;

    public WarningAnimation(int fadeIn, int duration, int fadeOut, String title, String subTitle) {
        this.fadeIn = fadeIn;
        this.duration = duration;
        this.fadeOut = fadeOut;
        this.title = title;
        this.subTitles = this.split(subTitle);
    }

    public List<String> split(String input) {
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRendererObj;
        int max = fontRenderer.getStringWidth("\u554a\u554a\u554a\u554a\u554a\u554a\u554a\u554a\u554a\u554a\u554a");
        ArrayList<String> lines = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (fontRenderer.getStringWidth(sb.toString()) >= max) {
                lines.add(sb.toString());
                sb = new StringBuilder();
            }
            sb.append(c);
        }
        if (sb.length() > 0) {
            lines.add(sb.toString());
        }
        return lines;
    }

    public void start() {
        Minecraft.getMinecraft().getSoundHandler().playSound((ISound)PositionedSoundRecord.create((ResourceLocation)new ResourceLocation("random.levelup"), (float)1.0f));
        MinecraftForge.EVENT_BUS.register((Object)this);
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        this.WIDTH = (int)((float)sr.getScaledWidth() * 0.15625f);
        this.HEIGHT = (int)((float)sr.getScaledHeight() * 0.119760476f);
        this.ICON_SIZE = (int)((float)sr.getScaledWidth() * 0.03125f);
        this.ROUNDING = (int)((float)sr.getScaledWidth() * 0.0052083335f);
        this.SCALE = (float)(this.WIDTH * this.HEIGHT) / 9000.0f;
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Pre event) {
        if (event.type == RenderGameOverlayEvent.ElementType.HOTBAR) {
            if (this.cancelled || this.isFinish()) {
                MinecraftForge.EVENT_BUS.unregister((Object)this);
            } else {
                this.play(event.resolution);
            }
        }
    }

    public boolean isFinish() {
        return this.ticks >= this.fadeIn + this.duration + this.fadeOut;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public void play(ScaledResolution sr) {
        ++this.ticks;
        if (this.ticks <= this.fadeIn) {
            this.draw(sr, (float)this.ticks / (float)this.fadeIn);
        } else if (this.ticks <= this.fadeIn + this.duration) {
            this.draw(sr, 1.0f);
        } else {
            this.draw(sr, 1.0f - (float)(this.ticks - this.fadeIn - this.duration) / (float)this.fadeOut);
        }
    }

    private void draw(ScaledResolution sr, float percent) {
        int color = (int)(255.0f * percent) << 24 | 0x520000 | 0xA00 | 0xFF;
        int x = sr.getScaledWidth() - this.WIDTH;
        int y = (int)((float)(sr.getScaledHeight() - this.HEIGHT) + (1.0f - percent) * (float)this.HEIGHT);
        RendererUtils.drawRoundedRectangle(x, y, this.WIDTH, this.HEIGHT, color, this.ROUNDING);
        Minecraft.getMinecraft().getTextureManager().bindTexture(ResourceConstants.SYSTEM_WARNING_ICON);
        Gui.drawModalRectWithCustomSizedTexture((int)((int)((float)(sr.getScaledWidth() - this.WIDTH) + this.ROUNDING + this.ROUNDING)), (int)((int)((float)(sr.getScaledHeight() - this.HEIGHT) + (1.0f - percent) * (float)this.HEIGHT + this.ROUNDING + this.ROUNDING + this.ROUNDING)), (float)this.ICON_SIZE, (float)this.ICON_SIZE, (int)this.ICON_SIZE, (int)this.ICON_SIZE, (float)this.ICON_SIZE, (float)this.ICON_SIZE);
        if (this.title != null) {
            this.drawString(this.title, x + this.WIDTH / 2, (int)((float)(y + this.HEIGHT / 7) - 1.0f * this.SCALE), 1.1f * this.SCALE);
        }
        int ax = x + this.WIDTH / 3;
        int ay = (int)((float)(y + this.HEIGHT / 3) + 3.0f * this.SCALE);
        for (String subTitle : this.subTitles) {
            this.drawString(subTitle, ax, ay, 0.9f * this.SCALE);
            ay = (int)((float)ay + (float)(1 + Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT) * this.SCALE);
        }
    }

    private void drawString(String text, int x, int y, float scale) {
        GlStateManager.pushMatrix();
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.translate((float)x, (float)y, (float)0.0f);
        GlStateManager.scale((float)scale, (float)scale, (float)1.0f);
        Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(text, 0.0f, 0.0f, -1);
        GlStateManager.popMatrix();
    }

    public static WarningAnimation play(int fadeIn, int duration, int fadeOut, String title, String subTitle) {
        WarningAnimation warningAnimation = new WarningAnimation(fadeIn, duration, fadeOut, title, subTitle);
        Minecraft.getMinecraft().addScheduledTask(() -> new DelayedTask(warningAnimation::start));
        return warningAnimation;
    }
}

