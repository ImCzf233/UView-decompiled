// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 */
package com.domcer.uview.gui.component;

import com.domcer.uview.gui.interfaces.UViewComponent;
import com.domcer.uview.messager.UViewMessager;
import com.domcer.uview.packet.packets.PacketCheckBox;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class UViewCheckBox
extends Gui
implements UViewComponent {
    public int width;
    public int height;
    public int xPosition;
    public int yPosition;
    public int id;
    public boolean checked;

    public UViewCheckBox(int id, int x, int y) {
        this(id, x, y, 20, 20);
    }

    public UViewCheckBox(int id, int x, int y, int widthIn, int heightIn) {
        this.id = id;
        this.xPosition = x;
        this.yPosition = y;
        this.width = widthIn;
        this.height = heightIn;
    }

    @Override
    public void beforeDraw(Minecraft mc, int mouseX, int mouseY, int offsetX, int offsetY, double scaled) {
        int left = (int)((double)this.xPosition * scaled + (double)offsetX);
        int right = (int)((double)left + (double)this.width * scaled);
        int top = (int)((double)this.yPosition * scaled + (double)offsetY);
        int bottom = (int)((double)top + (double)this.height * scaled);
        UViewCheckBox.drawRect((int)left, (int)top, (int)right, (int)bottom, (int)-9868951);
        UViewCheckBox.drawRect((int)(left + 2), (int)(top + 2), (int)(right - 2), (int)(bottom - 2), (int)-16777216);
        if (this.checked) {
            UViewCheckBox.drawRect((int)(left + 4), (int)(top + 4), (int)(right - 4), (int)(bottom - 4), (int)-14774017);
        }
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
        return "UViewCheckBox";
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

    public void senddUpdatePacket() {
        PacketCheckBox packet = new PacketCheckBox();
        packet.id = this.id;
        packet.checked = this.checked;
        UViewMessager.sendPacket(packet);
    }
}

