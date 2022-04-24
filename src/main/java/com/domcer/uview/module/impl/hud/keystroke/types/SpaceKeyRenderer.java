// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.settings.KeyBinding
 *  org.lwjgl.input.Keyboard
 */
package com.domcer.uview.module.impl.hud.keystroke.types;

import com.domcer.uview.module.impl.hud.keystroke.KeyRenderer;
import com.domcer.uview.settings.UViewSettings;
import com.domcer.uview.util.ChromaUtils;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class SpaceKeyRenderer
extends KeyRenderer {
    public SpaceKeyRenderer(KeyBinding keyBinding) {
        super(keyBinding);
    }

    @Override
    public void render(int x, int y) {
        Keyboard.poll();
        int width = this.getWidth();
        int height = this.getHeight();
        boolean pressed = Keyboard.isKeyDown((int)this.getButton());
        Gui.drawRect((int)x, (int)y, (int)(x + width), (int)(y + height), (int)(pressed ? this.getPressedColor() : this.getColor()));
        if (UViewSettings.keyStrokeColor.isChroma().booleanValue()) {
            ChromaUtils.drawChromaRect(1, (int)((float)x + ((float)width - (float)width / 1.6f) / 2.0f), (int)((float)y + (float)(height - height / 5) / 2.0f), (int)((float)width / 1.6f), height / 5);
        } else {
            Gui.drawRect((int)((int)((float)x + ((float)width - (float)width / 1.6f) / 2.0f)), (int)((int)((float)y + (float)(height - height / 5) / 2.0f)), (int)((int)((float)x + ((float)width - (float)width / 1.6f) / 2.0f + (float)width / 1.6f)), (int)((int)((float)y + (float)(height - height / 5) / 2.0f) + height / 5), (int)UViewSettings.keyStrokeColor.value());
        }
    }

    @Override
    public int getWidth() {
        return (int)(70.0f * UViewSettings.keyStrokeScale.value().floatValue());
    }

    @Override
    public int getHeight() {
        return (int)(16.0f * UViewSettings.keyStrokeScale.value().floatValue());
    }
}

