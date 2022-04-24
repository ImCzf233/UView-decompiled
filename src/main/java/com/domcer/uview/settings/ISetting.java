/*
 * Decompiled with CFR 0.152.
 */
package com.domcer.uview.settings;

public interface ISetting<T> {
    public T defaultValue();

    public T value();

    public void setValue(T var1);

    public String getPath();

    public String getDesc();
}

