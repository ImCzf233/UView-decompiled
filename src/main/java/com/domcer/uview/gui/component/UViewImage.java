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
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.domcer.uview.gui.component;

import com.domcer.uview.gui.interfaces.UViewComponent;
import com.domcer.uview.gui.interfaces.UViewHoverTextComponent;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class UViewImage
extends Gui
implements UViewComponent,
UViewHoverTextComponent {
    public ResourceLocation imageLocation;
    public int width = 200;
    public int height = 20;
    public int xPosition;
    public int yPosition;
    public int id;
    public boolean visible = true;
    protected boolean hovered;
    private List<String> hoverText;

    public UViewImage(int id, int x, int y, ResourceLocation imageLocation) {
        this(id, x, y, 64, 64, imageLocation);
    }

    public UViewImage(int id, int x, int y, int widthIn, int heightIn, ResourceLocation imageLocation) {
        this.id = id;
        this.xPosition = x;
        this.yPosition = y;
        this.width = widthIn;
        this.height = heightIn;
        this.imageLocation = imageLocation;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getType() {
        return "UViewImage";
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
        if (this.visible) {
            mc.getTextureManager().bindTexture(this.imageLocation);
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.hovered = (double)mouseX >= (double)this.xPosition * scaled + (double)offsetX && (double)mouseY >= (double)this.yPosition * scaled + (double)offsetY && (double)mouseX < (double)this.xPosition * scaled + (double)offsetX + (double)this.width * scaled && (double)mouseY < (double)this.yPosition * scaled + (double)offsetY + (double)this.height * scaled;
            UViewImage.drawModalRectWithCustomSizedTexture((int)((int)((double)this.xPosition * scaled + (double)offsetX)), (int)((int)((double)this.yPosition * scaled + (double)offsetY)), (float)0.0f, (float)0.0f, (int)((int)((double)this.width * scaled)), (int)((int)((double)this.height * scaled)), (float)((int)((double)this.width * scaled)), (float)((int)((double)this.height * scaled)));
        }
    }

    @Override
    public void draw(Minecraft mc, int mouseX, int mouseY, int offsetX, int offsetY, double scaled) {
        if (this.isMouseOver() && this.hoverText != null && !this.hoverText.isEmpty()) {
            GlStateManager.disableRescaleNormal();
            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            int i = 0;
            for (String s : this.hoverText) {
                int j = mc.fontRendererObj.getStringWidth(s);
                if (j <= i) continue;
                i = j;
            }
            int l1 = mouseX + 12;
            int i2 = mouseY - 12;
            int k = 8;
            if (this.hoverText.size() > 1) {
                k += 2 + (this.hoverText.size() - 1) * 10;
            }
            this.zLevel = 300.0f;
            int l = -267386864;
            this.drawGradientRect(l1 - 3, i2 - 4, l1 + i + 3, i2 - 3, l, l);
            this.drawGradientRect(l1 - 3, i2 + k + 3, l1 + i + 3, i2 + k + 4, l, l);
            this.drawGradientRect(l1 - 3, i2 - 3, l1 + i + 3, i2 + k + 3, l, l);
            this.drawGradientRect(l1 - 4, i2 - 3, l1 - 3, i2 + k + 3, l, l);
            this.drawGradientRect(l1 + i + 3, i2 - 3, l1 + i + 4, i2 + k + 3, l, l);
            int i1 = 0x505000FF;
            int j1 = (i1 & 0xFEFEFE) >> 1 | i1 & 0xFF000000;
            this.drawGradientRect(l1 - 3, i2 - 3 + 1, l1 - 3 + 1, i2 + k + 3 - 1, i1, j1);
            this.drawGradientRect(l1 + i + 2, i2 - 3 + 1, l1 + i + 3, i2 + k + 3 - 1, i1, j1);
            this.drawGradientRect(l1 - 3, i2 - 3, l1 + i + 3, i2 - 3 + 1, i1, i1);
            this.drawGradientRect(l1 - 3, i2 + k + 2, l1 + i + 3, i2 + k + 3, j1, j1);
            for (int k1 = 0; k1 < this.hoverText.size(); ++k1) {
                String s1 = this.hoverText.get(k1);
                mc.fontRendererObj.drawStringWithShadow(s1, (float)l1, (float)i2, -1);
                if (k1 == 0) {
                    i2 += 2;
                }
                i2 += 10;
            }
            this.zLevel = 0.0f;
            GlStateManager.enableLighting();
            GlStateManager.enableDepth();
            RenderHelper.enableStandardItemLighting();
            GlStateManager.enableRescaleNormal();
        }
    }

    @Override
    public void afterDraw(Minecraft mc, int mouseX, int mouseY, int offsetX, int offsetY, double scaled) {
    }

    @Override
    public List<String> getHoverText() {
        return this.hoverText;
    }

    @Override
    public void setHoverText(List<String> hoverText) {
        this.hoverText = hoverText;
    }

    public boolean isMouseOver() {
        return this.hovered;
    }
}

