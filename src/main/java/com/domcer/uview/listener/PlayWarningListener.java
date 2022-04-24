/*
 * Decompiled with CFR 0.152.
 */
package com.domcer.uview.listener;

import com.domcer.uview.custom.WarningAnimation;
import com.domcer.uview.packet.PacketListener;
import com.domcer.uview.packet.packets.PacketPlayWarning;

public class PlayWarningListener
extends PacketListener<PacketPlayWarning> {
    public PlayWarningListener() {
        super(PacketPlayWarning.class);
    }

    @Override
    public void receivePacket(PacketPlayWarning packet) {
        WarningAnimation.play(packet.fadeIn, packet.duration, packet.fadeOut, packet.title, packet.subTitle);
    }
}

