// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 */
package com.domcer.uview.listener;

import com.domcer.uview.packet.PacketListener;
import com.domcer.uview.packet.packets.PacketOpenGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class OpenGuiListener
extends PacketListener<PacketOpenGui> {
    public OpenGuiListener() {
        super(PacketOpenGui.class);
    }

    @Override
    public void receivePacket(PacketOpenGui packetOpenGui) {
        Minecraft.getMinecraft().displayGuiScreen((GuiScreen)packetOpenGui.gui);
    }
}

