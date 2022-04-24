// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.util.MathHelper
 */
package com.domcer.uview.settings.gui.button;

import com.domcer.uview.settings.gui.NumberSettingSliderWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;

public class NumberSettingSlider
extends GuiButton {
    private float sliderValue;
    public boolean dragging;
    private NumberSettingSliderWrapper numberSettingSliderWrapper;

    public NumberSettingSlider(NumberSettingSliderWrapper numberSettingSliderWrapper, int p_i45017_1_, int p_i45017_2_, int p_i45017_3_) {
        super(p_i45017_1_, p_i45017_2_, p_i45017_3_, 150, 20, "");
        this.numberSettingSliderWrapper = numberSettingSliderWrapper;
        this.sliderValue = numberSettingSliderWrapper.getSliderValue();
        this.displayString = numberSettingSliderWrapper.getButtonText();
    }

    protected int getHoverState(boolean p_getHoverState_1_) {
        return 0;
    }

    protected void mouseDragged(Minecraft p_mouseDragged_1_, int p_mouseDragged_2_, int p_mouseDragged_3_) {
        if (this.visible) {
            if (this.dragging) {
                this.sliderValue = (float)(p_mouseDragged_2_ - (this.xPosition + 4)) / (float)(this.width - 8);
                this.sliderValue = MathHelper.clamp_float((float)this.sliderValue, (float)0.0f, (float)1.0f);
                this.numberSettingSliderWrapper.handleSlide(this.sliderValue);
                this.displayString = this.numberSettingSliderWrapper.getButtonText();
            }
            p_mouseDragged_1_.getTextureManager().bindTexture(buttonTextures);
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (float)(this.width - 8)), this.yPosition, 0, 66, 4, 20);
            this.drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (float)(this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
        }
    }

    public boolean mousePressed(Minecraft mc, int p_mousePressed_2_, int p_mousePressed_3_) {
        if (super.mousePressed(mc, p_mousePressed_2_, p_mousePressed_3_)) {
            this.sliderValue = (float)(p_mousePressed_2_ - (this.xPosition + 4)) / (float)(this.width - 8);
            this.sliderValue = MathHelper.clamp_float((float)this.sliderValue, (float)0.0f, (float)1.0f);
            this.numberSettingSliderWrapper.handleSlide(this.sliderValue);
            this.displayString = this.numberSettingSliderWrapper.getButtonText();
            this.dragging = true;
            this.playPressSound(mc.getSoundHandler());
            return true;
        }
        return false;
    }

    public void mouseReleased(int p_mouseReleased_1_, int p_mouseReleased_2_) {
        this.dragging = false;
    }
}

