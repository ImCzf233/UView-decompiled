/*
 * Decompiled with CFR 0.152.
 */
package com.domcer.uview.settings.types;

import com.domcer.uview.settings.ISetting;

public class BooleanSetting
implements ISetting<Boolean> {
    private final String path;
    private final String desc;
    private boolean value;
    private final boolean defValue;

    public BooleanSetting(String path, String desc) {
        this(path, desc, false);
    }

    public BooleanSetting(String path, String desc, boolean defValue) {
        this.path = path;
        this.desc = desc;
        this.defValue = this.value = defValue;
    }

    @Override
    public Boolean defaultValue() {
        return this.defValue;
    }

    @Override
    public Boolean value() {
        return this.value;
    }

    @Override
    public void setValue(Boolean value) {
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

