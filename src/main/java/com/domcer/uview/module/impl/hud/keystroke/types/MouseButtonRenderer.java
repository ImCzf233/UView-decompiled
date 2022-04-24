// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.settings.KeyBinding
 *  org.lwjgl.input.Mouse
 */
package com.domcer.uview.module.impl.hud.keystroke.types;

import com.domcer.uview.module.impl.hud.keystroke.KeyRenderer;
import com.domcer.uview.module.impl.hud.keystroke.KeyStroke;
import com.domcer.uview.settings.UViewSettings;
import com.domcer.uview.util.ChromaUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Mouse;

public class MouseButtonRenderer
extends KeyRenderer {
    public MouseButtonRenderer(KeyBinding keyBinding) {
        super(keyBinding);
    }

    @Override
    public void render(int x, int y) {
        Mouse.poll();
        int width = this.getWidth();
        int height = this.getHeight();
        Minecraft mc = Minecraft.getMinecraft();
        boolean pressed = Mouse.isButtonDown((int)(this.getKeyBinding().getKeyCode() + 100));
        Gui.drawRect((int)x, (int)y, (int)(x + width), (int)(y + height), (int)(pressed ? this.getPressedColor() : this.getColor()));
        GlStateManager.pushMatrix();
        String text = this.getKeyOrMouseName(this.getButton());
        GlStateManager.translate((float)((float)x + ((float)width - (float)mc.fontRendererObj.getStringWidth(text) * KeyStroke.INSTANCE.getScale().value().floatValue()) / 2.0f), (float)((float)y + ((float)height - (float)mc.fontRendererObj.FONT_HEIGHT * KeyStroke.INSTANCE.getScale().value().floatValue()) / 2.0f), (float)0.0f);
        GlStateManager.scale((float)KeyStroke.INSTANCE.getScale().value().floatValue(), (float)KeyStroke.INSTANCE.getScale().value().floatValue(), (float)KeyStroke.INSTANCE.getScale().value().floatValue());
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        if (UViewSettings.keyStrokeColor.isChroma().booleanValue()) {
            ChromaUtils.drawChromaString(text, 0.0f, 0.0f, false, true);
        } else {
            mc.fontRendererObj.drawStringWithShadow(text, 0.0f, 0.0f, UViewSettings.keyStrokeColor.value().intValue());
        }
        GlStateManager.popMatrix();
    }

    @Override
    public int getWidth() {
        return (int)(34.0f * UViewSettings.keyStrokeScale.value().floatValue());
    }

    @Override
    public int getHeight() {
        return (int)(22.0f * UViewSettings.keyStrokeScale.value().floatValue());
    }
}

