/*
 * Decompiled with CFR 0.152.
 */
package com.domcer.uview.listener;

import com.domcer.uview.packet.PacketListener;
import com.domcer.uview.packet.packets.PacketPlayerSkinUpdate;
import com.domcer.uview.skin.SkinManager;

public class PlayerSkinUpdateListener
extends PacketListener<PacketPlayerSkinUpdate> {
    public PlayerSkinUpdateListener() {
        super(PacketPlayerSkinUpdate.class);
    }

    @Override
    public void receivePacket(PacketPlayerSkinUpdate playerSkinUpdate) {
        SkinManager.applySkin(playerSkinUpdate.uuid, playerSkinUpdate.skinName);
    }
}

