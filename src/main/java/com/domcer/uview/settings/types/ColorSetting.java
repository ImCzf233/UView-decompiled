/*
 * Decompiled with CFR 0.152.
 */
package com.domcer.uview.settings.types;

import com.domcer.uview.settings.ISetting;

public class ColorSetting
implements ISetting<Integer> {
    private final String path;
    private final String desc;
    private Integer value;
    private final Integer defValue;
    private Boolean chroma;
    private final Boolean defChroma;
    private boolean chromaEnabled;
    private boolean alphaEnabled;

    public ColorSetting(String path, String desc, Integer defValue, Boolean defChroma) {
        this.path = path;
        this.desc = desc;
        this.defValue = this.value = defValue;
        this.defChroma = this.chroma = defChroma;
    }

    @Override
    public Integer defaultValue() {
        return this.defValue;
    }

    @Override
    public Integer value() {
        return this.value;
    }

    @Override
    public void setValue(Integer value) {
        this.value = value;
    }

    public void setChroma(Boolean chroma) {
        this.chroma = chroma;
    }

    public Boolean isChroma() {
        return this.chroma;
    }

    public Boolean defaultChroma() {
        return this.defChroma;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }

    public boolean isChromaEnabled() {
        return this.chromaEnabled;
    }

    public boolean isAlphaEnabled() {
        return this.alphaEnabled;
    }

    public ColorSetting enableChroma() {
        this.chromaEnabled = true;
        return this;
    }

    public ColorSetting enableAlpha() {
        this.alphaEnabled = true;
        return this;
    }
}

