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
 *  net.minecraft.entity.item.EntityTNTPrimed
 *  org.lwjgl.opengl.GL11
 */
package com.domcer.uview.module.impl.game;

import com.domcer.uview.module.AbstractModule;
import com.domcer.uview.settings.UViewSettings;
import java.text.DecimalFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.item.EntityTNTPrimed;
import org.lwjgl.opengl.GL11;

public class TNTTime
extends AbstractModule {
    public static final TNTTime INSTANCE = new TNTTime();
    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public void onLoad() {
    }

    public static void renderTime(EntityTNTPrimed entity, double x, double y, double z) {
        if (!UViewSettings.tntTimeEnabled.value().booleanValue()) {
            return;
        }
        int fuse = entity.fuse;
        float time = (float)fuse / 20.0f;
        String text = (double)time > 3.0 ? "\u00a7a" : ((double)time > 2.0 ? "\u00a7e" : "\u00a7c");
        text = text + df.format(time) + "\u79d2";
        double lvt_10_1_ = entity.getDistanceSqToEntity(Minecraft.getMinecraft().getRenderManager().livingPlayer);
        if (lvt_10_1_ < 200.0) {
            FontRenderer lvt_12_1_ = Minecraft.getMinecraft().fontRendererObj;
            float lvt_13_1_ = 1.6f;
            float lvt_14_1_ = 0.016666668f * lvt_13_1_;
            GlStateManager.pushMatrix();
            GlStateManager.translate((double)(x + 0.0), (double)(y + (double)entity.height + 0.5 + (double)(entity.hasCustomName() && entity.getAlwaysRenderNameTag() ? 0.4f : 0.0f)), (double)z);
            GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.rotate((float)(-Minecraft.getMinecraft().getRenderManager().playerViewY), (float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.rotate((float)Minecraft.getMinecraft().getRenderManager().playerViewX, (float)1.0f, (float)0.0f, (float)0.0f);
            GlStateManager.scale((float)(-lvt_14_1_), (float)(-lvt_14_1_), (float)lvt_14_1_);
            GlStateManager.disableLighting();
            GlStateManager.depthMask((boolean)false);
            GlStateManager.disableDepth();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
            Tessellator lvt_15_1_ = Tessellator.getInstance();
            WorldRenderer lvt_16_1_ = lvt_15_1_.getWorldRenderer();
            int lvt_17_1_ = 0;
            int lvt_18_1_ = lvt_12_1_.getStringWidth(text) / 2;
            GlStateManager.disableTexture2D();
            lvt_16_1_.begin(7, DefaultVertexFormats.POSITION_COLOR);
            lvt_16_1_.pos((double)(-lvt_18_1_ - 1), (double)(-1 + lvt_17_1_), 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();
            lvt_16_1_.pos((double)(-lvt_18_1_ - 1), (double)(8 + lvt_17_1_), 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();
            lvt_16_1_.pos((double)(lvt_18_1_ + 1), (double)(8 + lvt_17_1_), 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();
            lvt_16_1_.pos((double)(lvt_18_1_ + 1), (double)(-1 + lvt_17_1_), 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();
            lvt_15_1_.draw();
            GlStateManager.enableTexture2D();
            lvt_12_1_.drawString(text, -lvt_12_1_.getStringWidth(text) / 2, lvt_17_1_, 0x20FFFFFF);
            GlStateManager.enableDepth();
            GlStateManager.depthMask((boolean)true);
            lvt_12_1_.drawString(text, -lvt_12_1_.getStringWidth(text) / 2, lvt_17_1_, -1);
            GlStateManager.enableLighting();
            GlStateManager.disableBlend();
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GlStateManager.popMatrix();
        }
    }
}

