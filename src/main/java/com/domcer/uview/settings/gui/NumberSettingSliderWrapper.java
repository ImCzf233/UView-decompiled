/*
 * Decompiled with CFR 0.152.
 */
package com.domcer.uview.settings.gui;

import com.domcer.uview.settings.types.NumberSetting;

public abstract class NumberSettingSliderWrapper<T extends Number> {
    protected final NumberSetting setting;

    public NumberSettingSliderWrapper(NumberSetting setting) {
        this.setting = setting;
    }

    public NumberSetting getSetting() {
        return this.setting;
    }

    public abstract void handleSlide(float var1);

    public abstract float getSliderValue();

    public abstract String getButtonText();
}

