// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.client.audio.SoundHandler
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.util.ResourceLocation
 */
package com.domcer.uview.gui.component;

import com.domcer.uview.gui.interfaces.UViewComponent;
import com.domcer.uview.gui.interfaces.UViewHoverTextComponent;
import com.domcer.uview.gui.interfaces.UViewOpenUrlComponent;
import com.domcer.uview.messager.UViewMessager;
import com.domcer.uview.packet.packets.PacketButtonClick;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;

public class UViewButton
extends Gui
implements UViewComponent,
UViewHoverTextComponent,
UViewOpenUrlComponent {
    protected static final ResourceLocation buttonTextures = new ResourceLocation("textures/gui/widgets.png");
    public int width;
    public int height;
    public int xPosition;
    public int yPosition;
    public String displayString;
    public int id;
    public boolean enabled;
    public boolean visible;
    protected boolean hovered;
    public int packedFGColour;
    public ResourceLocation imageLocation;
    public ResourceLocation imageHoverLocation;
    public ResourceLocation soundLocation;
    public boolean lastHovered;
    private List<String> hoverText;
    private String openUrl;

    public UViewButton(int buttonId, int x, int y, String buttonText) {
        this(buttonId, x, y, 100, 20, buttonText);
    }

    public UViewButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
        this(buttonId, x, y, widthIn, heightIn, buttonText, null, null, null);
    }

    public UViewButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, ResourceLocation imageLocation, ResourceLocation imageHoverLocation, ResourceLocation soundLocation) {
        this.id = buttonId;
        this.xPosition = x;
        this.yPosition = y;
        this.width = widthIn;
        this.height = heightIn;
        this.displayString = buttonText;
        this.imageLocation = imageLocation;
        this.imageHoverLocation = imageHoverLocation;
        this.soundLocation = soundLocation;
        this.enabled = true;
        this.visible = true;
    }

    protected int getHoverState(boolean mouseOver) {
        int i = 1;
        if (!this.enabled) {
            i = 0;
        } else if (mouseOver) {
            i = 2;
        }
        return i;
    }

    @Override
    public void beforeDraw(Minecraft mc, int mouseX, int mouseY, int offsetX, int offsetY, double scaled) {
        if (this.imageLocation != null && !this.isMouseOver()) {
            if (this.visible) {
                mc.getTextureManager().bindTexture(this.imageLocation);
                GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                this.hovered = (double)mouseX >= (double)this.xPosition * scaled + (double)offsetX && (double)mouseY >= (double)this.yPosition * scaled + (double)offsetY && (double)mouseX < (double)this.xPosition * scaled + (double)offsetX + (double)this.width * scaled && (double)mouseY < (double)this.yPosition * scaled + (double)offsetY + (double)this.height * scaled;
                UViewButton.drawModalRectWithCustomSizedTexture((int)((int)((double)this.xPosition * scaled + (double)offsetX)), (int)((int)((double)this.yPosition * scaled + (double)offsetY)), (float)0.0f, (float)0.0f, (int)((int)((double)this.width * scaled)), (int)((int)((double)this.height * scaled)), (float)((int)((double)this.width * scaled)), (float)((int)((double)this.height * scaled)));
                this.mouseDragged(mc, mouseX, mouseY);
                if (this.displayString != null) {
                    int j = 0xE0E0E0;
                    if (this.packedFGColour != 0) {
                        j = this.packedFGColour;
                    } else if (!this.enabled) {
                        j = 0xA0A0A0;
                    } else if (this.hovered) {
                        j = 0xFFFFA0;
                    }
                    this.drawCenteredString(mc.fontRendererObj, this.displayString, (int)((double)this.xPosition * scaled + (double)this.width * scaled / 2.0 + (double)offsetX), (int)((double)this.yPosition * scaled + ((double)this.height * scaled - 8.0) / 2.0 + (double)offsetY), j);
                }
            }
        } else if (this.imageHoverLocation != null && this.isMouseOver()) {
            if (this.visible) {
                mc.getTextureManager().bindTexture(this.imageHoverLocation);
                GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                this.hovered = (double)mouseX >= (double)this.xPosition * scaled + (double)offsetX && (double)mouseY >= (double)this.yPosition * scaled + (double)offsetY && (double)mouseX < (double)this.xPosition * scaled + (double)offsetX + (double)this.width * scaled && (double)mouseY < (double)this.yPosition * scaled + (double)offsetY + (double)this.height * scaled;
                UViewButton.drawModalRectWithCustomSizedTexture((int)((int)((double)this.xPosition * scaled + (double)offsetX)), (int)((int)((double)this.yPosition * scaled + (double)offsetY)), (float)0.0f, (float)0.0f, (int)((int)((double)this.width * scaled)), (int)((int)((double)this.height * scaled)), (float)((int)((double)this.width * scaled)), (float)((int)((double)this.height * scaled)));
                this.mouseDragged(mc, mouseX, mouseY);
                if (this.displayString != null) {
                    int j = 0xE0E0E0;
                    if (this.packedFGColour != 0) {
                        j = this.packedFGColour;
                    } else if (!this.enabled) {
                        j = 0xA0A0A0;
                    } else if (this.hovered) {
                        j = 0xFFFFA0;
                    }
                    this.drawCenteredString(mc.fontRendererObj, this.displayString, (int)((double)this.xPosition * scaled + (double)this.width * scaled / 2.0 + (double)offsetX), (int)((double)this.yPosition * scaled + ((double)this.height * scaled - 8.0) / 2.0 + (double)offsetY), j);
                }
            }
        } else if (this.visible && this.imageLocation == null) {
            FontRenderer fontrenderer = mc.fontRendererObj;
            mc.getTextureManager().bindTexture(buttonTextures);
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.hovered = (double)mouseX >= (double)this.xPosition * scaled + (double)offsetX && (double)mouseY >= (double)this.yPosition * scaled + (double)offsetY && (double)mouseX < (double)this.xPosition * scaled + (double)offsetX + (double)this.width * scaled && (double)mouseY < (double)this.yPosition * scaled + (double)offsetY + (double)this.height * scaled;
            int i = this.getHoverState(this.hovered);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
            GlStateManager.blendFunc((int)770, (int)771);
            this.drawTexturedModalRect((int)((double)this.xPosition * scaled + (double)offsetX), (int)((double)this.yPosition * scaled + (double)offsetY), 0, 46 + i * 20, (int)((double)this.width * scaled / 2.0), (int)((double)this.height * scaled));
            this.drawTexturedModalRect((int)((double)this.xPosition * scaled + (double)this.width * scaled / 2.0 + (double)offsetX), (int)((double)this.yPosition * scaled + (double)offsetY), (int)(200.0 - (double)this.width * scaled / 2.0), 46 + i * 20, (int)((double)this.width * scaled / 2.0), (int)((double)this.height * scaled));
            this.mouseDragged(mc, mouseX, mouseY);
            int j = 0xE0E0E0;
            if (this.displayString != null) {
                if (this.packedFGColour != 0) {
                    j = this.packedFGColour;
                } else if (!this.enabled) {
                    j = 0xA0A0A0;
                } else if (this.hovered) {
                    j = 0xFFFFA0;
                }
                this.drawCenteredString(fontrenderer, this.displayString, (int)((double)this.xPosition * scaled + (double)this.width * scaled / 2.0 + (double)offsetX), (int)((double)this.yPosition * scaled + ((double)this.height * scaled - 8.0) / 2.0 + (double)offsetY), j);
            }
        }
    }

    @Override
    public void draw(Minecraft mc, int mouseX, int mouseY, int offsetX, int offsetY, double scaled) {
        if (this.isMouseOver()) {
            this.lastHovered = true;
            if (this.hoverText != null && !this.hoverText.isEmpty()) {
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
        } else {
            this.lastHovered = false;
        }
    }

    @Override
    public void afterDraw(Minecraft mc, int mouseX, int mouseY, int offsetX, int offsetY, double scaled) {
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getType() {
        return "UViewButton";
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
    public List<String> getHoverText() {
        return this.hoverText;
    }

    @Override
    public void setHoverText(List<String> hoverText) {
        this.hoverText = hoverText;
    }

    protected void mouseDragged(Minecraft mc, int mouseX, int mouseY) {
    }

    public void mouseReleased(int mouseX, int mouseY) {
    }

    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY, int offsetX, int offsetY, double scaled) {
        return this.enabled && this.visible && (double)mouseX >= (double)this.xPosition * scaled + (double)offsetX && (double)mouseY >= (double)this.yPosition * scaled + (double)offsetY && (double)mouseX < (double)this.xPosition * scaled + (double)offsetX + (double)this.width * scaled && (double)mouseY < (double)this.yPosition * scaled + (double)offsetY + (double)this.height * scaled;
    }

    public boolean isMouseOver() {
        return this.hovered;
    }

    public void playPressSound(SoundHandler soundHandlerIn) {
        if (this.soundLocation != null) {
            soundHandlerIn.playSound((ISound)PositionedSoundRecord.create((ResourceLocation)this.soundLocation, (float)1.0f));
        } else {
            soundHandlerIn.playSound((ISound)PositionedSoundRecord.create((ResourceLocation)new ResourceLocation("gui.button.press"), (float)1.0f));
        }
    }

    public void onClick() {
        PacketButtonClick packet = new PacketButtonClick();
        packet.buttonId = this.id;
        UViewMessager.sendPacket(packet);
    }

    @Override
    public String getOpenUrl() {
        return this.openUrl;
    }

    @Override
    public void setOpenUrl(String openUrl) {
        this.openUrl = openUrl;
    }
}

