// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package com.domcer.uview.listener;

import com.domcer.uview.packet.PacketListener;
import com.domcer.uview.packet.packets.PacketCloseGui;
import net.minecraft.client.Minecraft;

public class CloseGuiListener
extends PacketListener<PacketCloseGui> {
    public CloseGuiListener() {
        super(PacketCloseGui.class);
    }

    @Override
    public void receivePacket(PacketCloseGui packetCloseGui) {
        Minecraft.getMinecraft().displayGuiScreen(null);
    }
}

