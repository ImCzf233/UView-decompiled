/*
 * Decompiled with CFR 0.152.
 */
package com.domcer.uview.settings.types;

import com.domcer.uview.settings.ISetting;

public class StringSetting
implements ISetting<String> {
    private final String path;
    private final String desc;
    private String value;
    private final String defValue;

    public StringSetting(String path, String desc, String defValue) {
        this.path = path;
        this.desc = desc;
        this.defValue = this.value = defValue;
    }

    @Override
    public String defaultValue() {
        return this.defValue;
    }

    @Override
    public String value() {
        return this.value;
    }

    @Override
    public void setValue(String value) {
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

