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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PacketTitlesUpdate
extends Packet
implements PacketSerializer<PacketTitlesUpdate> {
    public UUID uuid;
    public List<String> lines;

    @Override
    public String name() {
        return "PacketTitlesUpdate";
    }

    @Override
    public byte[] encode(PacketTitlesUpdate packet) throws SerializationException {
        ByteArrayOutputStream out = new ByteArrayOutputStream(64);
        try {
            IOUtils.writeString(out, packet.name());
            IOUtils.writeString(out, packet.uuid.toString());
            IOUtils.writeInt(out, packet.lines == null ? 0 : packet.lines.size());
            if (packet.lines != null) {
                for (String line : packet.lines) {
                    IOUtils.writeString(out, line);
                }
            }
            return out.toByteArray();
        }
        catch (IOException ex) {
            throw new SerializationException(ex.getMessage(), ex);
        }
    }

    @Override
    public PacketTitlesUpdate decode(byte[] bytes) throws DeserializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            IOUtils.readString(in);
            PacketTitlesUpdate packet = new PacketTitlesUpdate();
            packet.uuid = UUID.fromString(IOUtils.readString(in));
            packet.lines = new ArrayList<String>();
            int size = IOUtils.readInt(in);
            for (int i = 0; i < size; ++i) {
                packet.lines.add(IOUtils.readString(in));
            }
            return packet;
        }
        catch (IOException ex) {
            throw new DeserializationException(ex.getMessage(), ex);
        }
    }
}

