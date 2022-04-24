/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.Unpooled
 *  net.minecraft.network.PacketBuffer
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.network.FMLNetworkEvent$ClientCustomPacketEvent
 *  net.minecraftforge.fml.common.network.internal.FMLProxyPacket
 */
package com.domcer.uview.messager;

import com.domcer.uview.UView;
import com.domcer.uview.listener.BossbarUpdateListener;
import com.domcer.uview.listener.CloseGuiListener;
import com.domcer.uview.listener.OpenGuiListener;
import com.domcer.uview.listener.PlayWarningListener;
import com.domcer.uview.listener.PlayerCapeUpdateListener;
import com.domcer.uview.listener.PlayerSkinUpdateListener;
import com.domcer.uview.listener.SecretKeyResponseListener;
import com.domcer.uview.listener.TitlesUpdateListener;
import com.domcer.uview.packet.Packet;
import com.domcer.uview.packet.PacketListener;
import com.domcer.uview.packet.PacketSerializer;
import com.domcer.uview.packet.SerializerManager;
import com.domcer.uview.packet.exception.DeserializationException;
import com.domcer.uview.packet.exception.SerializationException;
import com.domcer.uview.packet.packets.PacketBossbarUpdate;
import com.domcer.uview.packet.packets.PacketButtonClick;
import com.domcer.uview.packet.packets.PacketCheckBox;
import com.domcer.uview.packet.packets.PacketCloseGui;
import com.domcer.uview.packet.packets.PacketOpenGui;
import com.domcer.uview.packet.packets.PacketPlayWarning;
import com.domcer.uview.packet.packets.PacketPlayerCapeUpdate;
import com.domcer.uview.packet.packets.PacketPlayerSkinUpdate;
import com.domcer.uview.packet.packets.PacketResourceVersion;
import com.domcer.uview.packet.packets.PacketSecretKeyRequest;
import com.domcer.uview.packet.packets.PacketSecretKeyResponse;
import com.domcer.uview.packet.packets.PacketTextFieldUpdate;
import com.domcer.uview.packet.packets.PacketTitlesUpdate;
import com.domcer.uview.packet.serializers.StringSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;

public class UViewMessager {
    private static final Logger LOGGER = Logger.getLogger("UViewMessager");
    private final PacketSerializer<String> packetNameSerializer = new StringSerializer();
    private final List<PacketListener> packetListeners = new ArrayList<PacketListener>();

    public UViewMessager() {
        UView.channel.register((Object)this);
        SerializerManager.registerSerializer(new PacketOpenGui());
        this.registerListener(new OpenGuiListener());
        SerializerManager.registerSerializer(new PacketCloseGui());
        this.registerListener(new CloseGuiListener());
        SerializerManager.registerSerializer(new PacketButtonClick());
        SerializerManager.registerSerializer(new PacketTextFieldUpdate());
        SerializerManager.registerSerializer(new PacketCheckBox());
        SerializerManager.registerSerializer(new PacketTitlesUpdate());
        this.registerListener(new TitlesUpdateListener());
        SerializerManager.registerSerializer(new PacketPlayerSkinUpdate());
        this.registerListener(new PlayerSkinUpdateListener());
        SerializerManager.registerSerializer(new PacketPlayerCapeUpdate());
        this.registerListener(new PlayerCapeUpdateListener());
        SerializerManager.registerSerializer(new PacketSecretKeyRequest());
        SerializerManager.registerSerializer(new PacketSecretKeyResponse());
        this.registerListener(new SecretKeyResponseListener());
        SerializerManager.registerSerializer(new PacketBossbarUpdate());
        this.registerListener(new BossbarUpdateListener());
        SerializerManager.registerSerializer(new PacketPlayWarning());
        this.registerListener(new PlayWarningListener());
        SerializerManager.registerSerializer(new PacketResourceVersion());
    }

    public void registerListener(PacketListener packetListener) {
        this.packetListeners.add(packetListener);
    }

    @SubscribeEvent
    public void onClientPacket(FMLNetworkEvent.ClientCustomPacketEvent event) {
        if ("UView".equals(event.packet.channel())) {
            byte[] data = event.packet.payload().array();
            try {
                String packetName = this.packetNameSerializer.decode(data);
                PacketSerializer<?> serializer = SerializerManager.getSerializer(packetName);
                if (serializer == null) {
                    LOGGER.warning("Packet serializer " + packetName + " not fouond!");
                    return;
                }
                Packet packet = (Packet)serializer.decode(data);
                for (PacketListener packetListener : this.packetListeners) {
                    if (!packetListener.getType().equals(packet.getClass())) continue;
                    try {
                        packetListener.receivePacket(packet);
                    }
                    catch (Exception e) {
                        LOGGER.warning("handle packet " + packetName + " failed!");
                        e.printStackTrace();
                    }
                }
            }
            catch (DeserializationException e) {
                LOGGER.warning("Packet decode failed!");
                e.printStackTrace();
            }
        }
    }

    public static void sendPacket(Packet packet) {
        byte[] array = new byte[0];
        PacketSerializer<?> serializer = SerializerManager.getSerializer(packet.name());
        if (serializer == null) {
            LOGGER.warning("Packet serializer " + packet.name() + " not fouond!");
            return;
        }

        ByteBuf buf = Unpooled.wrappedBuffer((byte[])array);
        FMLProxyPacket proxyPacket = new FMLProxyPacket(new PacketBuffer(buf), "UView");
        UView.channel.sendToServer(proxyPacket);
    }
}

