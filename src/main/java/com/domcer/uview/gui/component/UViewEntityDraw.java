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
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.domcer.uview.gui.component;

import com.domcer.uview.gui.interfaces.UViewComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class UViewEntityDraw
extends Gui
implements UViewComponent {
    public int width = 200;
    public int height = 20;
    public int xPosition;
    public int yPosition;
    public int id;
    protected boolean hovered;
    private EntityLivingBase entityLiving;
    private int i;

    public UViewEntityDraw(int id, int x, int y, EntityLivingBase entityLiving) {
        this(id, x, y, 64, 64, entityLiving);
    }

    public UViewEntityDraw(int id, int x, int y, int widthIn, int heightIn, EntityLivingBase entityLiving) {
        this.id = id;
        this.xPosition = x;
        this.yPosition = y;
        this.width = widthIn;
        this.height = heightIn;
        this.entityLiving = entityLiving;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getType() {
        return "UViewEntityDraw";
    }

    @Override
    public int getX() {
        return this.xPosition;
    }

    @Override
    public int getY() {
        return this.yPosition;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public void beforeDraw(Minecraft mc, int mouseX, int mouseY, int offsetX, int offsetY, double scaled) {
        this.hovered = (double)mouseX >= (double)this.xPosition * scaled + (double)offsetX && (double)mouseY >= (double)this.yPosition * scaled + (double)offsetY && (double)mouseX < (double)this.xPosition * scaled + (double)offsetX + (double)this.width * scaled && (double)mouseY < (double)this.yPosition * scaled + (double)offsetY + (double)this.height * scaled;
    }

    @Override
    public void draw(Minecraft mc, int mouseX, int mouseY, int offsetX, int offsetY, double scaled) {
        if (this.isMouseOver()) {
            ++this.i;
            UViewEntityDraw.drawRect((int)mouseX, (int)mouseY, (int)(mouseX + 70), (int)(mouseY + 105), (int)-3750202);
            this.drawGradientRect(mouseX + 2, mouseY + 2, mouseX + 68, mouseY + 103, -5383962, -7876885);
            if (this.entityLiving != null) {
                GlStateManager.enableColorMaterial();
                GlStateManager.pushMatrix();
                GlStateManager.translate((float)(mouseX + 35), (float)(mouseY + 90), (float)50.0f);
                GlStateManager.scale((float)-40.0f, (float)40.0f, (float)40.0f);
                GlStateManager.rotate((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                float lvt_6_1_ = this.entityLiving.renderYawOffset;
                float lvt_7_1_ = this.entityLiving.rotationYaw;
                float lvt_8_1_ = this.entityLiving.rotationPitch;
                float lvt_9_1_ = this.entityLiving.prevRotationYawHead;
                float lvt_10_1_ = this.entityLiving.rotationYawHead;
                GlStateManager.rotate((float)135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                RenderHelper.enableStandardItemLighting();
                GlStateManager.rotate((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GlStateManager.rotate((float)(-((float)Math.atan((float)mouseY / 40.0f)) * 20.0f), (float)1.0f, (float)0.0f, (float)0.0f);
                this.entityLiving.renderYawOffset += (float)this.i;
                this.entityLiving.rotationYaw += (float)this.i;
                this.entityLiving.rotationPitch = 0.0f;
                this.entityLiving.rotationYawHead += (float)this.i;
                this.entityLiving.prevRotationYawHead += (float)this.i;
                GlStateManager.translate((float)0.0f, (float)0.0f, (float)0.0f);
                RenderManager lvt_11_1_ = Minecraft.getMinecraft().getRenderManager();
                lvt_11_1_.setPlayerViewY(180.0f);
                lvt_11_1_.setRenderShadow(false);
                lvt_11_1_.renderEntityWithPosYaw((Entity)this.entityLiving, 0.0, 0.0, 0.0, 0.0f, 1.0f);
                lvt_11_1_.setRenderShadow(true);
                this.entityLiving.renderYawOffset = lvt_6_1_;
                this.entityLiving.rotationYaw = lvt_7_1_;
                this.entityLiving.rotationPitch = lvt_8_1_;
                this.entityLiving.prevRotationYawHead = lvt_9_1_;
                this.entityLiving.rotationYawHead = lvt_10_1_;
                GlStateManager.popMatrix();
                RenderHelper.disableStandardItemLighting();
                GlStateManager.disableRescaleNormal();
                GlStateManager.setActiveTexture((int)OpenGlHelper.lightmapTexUnit);
                GlStateManager.disableTexture2D();
                GlStateManager.setActiveTexture((int)OpenGlHelper.defaultTexUnit);
            }
        }
    }

    @Override
    public void afterDraw(Minecraft mc, int mouseX, int mouseY, int offsetX, int offsetY, double scaled) {
    }

    public boolean isMouseOver() {
        return this.hovered;
    }
}

