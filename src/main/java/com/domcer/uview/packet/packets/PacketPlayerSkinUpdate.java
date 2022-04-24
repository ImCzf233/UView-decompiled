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

public class PacketPlayerSkinUpdate
extends Packet
implements PacketSerializer<PacketPlayerSkinUpdate> {
    public UUID uuid;
    public String skinName;

    @Override
    public String name() {
        return "PacketPlayerSkinUpdate";
    }

    @Override
    public byte[] encode(PacketPlayerSkinUpdate packet) throws SerializationException {
        ByteArrayOutputStream out = new ByteArrayOutputStream(64);
        try {
            IOUtils.writeString(out, packet.name());
            IOUtils.writeString(out, packet.uuid.toString());
            IOUtils.writeString(out, packet.skinName == null ? "" : packet.skinName);
            return out.toByteArray();
        }
        catch (IOException ex) {
            throw new SerializationException(ex.getMessage(), ex);
        }
    }

    @Override
    public PacketPlayerSkinUpdate decode(byte[] bytes) throws DeserializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            IOUtils.readString(in);
            PacketPlayerSkinUpdate packet = new PacketPlayerSkinUpdate();
            packet.uuid = UUID.fromString(IOUtils.readString(in));
            packet.skinName = IOUtils.readString(in);
            return packet;
        }
        catch (IOException ex) {
            throw new DeserializationException(ex.getMessage(), ex);
        }
    }
}

