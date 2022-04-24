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

public class PacketBossbarUpdate
extends Packet
implements PacketSerializer<PacketBossbarUpdate> {
    public String customName;
    public int customColor;
    public float customHealthScale;

    @Override
    public String name() {
        return "PacketBossbarUpdate";
    }

    @Override
    public byte[] encode(PacketBossbarUpdate packet) throws SerializationException {
        ByteArrayOutputStream out = new ByteArrayOutputStream(64);
        try {
            IOUtils.writeString(out, packet.name());
            IOUtils.writeString(out, packet.customName);
            IOUtils.writeInt(out, packet.customColor);
            IOUtils.writeInt(out, (int)(packet.customHealthScale * 100.0f));
            return out.toByteArray();
        }
        catch (IOException ex) {
            throw new SerializationException(ex.getMessage(), ex);
        }
    }

    @Override
    public PacketBossbarUpdate decode(byte[] bytes) throws DeserializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            IOUtils.readString(in);
            PacketBossbarUpdate packet = new PacketBossbarUpdate();
            packet.customName = IOUtils.readString(in);
            packet.customColor = IOUtils.readInt(in);
            packet.customHealthScale = (float)IOUtils.readInt(in) / 100.0f;
            return packet;
        }
        catch (IOException ex) {
            throw new DeserializationException(ex.getMessage(), ex);
        }
    }
}

