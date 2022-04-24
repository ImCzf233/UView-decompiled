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
 */
package com.domcer.uview.module.impl.chat;

import com.domcer.uview.module.AbstractModule;
import com.domcer.uview.settings.UViewSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;

public class BetterChat
extends AbstractModule {
    public static final BetterChat INSTANCE = new BetterChat();
    public static float percentComplete = 0.0f;
    public static long prevMillis = System.currentTimeMillis();

    @Override
    public void onLoad() {
    }

    public static void resetPercentComplete() {
        percentComplete = 0.0f;
    }

    public static void updatePercentage() {
        long current = System.currentTimeMillis();
        long diff = current - prevMillis;
        prevMillis = current;
        if (percentComplete < 1.0f) {
            percentComplete += 0.004f * (float)diff;
        }
        percentComplete = BetterChat.clamp(percentComplete, 0.0f, 1.0f);
    }

    public static float clamp(float number, float min, float max) {
        return number < min ? min : Math.min(number, max);
    }

    public static void drawRect(int i1, int i2, int i3, int i4, int i5) {
        Gui.drawRect((int)i1, (int)i2, (int)i3, (int)i4, (int)((int)(255.0f * (1.0f - UViewSettings.betterChatClear.value().floatValue())) << 24));
    }

    public static void translate(float f1, float f2, float f3) {
        float t = percentComplete;
        float percent = 1.0f - (t -= 1.0f) * t * t * t;
        percent = BetterChat.clamp(percent, 0.0f, 1.0f);
        if (UViewSettings.betterChatSmooth.value().booleanValue()) {
            GlStateManager.translate((float)f1, (float)(8.0f + (9.0f - 9.0f * percent) * f1), (float)f3);
        } else {
            GlStateManager.translate((float)f1, (float)f2, (float)f3);
        }
    }

    public static void drawStringWithShadow(String s, float f1, float f2, int i1, int l1) {
        float t = percentComplete;
        float percent = 1.0f - (t -= 1.0f) * t * t * t;
        percent = BetterChat.clamp(percent, 0.0f, 1.0f);
        if (UViewSettings.betterChatSmooth.value().booleanValue() && l1 <= 1) {
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(s, f1, f2, 0xFFFFFF + ((int)(255.0f * percent) << 24));
        } else {
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(s, f1, f2, i1);
        }
    }

    public static int getHeight() {
        return UViewSettings.betterChatSmooth.value() != false ? 40 : 27;
    }
}

