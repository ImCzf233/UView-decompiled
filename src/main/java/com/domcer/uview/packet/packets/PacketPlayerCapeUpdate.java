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
import java.util.UUID;

public class PacketPlayerCapeUpdate
extends Packet
implements PacketSerializer<PacketPlayerCapeUpdate> {
    public UUID uuid;
    public String capeName;

    @Override
    public String name() {
        return "PacketPlayerCapeUpdate";
    }

    @Override
    public byte[] encode(PacketPlayerCapeUpdate packet) throws SerializationException {
        ByteArrayOutputStream out = new ByteArrayOutputStream(64);
        try {
            IOUtils.writeString(out, packet.name());
            IOUtils.writeString(out, packet.uuid.toString());
            IOUtils.writeString(out, packet.capeName == null ? "" : packet.capeName);
            return out.toByteArray();
        }
        catch (IOException ex) {
            throw new SerializationException(ex.getMessage(), ex);
        }
    }

    @Override
    public PacketPlayerCapeUpdate decode(byte[] bytes) throws DeserializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            IOUtils.readString(in);
            PacketPlayerCapeUpdate packet = new PacketPlayerCapeUpdate();
            packet.uuid = UUID.fromString(IOUtils.readString(in));
            packet.capeName = IOUtils.readString(in);
            return packet;
        }
        catch (IOException ex) {
            throw new DeserializationException(ex.getMessage(), ex);
        }
    }
}

