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
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.WorldRenderer
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.client.settings.KeyBinding
 *  org.lwjgl.input.Keyboard
 */
package com.domcer.uview.module.impl.hud.keystroke.types;

import com.domcer.uview.module.impl.hud.keystroke.KeyRenderer;
import com.domcer.uview.module.impl.hud.keystroke.KeyStroke;
import com.domcer.uview.settings.UViewSettings;
import com.domcer.uview.util.ChromaUtils;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class MovementRenderer
extends KeyRenderer {
    public MovementRenderer(KeyBinding keyBinding) {
        super(keyBinding);
    }

    @Override
    public void render(int x, int y) {
        Keyboard.poll();
        Minecraft mc = Minecraft.getMinecraft();
        int width = this.getWidth();
        int height = this.getHeight();
        boolean pressed = Keyboard.isKeyDown((int)this.getButton());
        Gui.drawRect((int)x, (int)y, (int)(x + width), (int)(y + height), (int)(pressed ? this.getPressedColor() : this.getColor()));
        String text = this.getKeyOrMouseName(this.getButton());
        if (UViewSettings.keyStrokeArrowKeys.value().booleanValue()) {
            Color baseColor;
            double padding = 5.0;
            double bottom = (double)(y + this.getHeight()) - padding;
            double right = (double)(x + this.getWidth()) - padding;
            double left = (double)x + padding;
            double top = (double)y + padding;
            double centerX = (left + right) / 2.0;
            double centerY = (top + bottom) / 2.0;
            GlStateManager.pushMatrix();
            GlStateManager.translate((double)centerX, (double)centerY, (double)0.0);
            Color topColor = baseColor = new Color(UViewSettings.keyStrokeColor.value());
            Color bottomLeftColor = baseColor;
            Color bottomRightColor = baseColor;
            if (UViewSettings.keyStrokeColor.isChroma().booleanValue()) {
                topColor = this.getChromaColor(centerX, top, 1.0);
                bottomLeftColor = this.getChromaColor(left, bottom, 1.0);
                bottomRightColor = this.getChromaColor(right, bottom, 1.0);
            }
            int angle = 0;
            if (this.getKeyBinding() == mc.gameSettings.keyBindLeft) {
                angle = -90;
                if (UViewSettings.keyStrokeColor.isChroma().booleanValue()) {
                    topColor = this.getChromaColor(centerX, centerY, 1.0);
                    bottomLeftColor = this.getChromaColor(left, bottom, 1.0);
                    bottomRightColor = this.getChromaColor(right, top, 1.0);
                }
            }
            if (this.getKeyBinding() == mc.gameSettings.keyBindBack) {
                angle = -180;
                if (UViewSettings.keyStrokeColor.isChroma().booleanValue()) {
                    topColor = this.getChromaColor(centerX, bottom, 1.0);
                    bottomLeftColor = this.getChromaColor(right, top, 1.0);
                    bottomRightColor = this.getChromaColor(left, top, 1.0);
                }
            }
            if (this.getKeyBinding() == mc.gameSettings.keyBindRight) {
                angle = 90;
                if (UViewSettings.keyStrokeColor.isChroma().booleanValue()) {
                    topColor = this.getChromaColor(right, centerY, 1.0);
                    bottomLeftColor = this.getChromaColor(left, top, 1.0);
                    bottomRightColor = this.getChromaColor(left, bottom, 1.0);
                }
            }
            GlStateManager.rotate((float)angle, (float)0.0f, (float)0.0f, (float)1.0f);
            left -= centerX;
            right -= centerX;
            centerX = 0.0;
            top -= centerY;
            bottom -= centerY;
            Tessellator tessellator = Tessellator.getInstance();
            WorldRenderer worldrenderer = tessellator.getWorldRenderer();
            GlStateManager.enableBlend();
            GlStateManager.disableTexture2D();
            GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
            GlStateManager.shadeModel((int)7425);
            worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f);
            worldrenderer.pos(centerX, top, 0.0).color(topColor.getRed(), topColor.getGreen(), topColor.getBlue(), 255).endVertex();
            worldrenderer.pos(centerX, top, 0.0).color(topColor.getRed(), topColor.getGreen(), topColor.getBlue(), 255).endVertex();
            worldrenderer.pos(left, bottom, 0.0).color(bottomLeftColor.getRed(), bottomLeftColor.getGreen(), bottomLeftColor.getBlue(), 255).endVertex();
            worldrenderer.pos(right, bottom, 0.0).color(bottomRightColor.getRed(), bottomRightColor.getGreen(), bottomLeftColor.getBlue(), 255).endVertex();
            tessellator.draw();
            GlStateManager.shadeModel((int)7424);
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
            return;
        }
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)((float)x + ((float)width - (float)mc.fontRendererObj.getStringWidth(text) * KeyStroke.INSTANCE.getScale().value().floatValue()) / 2.0f), (float)((float)y + ((float)height - (float)mc.fontRendererObj.FONT_HEIGHT * KeyStroke.INSTANCE.getScale().value().floatValue()) / 2.0f), (float)0.0f);
        GlStateManager.scale((float)KeyStroke.INSTANCE.getScale().value().floatValue(), (float)KeyStroke.INSTANCE.getScale().value().floatValue(), (float)KeyStroke.INSTANCE.getScale().value().floatValue());
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        if (UViewSettings.keyStrokeColor.isChroma().booleanValue()) {
            ChromaUtils.drawChromaString(text, 0.0f, 0.0f, false, true);
        } else {
            mc.fontRendererObj.drawStringWithShadow(text, 0.0f, 0.0f, UViewSettings.keyStrokeColor.value().intValue());
        }
        GlStateManager.popMatrix();
    }

    private Color getChromaColor(double x, double y, double offsetScale) {
        float v = 2000.0f;
        return new Color(Color.HSBtoRGB((float)(((double)System.currentTimeMillis() - x * 10.0 * offsetScale - y * 10.0 * offsetScale) % (double)v) / v, 0.8f, 0.8f));
    }

    @Override
    public int getWidth() {
        return (int)(22.0f * UViewSettings.keyStrokeScale.value().floatValue());
    }

    @Override
    public int getHeight() {
        return (int)(22.0f * UViewSettings.keyStrokeScale.value().floatValue());
    }
}

