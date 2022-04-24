/*
 * Decompiled with CFR 0.152.
 */
package com.domcer.uview.packet;

public abstract class PacketListener<T> {
    private Class<T> type;

    public PacketListener(Class<T> type) {
        this.type = type;
    }

    public abstract void receivePacket(T var1);

    public Class<T> getType() {
        return this.type;
    }
}

