// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  com.google.common.base.Predicates
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.WorldRenderer
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.util.ChatAllowedCharacters
 *  net.minecraft.util.MathHelper
 */
package com.domcer.uview.gui.component;

import com.domcer.uview.gui.interfaces.UViewComponent;
import com.domcer.uview.messager.UViewMessager;
import com.domcer.uview.packet.packets.PacketTextFieldUpdate;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.MathHelper;

public class UViewTextField
extends Gui
implements UViewComponent {
    private final int id;
    private final FontRenderer fontRenderer;
    public int xPosition;
    public int yPosition;
    public int width;
    public int height;
    private String text = "";
    private int maxStringLength = 32;
    private int cursorCounter;
    private boolean enableBackgroundDrawing = true;
    private boolean focused;
    private int lineScrollOffset;
    private int cursorPosition;
    private int selectionEnd;
    private int enabledColor = 0xE0E0E0;
    private Predicate<String> predicate = Predicates.alwaysTrue();

    public UViewTextField(int id, FontRenderer fontrendererObj, int x, int y) {
        this(id, fontrendererObj, x, y, 100, 20);
    }

    public UViewTextField(int id, FontRenderer fontrendererObj, int x, int y, int width, int height) {
        this.id = id;
        this.fontRenderer = fontrendererObj;
        this.xPosition = x;
        this.yPosition = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void beforeDraw(Minecraft mc, int mouseX, int mouseY, int offsetX, int offsetY, double scaled) {
        this.drawTextBox(offsetX, offsetY, scaled);
    }

    @Override
    public void draw(Minecraft mc, int mouseX, int mouseY, int offsetX, int offsetY, double scaled) {
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
        return "UViewTextField";
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
        return this.getEnableBackgroundDrawing() ? this.width - 8 : this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    public void updateCursorCounter() {
        ++this.cursorCounter;
    }

    public void setText(String text) {
        if (this.predicate.apply((String) text)) {
            this.text = text.length() > this.maxStringLength ? text.substring(0, this.maxStringLength) : text;
            this.setCursorPositionEnd();
        }
    }

    public String getText() {
        return this.text;
    }

    public String getSelectedText() {
        int lvt_1_1_ = Math.min(this.cursorPosition, this.selectionEnd);
        int lvt_2_1_ = Math.max(this.cursorPosition, this.selectionEnd);
        return this.text.substring(lvt_1_1_, lvt_2_1_);
    }

    public void func_175205_a(Predicate<String> predicate) {
        this.predicate = predicate;
    }

    public void writeText(String p_writeText_1_) {
        int lvt_7_1_;
        String lvt_2_1_ = "";
        String lvt_3_1_ = ChatAllowedCharacters.filterAllowedCharacters((String)p_writeText_1_);
        int lvt_4_1_ = Math.min(this.cursorPosition, this.selectionEnd);
        int lvt_5_1_ = Math.max(this.cursorPosition, this.selectionEnd);
        int lvt_6_1_ = this.maxStringLength - this.text.length() - (lvt_4_1_ - lvt_5_1_);
        if (this.text.length() > 0) {
            lvt_2_1_ = lvt_2_1_ + this.text.substring(0, lvt_4_1_);
        }
        if (lvt_6_1_ < lvt_3_1_.length()) {
            lvt_2_1_ = lvt_2_1_ + lvt_3_1_.substring(0, lvt_6_1_);
            lvt_7_1_ = lvt_6_1_;
        } else {
            lvt_2_1_ = lvt_2_1_ + lvt_3_1_;
            lvt_7_1_ = lvt_3_1_.length();
        }
        if (this.text.length() > 0 && lvt_5_1_ < this.text.length()) {
            lvt_2_1_ = lvt_2_1_ + this.text.substring(lvt_5_1_);
        }
        if (this.predicate.apply((String) lvt_2_1_)) {
            this.text = lvt_2_1_;
            this.moveCursorBy(lvt_4_1_ - this.selectionEnd + lvt_7_1_);
        }
    }

    public void deleteWords(int p_deleteWords_1_) {
        if (this.text.length() != 0) {
            if (this.selectionEnd != this.cursorPosition) {
                this.writeText("");
            } else {
                this.deleteFromCursor(this.getNthWordFromCursor(p_deleteWords_1_) - this.cursorPosition);
            }
        }
    }

    public void deleteFromCursor(int p_deleteFromCursor_1_) {
        if (this.text.length() != 0) {
            if (this.selectionEnd != this.cursorPosition) {
                this.writeText("");
            } else {
                boolean lvt_2_1_ = p_deleteFromCursor_1_ < 0;
                int lvt_3_1_ = lvt_2_1_ ? this.cursorPosition + p_deleteFromCursor_1_ : this.cursorPosition;
                int lvt_4_1_ = lvt_2_1_ ? this.cursorPosition : this.cursorPosition + p_deleteFromCursor_1_;
                String lvt_5_1_ = "";
                if (lvt_3_1_ >= 0) {
                    lvt_5_1_ = this.text.substring(0, lvt_3_1_);
                }
                if (lvt_4_1_ < this.text.length()) {
                    lvt_5_1_ = lvt_5_1_ + this.text.substring(lvt_4_1_);
                }
                if (this.predicate.apply((String) lvt_5_1_)) {
                    this.text = lvt_5_1_;
                    if (lvt_2_1_) {
                        this.moveCursorBy(p_deleteFromCursor_1_);
                    }
                }
            }
        }
    }

    public int getNthWordFromCursor(int p_getNthWordFromCursor_1_) {
        return this.getNthWordFromPos(p_getNthWordFromCursor_1_, this.getCursorPosition());
    }

    public int getNthWordFromPos(int p_getNthWordFromPos_1_, int p_getNthWordFromPos_2_) {
        return this.func_146197_a(p_getNthWordFromPos_1_, p_getNthWordFromPos_2_, true);
    }

    public int func_146197_a(int p_146197_1_, int p_146197_2_, boolean p_146197_3_) {
        int lvt_4_1_ = p_146197_2_;
        boolean lvt_5_1_ = p_146197_1_ < 0;
        int lvt_6_1_ = Math.abs(p_146197_1_);
        for (int lvt_7_1_ = 0; lvt_7_1_ < lvt_6_1_; ++lvt_7_1_) {
            if (!lvt_5_1_) {
                int lvt_8_1_ = this.text.length();
                if ((lvt_4_1_ = this.text.indexOf(32, lvt_4_1_)) == -1) {
                    lvt_4_1_ = lvt_8_1_;
                    continue;
                }
                while (p_146197_3_ && lvt_4_1_ < lvt_8_1_ && this.text.charAt(lvt_4_1_) == ' ') {
                    ++lvt_4_1_;
                }
                continue;
            }
            while (p_146197_3_ && lvt_4_1_ > 0 && this.text.charAt(lvt_4_1_ - 1) == ' ') {
                --lvt_4_1_;
            }
            while (lvt_4_1_ > 0 && this.text.charAt(lvt_4_1_ - 1) != ' ') {
                --lvt_4_1_;
            }
        }
        return lvt_4_1_;
    }

    public void moveCursorBy(int p_moveCursorBy_1_) {
        this.setCursorPosition(this.selectionEnd + p_moveCursorBy_1_);
    }

    public void setCursorPosition(int p_setCursorPosition_1_) {
        this.cursorPosition = p_setCursorPosition_1_;
        int lvt_2_1_ = this.text.length();
        this.cursorPosition = MathHelper.clamp_int((int)this.cursorPosition, (int)0, (int)lvt_2_1_);
        this.setSelectionPos(this.cursorPosition);
    }

    public void setCursorPositionZero() {
        this.setCursorPosition(0);
    }

    public void setCursorPositionEnd() {
        this.setCursorPosition(this.text.length());
    }

    public boolean textboxKeyTyped(char p_textboxKeyTyped_1_, int p_textboxKeyTyped_2_) {
        if (!this.focused) {
            return false;
        }
        if (GuiScreen.isKeyComboCtrlA((int)p_textboxKeyTyped_2_)) {
            this.setCursorPositionEnd();
            this.setSelectionPos(0);
            return true;
        }
        if (GuiScreen.isKeyComboCtrlC((int)p_textboxKeyTyped_2_)) {
            GuiScreen.setClipboardString((String)this.getSelectedText());
            return true;
        }
        if (GuiScreen.isKeyComboCtrlV((int)p_textboxKeyTyped_2_)) {
            this.writeText(GuiScreen.getClipboardString());
            this.sendUpdatePacket();
            return true;
        }
        if (GuiScreen.isKeyComboCtrlX((int)p_textboxKeyTyped_2_)) {
            GuiScreen.setClipboardString((String)this.getSelectedText());
            this.writeText("");
            this.sendUpdatePacket();
            return true;
        }
        switch (p_textboxKeyTyped_2_) {
            case 14: {
                if (GuiScreen.isCtrlKeyDown()) {
                    this.deleteWords(-1);
                } else {
                    this.deleteFromCursor(-1);
                }
                this.sendUpdatePacket();
                return true;
            }
            case 199: {
                if (GuiScreen.isShiftKeyDown()) {
                    this.setSelectionPos(0);
                } else {
                    this.setCursorPositionZero();
                }
                return true;
            }
            case 203: {
                if (GuiScreen.isShiftKeyDown()) {
                    if (GuiScreen.isCtrlKeyDown()) {
                        this.setSelectionPos(this.getNthWordFromPos(-1, this.getSelectionEnd()));
                    } else {
                        this.setSelectionPos(this.getSelectionEnd() - 1);
                    }
                } else if (GuiScreen.isCtrlKeyDown()) {
                    this.setCursorPosition(this.getNthWordFromCursor(-1));
                } else {
                    this.moveCursorBy(-1);
                }
                return true;
            }
            case 205: {
                if (GuiScreen.isShiftKeyDown()) {
                    if (GuiScreen.isCtrlKeyDown()) {
                        this.setSelectionPos(this.getNthWordFromPos(1, this.getSelectionEnd()));
                    } else {
                        this.setSelectionPos(this.getSelectionEnd() + 1);
                    }
                } else if (GuiScreen.isCtrlKeyDown()) {
                    this.setCursorPosition(this.getNthWordFromCursor(1));
                } else {
                    this.moveCursorBy(1);
                }
                return true;
            }
            case 207: {
                if (GuiScreen.isShiftKeyDown()) {
                    this.setSelectionPos(this.text.length());
                } else {
                    this.setCursorPositionEnd();
                }
                return true;
            }
            case 211: {
                if (GuiScreen.isCtrlKeyDown()) {
                    this.deleteWords(1);
                } else {
                    this.deleteFromCursor(1);
                }
                this.sendUpdatePacket();
                return true;
            }
        }
        if (ChatAllowedCharacters.isAllowedCharacter((char)p_textboxKeyTyped_1_)) {
            this.writeText(Character.toString(p_textboxKeyTyped_1_));
            this.sendUpdatePacket();
            return true;
        }
        return false;
    }

    public void sendUpdatePacket() {
        PacketTextFieldUpdate packet = new PacketTextFieldUpdate();
        packet.id = this.id;
        packet.text = this.text;
        UViewMessager.sendPacket(packet);
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton, int offsetX, int offsetY, double scaled) {
        boolean lvt_4_1_;
        boolean bl = lvt_4_1_ = (double)mouseX >= (double)this.xPosition * scaled + (double)offsetX && (double)mouseX < (double)this.xPosition * scaled + (double)offsetX + (double)this.width * scaled && (double)mouseY >= (double)this.yPosition * scaled + (double)offsetY && (double)mouseY < (double)this.yPosition * scaled + (double)offsetY + (double)this.height * scaled;
        if (this.focused && lvt_4_1_ && mouseButton == 0) {
            int lvt_5_1_ = (int)((double)mouseX - ((double)this.xPosition * scaled + (double)offsetX));
            if (this.enableBackgroundDrawing) {
                lvt_5_1_ -= 4;
            }
            String lvt_6_1_ = this.fontRenderer.trimStringToWidth(this.text.substring(this.lineScrollOffset), this.getWidth());
            this.setCursorPosition(this.fontRenderer.trimStringToWidth(lvt_6_1_, lvt_5_1_).length() + this.lineScrollOffset);
        } else if (!this.focused && mouseButton == 0) {
            this.setFocused(true);
        }
    }

    public void drawTextBox(int offsetX, int offsetY, double scaled) {
        if (this.getEnableBackgroundDrawing()) {
            UViewTextField.drawRect((int)((int)((double)this.xPosition * scaled + (double)offsetX - 1.0)), (int)((int)((double)this.yPosition * scaled + (double)offsetY - 1.0)), (int)((int)((double)this.xPosition * scaled + (double)offsetX + (double)this.width * scaled + 1.0)), (int)((int)((double)this.yPosition * scaled + (double)offsetY + (double)this.height * scaled + 1.0)), (int)-6250336);
            UViewTextField.drawRect((int)((int)((double)this.xPosition * scaled + (double)offsetX)), (int)((int)((double)this.yPosition * scaled + (double)offsetY)), (int)((int)((double)this.xPosition * scaled + (double)offsetX + (double)this.width * scaled)), (int)((int)((double)this.yPosition * scaled + (double)offsetY + (double)this.height * scaled)), (int)-16777216);
        }
        int lvt_1_1_ = this.enabledColor;
        int lvt_2_1_ = this.cursorPosition - this.lineScrollOffset;
        int lvt_3_1_ = this.selectionEnd - this.lineScrollOffset;
        String lvt_4_1_ = this.fontRenderer.trimStringToWidth(this.text.substring(this.lineScrollOffset), this.getWidth());
        boolean lvt_5_1_ = lvt_2_1_ >= 0 && lvt_2_1_ <= lvt_4_1_.length();
        boolean lvt_6_1_ = this.focused && this.cursorCounter / 6 % 2 == 0 && lvt_5_1_;
        int lvt_7_1_ = (int)(this.enableBackgroundDrawing ? (double)this.xPosition * scaled + (double)offsetX + 4.0 : (double)this.xPosition * scaled + (double)offsetX);
        int lvt_8_1_ = (int)(this.enableBackgroundDrawing ? (double)this.yPosition * scaled + (double)offsetY + ((double)this.height * scaled - 8.0) / 2.0 : (double)this.yPosition * scaled + (double)offsetY);
        int lvt_9_1_ = lvt_7_1_;
        if (lvt_3_1_ > lvt_4_1_.length()) {
            lvt_3_1_ = lvt_4_1_.length();
        }
        if (lvt_4_1_.length() > 0) {
            String lvt_10_1_ = lvt_5_1_ ? lvt_4_1_.substring(0, lvt_2_1_) : lvt_4_1_;
            lvt_9_1_ = this.fontRenderer.drawStringWithShadow(lvt_10_1_, (float)lvt_7_1_, (float)lvt_8_1_, lvt_1_1_);
        }
        boolean lvt_10_2_ = this.cursorPosition < this.text.length() || this.text.length() >= this.getMaxStringLength();
        int lvt_11_1_ = lvt_9_1_;
        if (!lvt_5_1_) {
            lvt_11_1_ = lvt_2_1_ > 0 ? (int)((double)lvt_7_1_ + (double)this.width * scaled) : lvt_7_1_;
        } else if (lvt_10_2_) {
            lvt_11_1_ = lvt_9_1_ - 1;
            --lvt_9_1_;
        }
        if (lvt_4_1_.length() > 0 && lvt_5_1_ && lvt_2_1_ < lvt_4_1_.length()) {
            lvt_9_1_ = this.fontRenderer.drawStringWithShadow(lvt_4_1_.substring(lvt_2_1_), (float)lvt_9_1_, (float)lvt_8_1_, lvt_1_1_);
        }
        if (lvt_6_1_) {
            if (lvt_10_2_) {
                Gui.drawRect((int)lvt_11_1_, (int)(lvt_8_1_ - 1), (int)(lvt_11_1_ + 1), (int)(lvt_8_1_ + 1 + this.fontRenderer.FONT_HEIGHT), (int)-3092272);
            } else {
                this.fontRenderer.drawStringWithShadow("_", (float)lvt_11_1_, (float)lvt_8_1_, lvt_1_1_);
            }
        }
        if (lvt_3_1_ != lvt_2_1_) {
            int lvt_12_1_ = lvt_7_1_ + this.fontRenderer.getStringWidth(lvt_4_1_.substring(0, lvt_3_1_));
            this.drawCursorVertical(lvt_11_1_, lvt_8_1_ - 1, lvt_12_1_ - 1, lvt_8_1_ + 1 + this.fontRenderer.FONT_HEIGHT, offsetX, offsetY, scaled);
        }
    }

    private void drawCursorVertical(int p_drawCursorVertical_1_, int p_drawCursorVertical_2_, int p_drawCursorVertical_3_, int p_drawCursorVertical_4_, int offsetX, int offsetY, double scaled) {
        int lvt_5_2_;
        if (p_drawCursorVertical_1_ < p_drawCursorVertical_3_) {
            lvt_5_2_ = p_drawCursorVertical_1_;
            p_drawCursorVertical_1_ = p_drawCursorVertical_3_;
            p_drawCursorVertical_3_ = lvt_5_2_;
        }
        if (p_drawCursorVertical_2_ < p_drawCursorVertical_4_) {
            lvt_5_2_ = p_drawCursorVertical_2_;
            p_drawCursorVertical_2_ = p_drawCursorVertical_4_;
            p_drawCursorVertical_4_ = lvt_5_2_;
        }
        if ((double)p_drawCursorVertical_3_ > (double)this.xPosition * scaled + (double)offsetX + (double)this.width * scaled) {
            p_drawCursorVertical_3_ = (int)((double)this.xPosition * scaled + (double)offsetX + (double)this.width * scaled);
        }
        if ((double)p_drawCursorVertical_1_ > (double)this.xPosition * scaled + (double)offsetX + (double)this.width * scaled) {
            p_drawCursorVertical_1_ = (int)((double)this.xPosition * scaled + (double)offsetX + (double)this.width * scaled);
        }
        Tessellator lvt_5_3_ = Tessellator.getInstance();
        WorldRenderer lvt_6_1_ = lvt_5_3_.getWorldRenderer();
        GlStateManager.color((float)0.0f, (float)0.0f, (float)255.0f, (float)255.0f);
        GlStateManager.disableTexture2D();
        GlStateManager.enableColorLogic();
        GlStateManager.colorLogicOp((int)5387);
        lvt_6_1_.begin(7, DefaultVertexFormats.POSITION);
        lvt_6_1_.pos((double)p_drawCursorVertical_1_, (double)p_drawCursorVertical_4_, 0.0).endVertex();
        lvt_6_1_.pos((double)p_drawCursorVertical_3_, (double)p_drawCursorVertical_4_, 0.0).endVertex();
        lvt_6_1_.pos((double)p_drawCursorVertical_3_, (double)p_drawCursorVertical_2_, 0.0).endVertex();
        lvt_6_1_.pos((double)p_drawCursorVertical_1_, (double)p_drawCursorVertical_2_, 0.0).endVertex();
        lvt_5_3_.draw();
        GlStateManager.disableColorLogic();
        GlStateManager.enableTexture2D();
    }

    public void setMaxStringLength(int p_setMaxStringLength_1_) {
        this.maxStringLength = p_setMaxStringLength_1_;
        if (this.text.length() > p_setMaxStringLength_1_) {
            this.text = this.text.substring(0, p_setMaxStringLength_1_);
        }
    }

    public int getMaxStringLength() {
        return this.maxStringLength;
    }

    public int getCursorPosition() {
        return this.cursorPosition;
    }

    public boolean getEnableBackgroundDrawing() {
        return this.enableBackgroundDrawing;
    }

    public void setEnableBackgroundDrawing(boolean enableBackgroundDrawing) {
        this.enableBackgroundDrawing = enableBackgroundDrawing;
    }

    public void setTextColor(int textColor) {
        this.enabledColor = textColor;
    }

    public void setFocused(boolean focused) {
        if (focused && !this.focused) {
            this.cursorCounter = 0;
        }
        this.focused = focused;
    }

    public boolean isFocused() {
        return this.focused;
    }

    public int getSelectionEnd() {
        return this.selectionEnd;
    }

    public void setSelectionPos(int p_setSelectionPos_1_) {
        int lvt_2_1_ = this.text.length();
        if (p_setSelectionPos_1_ > lvt_2_1_) {
            p_setSelectionPos_1_ = lvt_2_1_;
        }
        if (p_setSelectionPos_1_ < 0) {
            p_setSelectionPos_1_ = 0;
        }
        this.selectionEnd = p_setSelectionPos_1_;
        if (this.fontRenderer != null) {
            if (this.lineScrollOffset > lvt_2_1_) {
                this.lineScrollOffset = lvt_2_1_;
            }
            int lvt_3_1_ = this.getWidth();
            String lvt_4_1_ = this.fontRenderer.trimStringToWidth(this.text.substring(this.lineScrollOffset), lvt_3_1_);
            int lvt_5_1_ = lvt_4_1_.length() + this.lineScrollOffset;
            if (p_setSelectionPos_1_ == this.lineScrollOffset) {
                this.lineScrollOffset -= this.fontRenderer.trimStringToWidth(this.text, lvt_3_1_, true).length();
            }
            if (p_setSelectionPos_1_ > lvt_5_1_) {
                this.lineScrollOffset += p_setSelectionPos_1_ - lvt_5_1_;
            } else if (p_setSelectionPos_1_ <= this.lineScrollOffset) {
                this.lineScrollOffset -= this.lineScrollOffset - p_setSelectionPos_1_;
            }
            this.lineScrollOffset = MathHelper.clamp_int((int)this.lineScrollOffset, (int)0, (int)lvt_2_1_);
        }
    }
}

