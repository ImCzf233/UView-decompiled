/*
 * Decompiled with CFR 0.152.
 */
package com.domcer.uview.packet.packets;

import com.domcer.uview.packet.Packet;
import com.domcer.uview.packet.PacketSerializer;
import com.domcer.uview.packet.exception.DeserializationException;
import com.domcer.uview.packet.exception.SerializationException;
import com.domcer.uview.util.IOUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PacketSecretKeyRequest
extends Packet
implements PacketSerializer<PacketSecretKeyRequest> {
    public String type;

    @Override
    public String name() {
        return "PacketSecretKeyRequest";
    }

    @Override
    public byte[] encode(PacketSecretKeyRequest packet) throws SerializationException {
        ByteArrayOutputStream out = new ByteArrayOutputStream(64);
        try {
            IOUtils.writeString(out, packet.name());
            IOUtils.writeString(out, packet.type);
            return out.toByteArray();
        }
        catch (IOException ex) {
            throw new SerializationException(ex.getMessage(), ex);
        }
    }

    @Override
    public PacketSecretKeyRequest decode(byte[] bytes) throws DeserializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            IOUtils.readString(in);
            PacketSecretKeyRequest packet = new PacketSecretKeyRequest();
            packet.type = IOUtils.readString(in);
            return packet;
        }
        catch (IOException ex) {
            throw new DeserializationException(ex.getMessage(), ex);
        }
    }
}

