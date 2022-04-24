/*
 * Decompiled with CFR 0.152.
 */
package com.domcer.uview.packet;

import com.domcer.uview.packet.PacketSerializer;
import java.util.HashMap;
import java.util.Map;

public class SerializerManager {
    private static final Map<String, PacketSerializer<?>> TYPE_SERIALIZER_MAP = new HashMap();

    public static void registerSerializer(PacketSerializer<?> serializer) {
        TYPE_SERIALIZER_MAP.put(serializer.name(), serializer);
    }

    public static PacketSerializer<?> getSerializer(String type) {
        return TYPE_SERIALIZER_MAP.get(type);
    }

    public static PacketSerializer<?> getSerializer(Class<?> type) {
        return TYPE_SERIALIZER_MAP.get(type.getSimpleName());
    }
}

