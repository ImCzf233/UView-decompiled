/*
 * Decompiled with CFR 0.152.
 */
package com.domcer.uview.listener;

import com.domcer.uview.packet.PacketListener;
import com.domcer.uview.packet.packets.PacketSecretKeyResponse;
import java.util.function.Consumer;

public class SecretKeyResponseListener
extends PacketListener<PacketSecretKeyResponse> {
    public static Consumer<String> responseHandler;

    public SecretKeyResponseListener() {
        super(PacketSecretKeyResponse.class);
    }

    @Override
    public void receivePacket(PacketSecretKeyResponse packet) {
        if (responseHandler != null) {
            try {
                Consumer<String> consumer = responseHandler;
                responseHandler = null;
                consumer.accept(packet.response);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

