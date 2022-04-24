/*
 * Decompiled with CFR 0.152.
 */
package com.domcer.uview.listener;

import com.domcer.uview.module.impl.game.Bossbar;
import com.domcer.uview.packet.PacketListener;
import com.domcer.uview.packet.packets.PacketBossbarUpdate;

public class BossbarUpdateListener
extends PacketListener<PacketBossbarUpdate> {
    public BossbarUpdateListener() {
        super(PacketBossbarUpdate.class);
    }

    @Override
    public void receivePacket(PacketBossbarUpdate packet) {
        Bossbar.customName = packet.customName;
        Bossbar.customColor = packet.customColor;
        Bossbar.customHealthScale = packet.customHealthScale;
    }
}

