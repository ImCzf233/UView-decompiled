/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package com.domcer.uview.module;

import com.domcer.uview.settings.types.NumberSetting;
import net.minecraft.client.Minecraft;

public interface IRenderer {
    public NumberSetting getX();

    public NumberSetting getY();

    public NumberSetting getScale();

    public int getHeight();

    public int getWidth();

    public boolean isGuiEnabled();

    public void render(Minecraft var1, float var2, float var3);
}

