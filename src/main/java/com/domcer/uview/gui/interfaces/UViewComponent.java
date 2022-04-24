/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package com.domcer.uview.gui.interfaces;

import net.minecraft.client.Minecraft;

public interface UViewComponent {
    public int getId();

    public String getType();

    public int getX();

    public int getY();

    public int getWidth();

    public int getHeight();

    public void beforeDraw(Minecraft var1, int var2, int var3, int var4, int var5, double var6);

    public void draw(Minecraft var1, int var2, int var3, int var4, int var5, double var6);

    public void afterDraw(Minecraft var1, int var2, int var3, int var4, int var5, double var6);
}

