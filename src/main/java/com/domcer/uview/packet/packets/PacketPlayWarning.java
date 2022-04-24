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

public class PacketPlayWarning
extends Packet
implements PacketSerializer<PacketPlayWarning> {
    public int fadeIn;
    public int duration;
    public int fadeOut;
    public String title;
    public String subTitle;

    @Override
    public String name() {
        return "PacketPlayWarning";
    }

    @Override
    public byte[] encode(PacketPlayWarning packet) throws SerializationException {
        ByteArrayOutputStream out = new ByteArrayOutputStream(64);
        try {
            IOUtils.writeString(out, packet.name());
            IOUtils.writeInt(out, packet.fadeIn);
            IOUtils.writeInt(out, packet.duration);
            IOUtils.writeInt(out, packet.fadeOut);
            IOUtils.writeString(out, packet.title == null ? "" : packet.title);
            IOUtils.writeString(out, packet.subTitle == null ? "" : packet.subTitle);
            return out.toByteArray();
        }
        catch (IOException ex) {
            throw new SerializationException(ex.getMessage(), ex);
        }
    }

    @Override
    public PacketPlayWarning decode(byte[] bytes) throws DeserializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            IOUtils.readString(in);
            PacketPlayWarning packet = new PacketPlayWarning();
            packet.fadeIn = IOUtils.readInt(in);
            packet.duration = IOUtils.readInt(in);
            packet.fadeOut = IOUtils.readInt(in);
            packet.title = IOUtils.readString(in);
            packet.subTitle = IOUtils.readString(in);
            return packet;
        }
        catch (IOException ex) {
            throw new DeserializationException(ex.getMessage(), ex);
        }
    }
}

