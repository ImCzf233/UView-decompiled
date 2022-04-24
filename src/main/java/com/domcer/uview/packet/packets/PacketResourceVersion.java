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

public class PacketResourceVersion
extends Packet
implements PacketSerializer<PacketResourceVersion> {
    public int version;

    @Override
    public String name() {
        return "PacketResourceVersion";
    }

    @Override
    public byte[] encode(PacketResourceVersion packet) throws SerializationException {
        ByteArrayOutputStream out = new ByteArrayOutputStream(64);
        try {
            IOUtils.writeString(out, packet.name());
            IOUtils.writeInt(out, packet.version);
            return out.toByteArray();
        }
        catch (IOException ex) {
            throw new SerializationException(ex.getMessage(), ex);
        }
    }

    @Override
    public PacketResourceVersion decode(byte[] bytes) throws DeserializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            IOUtils.readString(in);
            PacketResourceVersion packet = new PacketResourceVersion();
            packet.version = IOUtils.readInt(in);
            return packet;
        }
        catch (IOException ex) {
            throw new DeserializationException(ex.getMessage(), ex);
        }
    }
}

