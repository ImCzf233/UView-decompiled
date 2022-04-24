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

public class PacketTextFieldUpdate
extends Packet
implements PacketSerializer<PacketTextFieldUpdate> {
    public int id;
    public String text;

    @Override
    public String name() {
        return "PacketTextFieldUpdate";
    }

    @Override
    public byte[] encode(PacketTextFieldUpdate packet) throws SerializationException {
        ByteArrayOutputStream out = new ByteArrayOutputStream(64);
        try {
            IOUtils.writeString(out, packet.name());
            IOUtils.writeInt(out, packet.id);
            IOUtils.writeString(out, packet.text == null ? "" : packet.text);
            return out.toByteArray();
        }
        catch (IOException ex) {
            throw new SerializationException(ex.getMessage(), ex);
        }
    }

    @Override
    public PacketTextFieldUpdate decode(byte[] bytes) throws DeserializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            IOUtils.readString(in);
            PacketTextFieldUpdate packet = new PacketTextFieldUpdate();
            packet.id = IOUtils.readInt(in);
            packet.text = IOUtils.readString(in);
            return packet;
        }
        catch (IOException ex) {
            throw new DeserializationException(ex.getMessage(), ex);
        }
    }
}

