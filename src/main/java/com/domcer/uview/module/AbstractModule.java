// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package com.domcer.uview.module;

import net.minecraft.client.Minecraft;

public abstract class AbstractModule {
    public abstract void onLoad();

    public boolean isDisplable() {
        return Minecraft.getMinecraft().currentScreen == null;
    }
}

