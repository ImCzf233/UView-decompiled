/*
 * Decompiled with CFR 0.152.
 */
package com.domcer.uview.settings.types;

import com.domcer.uview.settings.ISetting;

public class NumberSetting
implements ISetting<Number> {
    private final String path;
    private final String desc;
    private Number value;
    private final Number defValue;

    public NumberSetting(String path, String desc, Number defValue) {
        this.path = path;
        this.desc = desc;
        this.defValue = this.value = defValue;
    }

    @Override
    public Number defaultValue() {
        return this.defValue;
    }

    @Override
    public Number value() {
        return this.value;
    }

    @Override
    public void setValue(Number value) {
        this.value = value;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }
}

