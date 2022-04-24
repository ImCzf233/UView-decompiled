/*
 * Decompiled with CFR 0.152.
 */
package com.domcer.uview.listener;

import com.domcer.uview.module.impl.tag.TagRenderer;
import com.domcer.uview.packet.PacketListener;
import com.domcer.uview.packet.packets.PacketTitlesUpdate;

public class TitlesUpdateListener
extends PacketListener<PacketTitlesUpdate> {
    public TitlesUpdateListener() {
        super(PacketTitlesUpdate.class);
    }

    @Override
    public void receivePacket(PacketTitlesUpdate packetTitlesUpdate) {
        TagRenderer.INSTANCE.updateTags(packetTitlesUpdate.uuid, packetTitlesUpdate.lines);
    }
}

