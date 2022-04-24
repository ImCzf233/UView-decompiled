// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.WorldRenderer
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 */
package com.domcer.uview.util;

import java.awt.Color;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class RendererUtils {
    private static final double PI = Math.PI;
    private static final double HALF_PI = 1.5707963267948966;
    private static final Tessellator tessellator = Tessellator.getInstance();
    private static final WorldRenderer worldRenderer = tessellator.getWorldRenderer();

    public static void drawRoundedRectangle(float x, float y, float w, float h, int color, float rounding) {
        double angle;
        int segment;
        GlStateManager.enableBlend();
        GlStateManager.disableCull();
        GlStateManager.disableTexture2D();
        RendererUtils.begin2D(7);
        float x1 = x + rounding;
        float x2 = x + w - rounding;
        float y1 = y;
        float y2 = y + h;
        RendererUtils.addVertex(x1, y2, color);
        RendererUtils.addVertex(x2, y2, color);
        RendererUtils.addVertex(x2, y1, color);
        RendererUtils.addVertex(x1, y1, color);
        x1 = x;
        x2 = x + rounding;
        y1 = y + rounding;
        y2 = y + h - rounding;
        RendererUtils.addVertex(x1, y2, color);
        RendererUtils.addVertex(x2, y2, color);
        RendererUtils.addVertex(x2, y1, color);
        RendererUtils.addVertex(x1, y1, color);
        x1 = x + w - rounding;
        x2 = x + w;
        y1 = y + rounding;
        y2 = y + h - rounding;
        RendererUtils.addVertex(x1, y2, color);
        RendererUtils.addVertex(x2, y2, color);
        RendererUtils.addVertex(x2, y1, color);
        RendererUtils.addVertex(x1, y1, color);
        RendererUtils.end();
        int segments = 64;
        double angleStep = 1.5707963267948966 / (double)segments;
        RendererUtils.begin2D(6);
        double startAngle = -1.5707963267948966;
        double startX = x + rounding;
        double startY = y + rounding;
        RendererUtils.addVertex(startX, startY, color);
        for (segment = 0; segment <= segments; ++segment) {
            angle = startAngle - angleStep * (double)segment;
            RendererUtils.addVertex(startX + (double)rounding * Math.cos(angle), startY + (double)rounding * Math.sin(angle), color);
        }
        RendererUtils.end();
        RendererUtils.begin2D(6);
        startAngle = 0.0;
        startX = x + w - rounding;
        startY = y + rounding;
        RendererUtils.addVertex(startX, startY, color);
        for (segment = 0; segment <= segments; ++segment) {
            angle = startAngle - angleStep * (double)segment;
            RendererUtils.addVertex(startX + (double)rounding * Math.cos(angle), startY + (double)rounding * Math.sin(angle), color);
        }
        RendererUtils.end();
        RendererUtils.begin2D(6);
        startAngle = 1.5707963267948966;
        startX = x + w - rounding;
        startY = y + h - rounding;
        RendererUtils.addVertex(startX, startY, color);
        for (segment = 0; segment <= segments; ++segment) {
            angle = startAngle - angleStep * (double)segment;
            RendererUtils.addVertex(startX + (double)rounding * Math.cos(angle), startY + (double)rounding * Math.sin(angle), color);
        }
        RendererUtils.end();
        RendererUtils.begin2D(6);
        startAngle = Math.PI;
        startX = x + rounding;
        startY = y + h - rounding;
        RendererUtils.addVertex(startX, startY, color);
        for (segment = 0; segment <= segments; ++segment) {
            angle = startAngle - angleStep * (double)segment;
            RendererUtils.addVertex(startX + (double)rounding * Math.cos(angle), startY + (double)rounding * Math.sin(angle), color);
        }
        RendererUtils.end();
        GlStateManager.disableCull();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void begin2D(int drawType) {
        worldRenderer.begin(drawType, DefaultVertexFormats.POSITION_COLOR);
        GlStateManager.shadeModel((int)7425);
    }

    private static void addVertex(double x, double y, int color) {
        Color c = new Color(color, true);
        worldRenderer.pos(x, y, 0.0).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
    }

    public static void end() {
        tessellator.draw();
    }
}

