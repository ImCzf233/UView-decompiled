// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.util.MathHelper
 */
package com.domcer.uview.settings.gui.button;

import com.domcer.uview.module.IRenderer;
import com.domcer.uview.settings.gui.GuiHudPosition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.MathHelper;

public class RendererButton
extends GuiButton {
    private static final int CORNER_SIZE = 3;
    private final IRenderer renderer;
    public GuiHudPosition parentGui;
    public boolean clickedCorner = false;
    public int prevWidth;
    public int prevHeight;
    public int prevMouseX;
    public int prevMouseY;
    public float originX;
    public float originY;
    public float originScale;

    public RendererButton(IRenderer renderer) {
        super(0, 0, 0, "");
        this.renderer = renderer;
    }

    public void restorePosition(ScaledResolution scaledResolution) {
        this.renderer.getX().setValue(Float.valueOf(this.originX));
        this.renderer.getY().setValue(Float.valueOf(this.originY));
        this.renderer.getScale().setValue(Float.valueOf(this.originScale));
    }

    public void drawButton(Minecraft mc, ScaledResolution scaledResolution) {
        float x = this.renderer.getX().value().floatValue() * (float)scaledResolution.getScaledWidth();
        float y = this.renderer.getY().value().floatValue() * (float)scaledResolution.getScaledHeight();
        this.renderer.render(mc, x, y);
        if (this.parentGui.selectedRenderer == this) {
            this.drawCorner(x + (float)this.renderer.getWidth(), y + (float)this.renderer.getHeight());
        }
    }

    private void drawCorner(float x, float y) {
        Gui.drawRect((int)((int)x), (int)((int)y), (int)((int)x + 3), (int)((int)y + 3), (int)-16711681);
    }

    public void handleDragCorner(int x, int y, ScaledResolution scaledResolution) {
        this.clickedCorner = true;
        this.handleDragInternal(x - this.prevMouseX, y - this.prevMouseY, scaledResolution);
    }

    private void handleDragInternal(int deltX, int deltY, ScaledResolution scaledResolution) {
        if (Math.abs(deltX) >= Math.abs(deltY)) {
            float scale = ((float)this.prevWidth + (float)deltX * 0.4f) / (float)this.prevWidth;
            scale = MathHelper.clamp_float((float)scale, (float)0.0f, (float)2.0f);
            this.renderer.getScale().setValue(Float.valueOf(scale));
        } else {
            float scale = ((float)this.prevHeight + (float)deltY * 0.4f) / (float)this.prevHeight;
            scale = MathHelper.clamp_float((float)scale, (float)0.0f, (float)2.0f);
            this.renderer.getScale().setValue(Float.valueOf(scale));
        }
    }

    public boolean isClickedCorner(int mouseX, int mouseY, ScaledResolution scaledResolution) {
        return this.isInCorner(this.renderer.getX().value().floatValue() * (float)scaledResolution.getScaledWidth() + (float)this.renderer.getWidth(), this.renderer.getY().value().floatValue() * (float)scaledResolution.getScaledHeight() + (float)this.renderer.getHeight(), mouseX, mouseY);
    }

    private boolean isInCorner(float x, float y, int mouseX, int mouseY) {
        return (float)mouseX >= x && (float)mouseY >= y && (float)mouseX <= x + 3.0f && (float)mouseY <= y + 3.0f;
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
    }

    public boolean mousePressed(Minecraft p_mousePressed_1_, int p_mousePressed_2_, int p_mousePressed_3_) {
        return false;
    }

    public IRenderer getRenderer() {
        return this.renderer;
    }
}

