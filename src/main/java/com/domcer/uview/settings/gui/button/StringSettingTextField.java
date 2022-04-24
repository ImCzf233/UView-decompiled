// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiTextField
 */
package com.domcer.uview.settings.gui.button;

import com.domcer.uview.settings.types.StringSetting;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;

public class StringSettingTextField
extends GuiTextField {
    private StringSetting stringSetting;

    public StringSettingTextField(StringSetting stringSetting, int componentId, FontRenderer fontrendererObj, int x, int y, int par5Width, int par6Height) {
        super(componentId, fontrendererObj, x, y, par5Width, par6Height);
        this.stringSetting = stringSetting;
        this.setText(stringSetting.value());
    }

    public void onTyped() {
        this.stringSetting.setValue(this.getText());
    }
}

