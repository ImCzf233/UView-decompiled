// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.SoundHandler
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.renderer.GlStateManager
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.util.Color
 *  org.lwjgl.util.Point
 */
package com.domcer.uview.settings.gui.button;

import com.domcer.uview.settings.gui.GuiSettings;
import com.domcer.uview.settings.types.ColorSetting;
import com.domcer.uview.util.ChromaUtils;
import com.domcer.uview.util.MouseUtils;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Point;

public class GuiColorPicker
extends GuiButton {
    private GuiSettings guiSettings;
    private boolean pickerVisible;
    public ColorSetting colorSetting;
    public String text;
    private long pickerCreated;
    private long lastClicked;

    public GuiColorPicker(GuiSettings guiSettings, ColorSetting colorSetting, int buttonId, int x, int y) {
        super(buttonId, x, y, 146, 16, "");
        this.guiSettings = guiSettings;
        this.colorSetting = colorSetting;
        this.pickerVisible = false;
        this.pickerCreated = System.currentTimeMillis() / 1000L;
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            FontRenderer fontrenderer = mc.fontRendererObj;
            mc.getTextureManager().bindTexture(buttonTextures);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GuiColorPicker.drawRect((int)(this.xPosition - 1), (int)(this.yPosition - 1), (int)(this.xPosition + this.width + 1), (int)(this.yPosition + this.height + 1), (int)-6250336);
            if (this.colorSetting.isChroma().booleanValue()) {
                ChromaUtils.drawChromaRect(1, this.xPosition, this.yPosition, this.width, this.height);
            } else {
                GuiColorPicker.drawRect((int)this.xPosition, (int)this.yPosition, (int)(this.xPosition + this.width), (int)(this.yPosition + this.height), (int)this.colorSetting.value());
                if (this.text != null) {
                    String hex = this.text.toUpperCase();
                    this.drawString(fontrenderer, hex, this.xPosition + (this.width - fontrenderer.getStringWidth(hex)) / 2, this.yPosition + (this.height - fontrenderer.FONT_HEIGHT) / 2, -1);
                } else {
                    String s = Integer.toHexString(this.colorSetting.value()).toUpperCase();
                    String hex = s.length() == 8 ? s.substring(2, 8) : s;
                    this.drawString(fontrenderer, hex, this.xPosition + (this.width - fontrenderer.getStringWidth(hex)) / 2, this.yPosition + (this.height - fontrenderer.FONT_HEIGHT) / 2, -1);
                }
            }
            if (this.pickerVisible && this.enabled) {
                int offsetX = 200;
                int offsetY = -40;
                int current = 0;
                int currentY = 0;
                this.drawGradientRect(this.xPosition - 1 + offsetX, this.yPosition - 1 + offsetY, this.xPosition + 100 + 1 + offsetX, this.yPosition + 100 + 1 + offsetY, Color.BLACK.getRGB(), Color.BLACK.getRGB());
                for (int x = 0; x < 100; ++x) {
                    for (int y = 0; y < 100; ++y) {
                        int color = this.getColorAtPosition(x, y);
                        this.drawGradientRect(this.xPosition + x + offsetX, this.yPosition + y + offsetY, this.xPosition + x + 1 + offsetX, this.yPosition + y + 1 + offsetY, color, color);
                        if (x == 0 || y == 0) continue;
                        if ((0xFF000000 | this.colorSetting.value()) == color) {
                            current = x;
                            currentY = y;
                            this.drawHorizontalLine(this.xPosition + x - 2 + offsetX, this.xPosition + x + 2 + offsetX, this.yPosition + y + offsetY, -1);
                        }
                        if (x - 1 == current && y == currentY) {
                            this.drawVerticalLine(this.xPosition + x - 1 + offsetX, this.yPosition + y - 3 + offsetY, this.yPosition + y + 3 + offsetY, -1);
                        }
                        if (x - 2 != current || y != currentY) continue;
                        this.drawHorizontalLine(this.xPosition + x - 2 + offsetX, this.xPosition + x + 2 + offsetX, this.yPosition + y + offsetY, -1);
                    }
                }
                if (this.colorSetting.isAlphaEnabled()) {
                    GuiColorPicker.drawRect((int)(this.xPosition + offsetX), (int)(this.yPosition + offsetY - 10), (int)(this.xPosition + offsetX + 100), (int)(this.yPosition + offsetY + 2 - 10), (int)-6250336);
                    Color color = new Color(this.colorSetting.value(), true);
                    int ax = (int)((float)color.getAlpha() / 255.0f * 100.0f);
                    this.drawCircle(this.xPosition + offsetX + ax - 12, this.yPosition + offsetY - 20, 0, 191, 255);
                }
                if (this.colorSetting.isChromaEnabled()) {
                    GuiColorPicker.drawRect((int)(this.xPosition - 1 + offsetX + 35), (int)(this.yPosition - 1 + offsetY + 112), (int)(this.xPosition + 1 + offsetX + 45), (int)(this.yPosition + 1 + offsetY + 122), (int)-6250336);
                    GuiColorPicker.drawRect((int)(this.xPosition + offsetX + 35), (int)(this.yPosition + offsetY + 112), (int)(this.xPosition + offsetX + 45), (int)(this.yPosition + offsetY + 122), (int)(this.colorSetting.isChroma() != false ? -16728065 : -16777216));
                    this.drawString(fontrenderer, "\u968f\u673a\u989c\u8272", this.xPosition + offsetX + 50, this.yPosition + offsetY + 122 - fontrenderer.FONT_HEIGHT, -1);
                }
            }
            if (System.currentTimeMillis() / 1000L > this.pickerCreated + 1L) {
                this.mouseDragged(mc, mouseX, mouseY);
            }
        }
    }

    private void drawCircle(int x0, int y0, int r, int g, int b) {
        GlStateManager.pushMatrix();
        GL11.glScaled((double)2.0, (double)2.0, (double)2.0);
        GL11.glDisable((int)3553);
        int i = (int)((double)x0 * 0.5) + 6;
        int j = (int)((double)y0 * 0.5) + 6;
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GlStateManager.color((float)r, (float)g, (float)b, (float)1.0f);
        GL11.glEnable((int)2848);
        GL11.glLineWidth((float)3.0f);
        GL11.glBegin((int)6);
        GL11.glVertex2d((double)i, (double)j);
        for (int k = 0; k <= 150; ++k) {
            GL11.glVertex2d((double)((double)i - Math.sin((double)k * 0.06283185307179587)), (double)((double)j - Math.cos((double)k * 0.06283185307179587)));
        }
        GL11.glEnd();
        GL11.glDisable((int)2848);
        GL11.glEnable((int)3553);
        GlStateManager.popMatrix();
    }

    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        int offsetX = 200;
        int offsetY = -40;
        if (this.pickerVisible && this.enabled) {
            if (this.colorSetting.isAlphaEnabled() && MouseUtils.isMouseWithinBounds(this.xPosition + offsetX, this.yPosition + offsetY - 10, 100, 2)) {
                if (System.currentTimeMillis() - this.lastClicked <= 100L) {
                    return true;
                }
                this.lastClicked = System.currentTimeMillis();
                Point mousePoint = MouseUtils.getMousePos();
                int alpha = (int)(255.0f * ((float)(mousePoint.getX() - this.xPosition - offsetX) / 100.0f));
                Color color = new Color(this.colorSetting.value(), true);
                org.lwjgl.util.Color color1 = new org.lwjgl.util.Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
                color1.setAlpha(alpha);
                this.colorSetting.setValue((color1.getAlpha() & 0xFF) << 24 | (color1.getRed() & 0xFF) << 16 | (color1.getGreen() & 0xFF) << 8 | color1.getBlue() & 0xFF);
                this.pickerVisible = true;
                this.guiSettings.colorPicker = this;
                return true;
            }
            if (this.colorSetting.isChromaEnabled() && MouseUtils.isMouseWithinBounds(this.xPosition + offsetX + 35, this.yPosition + offsetY + 112, 10, 10)) {
                if (System.currentTimeMillis() - this.lastClicked <= 100L) {
                    return true;
                }
                this.lastClicked = System.currentTimeMillis();
                this.colorSetting.setChroma(this.colorSetting.isChroma() == false);
                this.pickerVisible = true;
                this.guiSettings.colorPicker = this;
                return true;
            }
            if (MouseUtils.isMouseWithinBounds(this.xPosition + offsetX, this.yPosition + offsetY, 100, 100)) {
                Point mousePoint = MouseUtils.getMousePos();
                this.colorSetting.setValue(this.getColorAtPosition(mousePoint.getX() - this.xPosition - offsetX, mousePoint.getY() - this.yPosition - offsetY));
            } else if (MouseUtils.isMouseWithinBounds(this.xPosition + offsetX - 30, this.yPosition + offsetY - 30, 160, 160)) {
                this.pickerVisible = true;
                this.guiSettings.colorPicker = this;
            } else if (!super.mousePressed(mc, mouseX, mouseY)) {
                this.pickerVisible = false;
                this.guiSettings.colorPicker = null;
            }
            return true;
        }
        if (!this.pickerVisible && super.mousePressed(mc, mouseX, mouseY)) {
            this.pickerVisible = true;
            this.guiSettings.colorPicker = this;
            return true;
        }
        return super.mousePressed(mc, mouseX, mouseY);
    }

    public void mouseDragged(Minecraft mc, int mouseX, int mouseY) {
        if (Mouse.isButtonDown((int)0)) {
            this.mousePressed(mc, mouseX, mouseY);
        }
    }

    public void setPickerVisible(boolean visible) {
        this.pickerVisible = visible;
    }

    private int getColorAtPosition(int x, int y) {
        float s = 0;
        float h;
        if (x < 0 || x > 100 || y < 0 || y > 100) {
            return 0;
        }
        boolean grey = x >= 95;
        float f = h = grey ? 0.0f : (float)x / 95.0f;
        float f2 = grey ? 0.0f : (s = y <= 50 ? (float)y / 50.0f : 1.0f);
        float v = grey ? 1.0f - (float)y / 100.0f : (y > 50 ? 1.0f - (float)(y - 50) / 50.0f : 1.0f);
        return Color.HSBtoRGB(h, s, v);
    }

    public void playPressSound(SoundHandler soundHandler) {
    }
}

