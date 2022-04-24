/*
 * Decompiled with CFR 0.152.
 */
package com.domcer.uview.packet;

import com.domcer.uview.packet.exception.DeserializationException;
import com.domcer.uview.packet.exception.SerializationException;

public interface PacketSerializer<T> {
    public String name();

    public byte[] encode(T var1) throws SerializationException;

    public T decode(byte[] var1) throws DeserializationException;
}

