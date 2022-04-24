// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 */
package com.domcer.uview.settings.gui.button;

import com.domcer.uview.settings.types.BooleanSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class BooleanSettingButton
extends GuiButton {
    private BooleanSetting booleanSetting;

    public BooleanSettingButton(BooleanSetting booleanSetting, int buttonId, int x, int y) {
        this(booleanSetting, buttonId, x, y, 200, 20);
    }

    public BooleanSettingButton(BooleanSetting booleanSetting, int buttonId, int x, int y, int widthIn, int heightIn) {
        super(buttonId, x, y, widthIn, heightIn, "");
        this.booleanSetting = booleanSetting;
        this.displayString = booleanSetting.value() != false ? "\u5f00\u542f" : "\u5173\u95ed";
    }

    public boolean mousePressed(Minecraft mc, int p_mouseReleased_1_, int p_mouseReleased_2_) {
        this.booleanSetting.setValue(this.booleanSetting.value() == false);
        this.displayString = this.booleanSetting.value() != false ? "\u5f00\u542f" : "\u5173\u95ed";
        this.playPressSound(mc.getSoundHandler());
        return true;
    }
}

