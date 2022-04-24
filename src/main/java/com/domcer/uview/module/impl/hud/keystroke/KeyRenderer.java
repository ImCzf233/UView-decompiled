// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.settings.KeyBinding
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 */
package com.domcer.uview.module.impl.hud.keystroke;

import com.domcer.uview.settings.UViewSettings;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public abstract class KeyRenderer {
    private final KeyBinding keyBinding;

    public KeyRenderer(KeyBinding keyBinding) {
        this.keyBinding = keyBinding;
    }

    public abstract void render(int var1, int var2);

    public abstract int getWidth();

    public abstract int getHeight();

    protected final int getColor() {
        return UViewSettings.keyStrokeBackgroundColor.value();
    }

    protected final int getPressedColor() {
        return UViewSettings.keyStrokePressedBackgroundColor.value();
    }

    public KeyBinding getKeyBinding() {
        return this.keyBinding;
    }

    public int getButton() {
        return this.keyBinding.getKeyCode();
    }

    protected String getKeyOrMouseName(int keyCode) {
        if (keyCode < 0) {
            String openglName = Mouse.getButtonName((int)(keyCode + 100));
            if (openglName != null) {
                if (openglName.equalsIgnoreCase("button0")) {
                    return "LMB";
                }
                if (openglName.equalsIgnoreCase("button1")) {
                    return "RMB";
                }
            }
            return openglName;
        }
        return Keyboard.getKeyName((int)keyCode);
    }
}

