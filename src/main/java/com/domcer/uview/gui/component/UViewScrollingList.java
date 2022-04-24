// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.WorldRenderer
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package com.domcer.uview.gui.component;

import com.domcer.uview.gui.interfaces.UViewComponent;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class UViewScrollingList
extends Gui
implements UViewComponent {
    public final int screenWidth;
    public final int screenHeight;
    public final int entryHeight;
    public final int border;
    protected int top;
    protected int bottom;
    protected int right;
    protected int left;
    protected int mouseX;
    protected int mouseY;
    private float initialMouseClickY = -2.0f;
    private float scrollFactor;
    private float scrollDistance;
    private boolean scrollBarEnabled;
    public ResourceLocation backgroundLocation;
    public int width = 200;
    public int height = 20;
    public int xPosition;
    public int yPosition;
    public int id;
    private List<UViewComponent> components = new ArrayList<UViewComponent>();

    public UViewScrollingList(int id, int x, int y, int width, int height, int entryHeight, int screenWidth, int screenHeight, int border, ResourceLocation backgroundLocation) {
        this.id = id;
        this.xPosition = x;
        this.yPosition = y;
        this.width = width;
        this.height = height;
        this.entryHeight = entryHeight;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.border = border;
        this.backgroundLocation = backgroundLocation;
        this.scrollBarEnabled = true;
    }

    public int getSize() {
        return this.components.size();
    }

    public int getContentHeight() {
        return this.getSize() * this.entryHeight + 1;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getType() {
        return "UViewScrollingList";
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
        this.drawScreen(mc, mouseX, mouseY, offsetX, offsetY, scaled);
    }

    @Override
    public void draw(Minecraft mc, int mouseX, int mouseY, int offsetX, int offsetY, double scaled) {
        int baseY = this.top - (int)this.scrollDistance;
        int listLength = this.getSize();
        for (int slotIdx = 0; slotIdx < listLength; ++slotIdx) {
            int slotTop = baseY + slotIdx * this.entryHeight;
            int slotBuffer = this.entryHeight - this.border;
            if (slotTop > this.bottom || slotTop + slotBuffer < this.top) continue;
            UViewComponent component = this.components.get(slotIdx);
            component.draw(Minecraft.getMinecraft(), mouseX, mouseY, this.left + 3, slotTop + 2, scaled);
        }
    }

    @Override
    public void afterDraw(Minecraft mc, int mouseX, int mouseY, int offsetX, int offsetY, double scaled) {
        int baseY = this.top - (int)this.scrollDistance;
        int listLength = this.getSize();
        for (int slotIdx = 0; slotIdx < listLength; ++slotIdx) {
            int slotTop = baseY + slotIdx * this.entryHeight;
            int slotBuffer = this.entryHeight - this.border;
            if (slotTop > this.bottom || slotTop + slotBuffer < this.top) continue;
            UViewComponent component = this.components.get(slotIdx);
            component.afterDraw(Minecraft.getMinecraft(), mouseX, mouseY, this.left + 3, slotTop + 2, scaled);
        }
    }

    private void applyScrollLimits() {
        int listHeight = this.getContentHeight() - (this.bottom - this.top - 4);
        if (listHeight < 0) {
            listHeight /= 2;
        }
        if (this.scrollDistance < 0.0f) {
            this.scrollDistance = 0.0f;
        }
        if (this.scrollDistance > (float)listHeight) {
            this.scrollDistance = listHeight;
        }
    }

    public void drawScreen(Minecraft client, int mouseX, int mouseY, int offsetX, int offsetY, double scaled) {
        int extraHeight;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.left = this.xPosition + offsetX;
        this.right = this.left + this.width;
        this.top = this.yPosition + offsetY;
        this.bottom = this.top + this.height;
        boolean isHovering = mouseX >= this.left && mouseX <= this.left + this.width && mouseY >= this.top && mouseY <= this.bottom;
        int listLength = this.getSize();
        int scrollBarWidth = 6;
        int scrollBarRight = this.left + this.width;
        int scrollBarLeft = scrollBarRight - scrollBarWidth;
        int viewHeight = this.bottom - this.top;
        if (Mouse.isButtonDown((int)0)) {
            if (this.initialMouseClickY == -1.0f) {
                if (isHovering) {
                    if (mouseX >= scrollBarLeft && mouseX <= scrollBarRight) {
                        int var13;
                        this.scrollFactor = -1.0f;
                        int scrollHeight = this.getContentHeight() - viewHeight - this.border;
                        if (scrollHeight < 1) {
                            scrollHeight = 1;
                        }
                        if ((var13 = (int)((float)(viewHeight * viewHeight) / (float)this.getContentHeight())) < 32) {
                            var13 = 32;
                        }
                        if (var13 > viewHeight - this.border * 2) {
                            var13 = viewHeight - this.border * 2;
                        }
                        this.scrollFactor /= (float)(viewHeight - var13) / (float)scrollHeight;
                    } else {
                        this.scrollFactor = 1.0f;
                    }
                    this.initialMouseClickY = mouseY;
                } else {
                    this.initialMouseClickY = -2.0f;
                }
            } else if (this.initialMouseClickY >= 0.0f) {
                this.scrollDistance -= ((float)mouseY - this.initialMouseClickY) * this.scrollFactor;
                this.initialMouseClickY = mouseY;
            }
        } else {
            while (isHovering && Mouse.next()) {
                int scroll = Mouse.getEventDWheel();
                if (scroll == 0) continue;
                scroll = scroll > 0 ? -1 : 1;
                this.scrollDistance += (float)(scroll * this.entryHeight / 2);
            }
            this.initialMouseClickY = -1.0f;
        }
        this.applyScrollLimits();
        Tessellator tess = Tessellator.getInstance();
        WorldRenderer worldr = tess.getWorldRenderer();
        ScaledResolution res = new ScaledResolution(client);
        double scaleW = (double)client.displayWidth / res.getScaledWidth_double();
        double scaleH = (double)client.displayHeight / res.getScaledHeight_double();
        GL11.glEnable((int)3089);
        GL11.glScissor((int)((int)((double)this.left * scaleW)), (int)((int)((double)client.displayHeight - (double)this.bottom * scaleH)), (int)((int)((double)this.width * scaleW)), (int)((int)((double)viewHeight * scaleH)));
        if (this.backgroundLocation != null) {
            UViewScrollingList.drawModalRectWithCustomSizedTexture((int)this.left, (int)this.top, (float)0.0f, (float)0.0f, (int)this.width, (int)this.height, (float)this.width, (float)this.height);
        } else if (client.theWorld != null) {
            this.drawGradientRect(this.left, this.top, this.right, this.bottom, -1072689136, -804253680);
        } else {
            GlStateManager.disableLighting();
            GlStateManager.disableFog();
            client.renderEngine.bindTexture(Gui.optionsBackground);
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            float scale = 32.0f;
            worldr.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            worldr.pos((double)this.left, (double)this.bottom, 0.0).tex((double)((float)this.left / scale), (double)((float)(this.bottom + (int)this.scrollDistance) / scale)).color(32, 32, 32, 255).endVertex();
            worldr.pos((double)this.right, (double)this.bottom, 0.0).tex((double)((float)this.right / scale), (double)((float)(this.bottom + (int)this.scrollDistance) / scale)).color(32, 32, 32, 255).endVertex();
            worldr.pos((double)this.right, (double)this.top, 0.0).tex((double)((float)this.right / scale), (double)((float)(this.top + (int)this.scrollDistance) / scale)).color(32, 32, 32, 255).endVertex();
            worldr.pos((double)this.left, (double)this.top, 0.0).tex((double)((float)this.left / scale), (double)((float)(this.top + (int)this.scrollDistance) / scale)).color(32, 32, 32, 255).endVertex();
            tess.draw();
        }
        int baseY = this.top - (int)this.scrollDistance;
        for (int slotIdx = 0; slotIdx < listLength; ++slotIdx) {
            int slotTop = baseY + slotIdx * this.entryHeight;
            int slotBuffer = this.entryHeight - this.border;
            if (slotTop > this.bottom || slotTop + slotBuffer < this.top) continue;
            UViewComponent component = this.components.get(slotIdx);
            component.beforeDraw(Minecraft.getMinecraft(), mouseX, mouseY, this.left + 3, slotTop + 2, scaled);
        }
        GlStateManager.disableDepth();
        if (this.scrollBarEnabled && (extraHeight = this.getContentHeight() - viewHeight - this.border) > 0) {
            int barTop;
            int height = viewHeight * viewHeight / this.getContentHeight();
            if (height < 32) {
                height = 32;
            }
            if (height > viewHeight - this.border * 2) {
                height = viewHeight - this.border * 2;
            }
            if ((barTop = (int)this.scrollDistance * (viewHeight - height) / extraHeight + this.top) < this.top) {
                barTop = this.top;
            }
            GlStateManager.disableTexture2D();
            worldr.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            worldr.pos((double)scrollBarLeft, (double)this.bottom, 0.0).tex(0.0, 1.0).color(0, 0, 0, 255).endVertex();
            worldr.pos((double)scrollBarRight, (double)this.bottom, 0.0).tex(1.0, 1.0).color(0, 0, 0, 255).endVertex();
            worldr.pos((double)scrollBarRight, (double)this.top, 0.0).tex(1.0, 0.0).color(0, 0, 0, 255).endVertex();
            worldr.pos((double)scrollBarLeft, (double)this.top, 0.0).tex(0.0, 0.0).color(0, 0, 0, 255).endVertex();
            tess.draw();
            worldr.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            worldr.pos((double)scrollBarLeft, (double)(barTop + height), 0.0).tex(0.0, 1.0).color(128, 128, 128, 255).endVertex();
            worldr.pos((double)scrollBarRight, (double)(barTop + height), 0.0).tex(1.0, 1.0).color(128, 128, 128, 255).endVertex();
            worldr.pos((double)scrollBarRight, (double)barTop, 0.0).tex(1.0, 0.0).color(128, 128, 128, 255).endVertex();
            worldr.pos((double)scrollBarLeft, (double)barTop, 0.0).tex(0.0, 0.0).color(128, 128, 128, 255).endVertex();
            tess.draw();
            worldr.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            worldr.pos((double)scrollBarLeft, (double)(barTop + height - 1), 0.0).tex(0.0, 1.0).color(192, 192, 192, 255).endVertex();
            worldr.pos((double)(scrollBarRight - 1), (double)(barTop + height - 1), 0.0).tex(1.0, 1.0).color(192, 192, 192, 255).endVertex();
            worldr.pos((double)(scrollBarRight - 1), (double)barTop, 0.0).tex(1.0, 0.0).color(192, 192, 192, 255).endVertex();
            worldr.pos((double)scrollBarLeft, (double)barTop, 0.0).tex(0.0, 0.0).color(192, 192, 192, 255).endVertex();
            tess.draw();
        }
        GlStateManager.enableTexture2D();
        GlStateManager.shadeModel((int)7424);
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
        GL11.glDisable((int)3089);
    }

    protected void drawGradientRect(int left, int top, int right, int bottom, int color1, int color2) {
        float a1 = (float)(color1 >> 24 & 0xFF) / 255.0f;
        float r1 = (float)(color1 >> 16 & 0xFF) / 255.0f;
        float g1 = (float)(color1 >> 8 & 0xFF) / 255.0f;
        float b1 = (float)(color1 & 0xFF) / 255.0f;
        float a2 = (float)(color2 >> 24 & 0xFF) / 255.0f;
        float r2 = (float)(color2 >> 16 & 0xFF) / 255.0f;
        float g2 = (float)(color2 >> 8 & 0xFF) / 255.0f;
        float b2 = (float)(color2 & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        GlStateManager.shadeModel((int)7425);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldrenderer.pos((double)right, (double)top, 0.0).color(r1, g1, b1, a1).endVertex();
        worldrenderer.pos((double)left, (double)top, 0.0).color(r1, g1, b1, a1).endVertex();
        worldrenderer.pos((double)left, (double)bottom, 0.0).color(r2, g2, b2, a2).endVertex();
        worldrenderer.pos((double)right, (double)bottom, 0.0).color(r2, g2, b2, a2).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel((int)7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public List<UViewComponent> getComponents() {
        return this.components;
    }

    public void addComponent(UViewComponent component) {
        this.components.add(component);
    }
}

