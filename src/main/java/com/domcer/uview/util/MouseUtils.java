// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.util.Point
 */
package com.domcer.uview.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.Point;

public class MouseUtils {
    private static final Minecraft mc = Minecraft.getMinecraft();

    public static Point getMousePos() {
        Point scaled = MouseUtils.getScaledDimensions();
        int width = scaled.getX();
        int height = scaled.getY();
        int mouseX = Mouse.getX() * width / MouseUtils.mc.displayWidth;
        int mouseY = height - Mouse.getY() * height / MouseUtils.mc.displayHeight;
        return new Point(mouseX, mouseY);
    }

    public static void moveMouse(int mouseX, int mouseY) {
        Point scaled = MouseUtils.getScaledDimensions();
        int width = scaled.getX();
        int height = scaled.getY();
        int x = (int)Math.round(((double)mouseX + 0.5) * (double)MouseUtils.mc.displayWidth / (double)width);
        int y = mouseY * MouseUtils.mc.displayHeight / height;
        Mouse.setCursorPosition((int)x, (int)y);
    }

    public static Point getScaledDimensions() {
        ScaledResolution sr = new ScaledResolution(mc);
        int width = sr.getScaledWidth();
        int heigth = sr.getScaledHeight();
        return new Point(width, heigth);
    }

    public static boolean isMouseWithinBounds(int minX, int minY, int width, int height) {
        Point mousePos = MouseUtils.getMousePos();
        return mousePos.getX() >= minX && mousePos.getX() <= minX + width && mousePos.getY() >= minY && mousePos.getY() <= minY + height;
    }
}

