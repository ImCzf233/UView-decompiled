/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package com.domcer.uview.util;

import com.domcer.uview.resource.ResourceConstants;
import net.minecraft.util.ResourceLocation;

public class AnimatedResourceLocation {
    private final int fpt;
    private int currentTick = 0;
    private int currentFrame = 0;
    private final ResourceLocation[] textures;

    public AnimatedResourceLocation(String name, int frames, int fpt) {
        this.fpt = fpt;
        this.textures = new ResourceLocation[frames];
        for (int i = 0; i < frames; ++i) {
            this.textures[i] = ResourceConstants.create(String.format(name + "/%d.res", i));
        }
    }

    public ResourceLocation getCurrentTexture() {
        return this.textures[this.currentFrame];
    }

    public void update() {
        if (this.currentTick > this.fpt) {
            this.currentTick = 0;
            ++this.currentFrame;
            if (this.currentFrame > this.textures.length - 1) {
                this.currentFrame = 0;
            }
        }
        ++this.currentTick;
    }
}

