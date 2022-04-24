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

import com.domcer.uview.settings.types.EnumSetting;
import com.domcer.uview.util.SwitchableEnum;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class EnumSettingButton
extends GuiButton {
    private EnumSetting<?> enumSetting;

    public EnumSettingButton(EnumSetting<?> enumSetting, int buttonId, int x, int y) {
        this(enumSetting, buttonId, x, y, 200, 20);
    }

    public EnumSettingButton(EnumSetting<?> enumSetting, int buttonId, int x, int y, int widthIn, int heightIn) {
        super(buttonId, x, y, widthIn, heightIn, "");
        this.enumSetting = enumSetting;
        this.displayString = enumSetting.value().displayName();
    }

    public boolean mousePressed(Minecraft mc, int p_mouseReleased_1_, int p_mouseReleased_2_) {
        SwitchableEnum senum = this.enumSetting.value().next();
        this.enumSetting.setValue(senum);
        this.displayString = this.enumSetting.value().displayName();
        this.playPressSound(mc.getSoundHandler());
        return true;
    }
}

