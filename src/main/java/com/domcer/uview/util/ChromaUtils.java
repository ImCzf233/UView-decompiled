// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.WorldRenderer
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.util.EnumChatFormatting
 */
package com.domcer.uview.util;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumChatFormatting;

public class ChromaUtils {
    public static void drawChromaString(String text, float xIn, float y, boolean isStatic, boolean shadow) {
        FontRenderer renderer = Minecraft.getMinecraft().fontRendererObj;
        int x = (int)xIn;
        text = EnumChatFormatting.getTextWithoutFormattingCodes((String)text);
        for (char c : text.toCharArray()) {
            long dif = (long)((float)((long)x * 10L) - y * 10.0f);
            if (isStatic) {
                dif = 0L;
            }
            long l = System.currentTimeMillis() - dif;
            float ff = isStatic ? 1000.0f : 2000.0f;
            int i = Color.HSBtoRGB((float)(l % (long)((int)ff)) / ff, 0.8f, 0.8f);
            String tmp = String.valueOf(c);
            renderer.drawString(tmp, (float)x, y, i, shadow);
            x += renderer.getCharWidth(c);
        }
    }

    public static void drawChromaRect(int zLevel, int x, int y, int width, int height) {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)(y + height), (float)0.0f);
        GlStateManager.rotate((float)-90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        boolean p_drawGradientRect_1_ = false;
        boolean p_drawGradientRect_2_ = false;
        int p_drawGradientRect_5_ = Color.HSBtoRGB((float)((System.currentTimeMillis() - (long)x * 10L - (long)y * 10L) % 2000L) / 2000.0f, 0.8f, 0.8f);
        int p_drawGradientRect_6_ = Color.HSBtoRGB((float)((System.currentTimeMillis() - (long)(x + width / 2) * 10L - (long)y * 10L) % 2000L) / 2000.0f, 0.8f, 0.8f);
        float lvt_7_1_ = (float)(p_drawGradientRect_5_ >> 24 & 0xFF) / 255.0f;
        float lvt_8_1_ = (float)(p_drawGradientRect_5_ >> 16 & 0xFF) / 255.0f;
        float lvt_9_1_ = (float)(p_drawGradientRect_5_ >> 8 & 0xFF) / 255.0f;
        float lvt_10_1_ = (float)(p_drawGradientRect_5_ & 0xFF) / 255.0f;
        float lvt_11_1_ = (float)(p_drawGradientRect_6_ >> 24 & 0xFF) / 255.0f;
        float lvt_12_1_ = (float)(p_drawGradientRect_6_ >> 16 & 0xFF) / 255.0f;
        float lvt_13_1_ = (float)(p_drawGradientRect_6_ >> 8 & 0xFF) / 255.0f;
        float lvt_14_1_ = (float)(p_drawGradientRect_6_ & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        GlStateManager.shadeModel((int)7425);
        Tessellator lvt_15_1_ = Tessellator.getInstance();
        WorldRenderer lvt_16_1_ = lvt_15_1_.getWorldRenderer();
        lvt_16_1_.begin(7, DefaultVertexFormats.POSITION_COLOR);
        lvt_16_1_.pos((double)height, (double)width, (double)zLevel).color(lvt_12_1_, lvt_13_1_, lvt_14_1_, lvt_11_1_).endVertex();
        lvt_15_1_.draw();
        GlStateManager.shadeModel((int)7424);
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }
}

