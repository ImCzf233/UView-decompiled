/*
 * Decompiled with CFR 0.152.
 */
package com.domcer.uview.settings.types;

import com.domcer.uview.settings.ISetting;
import com.domcer.uview.util.SwitchableEnum;

public class EnumSetting<E extends SwitchableEnum>
implements ISetting<SwitchableEnum> {
    private final String path;
    private final String desc;
    private SwitchableEnum value;
    private final SwitchableEnum defValue;

    public EnumSetting(String path, String desc, SwitchableEnum defValue) {
        this.path = path;
        this.desc = desc;
        this.defValue = this.value = defValue;
    }

    @Override
    public SwitchableEnum defaultValue() {
        return this.defValue;
    }

    @Override
    public SwitchableEnum value() {
        return this.value;
    }

    @Override
    public void setValue(SwitchableEnum value) {
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

    public Class<? extends Enum> getClassOfType() {
        return (Class<? extends Enum>) this.defValue.getClass();
    }
}

