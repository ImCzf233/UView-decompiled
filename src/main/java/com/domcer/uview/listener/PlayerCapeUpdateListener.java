/*
 * Decompiled with CFR 0.152.
 */
package com.domcer.uview.listener;

import com.domcer.uview.packet.PacketListener;
import com.domcer.uview.packet.packets.PacketPlayerCapeUpdate;
import com.domcer.uview.skin.SkinManager;

public class PlayerCapeUpdateListener
extends PacketListener<PacketPlayerCapeUpdate> {
    public PlayerCapeUpdateListener() {
        super(PacketPlayerCapeUpdate.class);
    }

    @Override
    public void receivePacket(PacketPlayerCapeUpdate packetPlayerCapeUpdate) {
        SkinManager.applyCape(packetPlayerCapeUpdate.uuid, packetPlayerCapeUpdate.capeName);
    }
}

