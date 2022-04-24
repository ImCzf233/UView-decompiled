// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.JsonObject
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraftforge.common.MinecraftForge
 *  org.apache.commons.lang3.StringUtils
 */
package com.domcer.uview.command;

import com.domcer.uview.listener.SecretKeyResponseListener;
import com.domcer.uview.messager.UViewMessager;
import com.domcer.uview.packet.packets.PacketSecretKeyRequest;
import com.domcer.uview.settings.UViewSettings;
import com.domcer.uview.settings.gui.GuiConfirm;
import com.domcer.uview.skin.DelayedTask;
import com.domcer.uview.util.HttpUtils;
import com.domcer.uview.util.IOUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.MinecraftForge;
import org.apache.commons.lang3.StringUtils;

public class CommandUCfg
extends CommandBase {
    private static final Gson gson = new Gson();
    private String json;

    public String getCommandName() {
        return "ucfg";
    }

    public String getCommandUsage(ICommandSender sender) {
        return "ucfg";
    }

    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length == 0) {
            CommandUCfg.shareConfig();
        } else {
            HttpURLConnection conn = null;
            try {
                conn = (HttpURLConnection)new URL("https://upload.server.domcer.com:25566/uview/load?code=" + args[0]).openConnection(Minecraft.getMinecraft().getProxy());
                conn.setDoInput(true);
                conn.setDoOutput(false);
                conn.connect();
                if (conn.getResponseCode() / 100 == 2) {
                    this.json = ((JsonObject)gson.fromJson(new String(IOUtils.readBytes(conn.getInputStream()), StandardCharsets.UTF_8), JsonObject.class)).get("data").getAsString();
                    MinecraftForge.EVENT_BUS.register((Object)this);
                    new DelayedTask(() -> Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiConfirm("\u786e\u5b9a\u52a0\u8f7d\u914d\u7f6e\u5417\uff1f\u8be5\u64cd\u4f5c\u4f1a\u8986\u76d6\u6240\u6709\u8bbe\u5b9a\uff01", () -> {
                        Minecraft.getMinecraft().displayGuiScreen(null);
                        UViewSettings.loadConfig(this.json);
                        sender.addChatMessage((IChatComponent)new ChatComponentText("\u00a7a\u52a0\u8f7d\u5b8c\u6bd5\uff01"));
                    }, () -> Minecraft.getMinecraft().displayGuiScreen(null))));
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
                sender.addChatMessage((IChatComponent)new ChatComponentText("\u00a7c\u52a0\u8f7d\u5931\u8d25\uff01"));
            }
            finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
        }
    }

    public static void shareConfig() {
        if (Minecraft.getMinecraft().thePlayer == null || SecretKeyResponseListener.responseHandler != null) {
            return;
        }
        PacketSecretKeyRequest packet = new PacketSecretKeyRequest();
        packet.type = "config";
        UViewMessager.sendPacket(packet);
        SecretKeyResponseListener.responseHandler = response -> {
            if (StringUtils.isEmpty((CharSequence)response) || "COOLDOWN".equals(response)) {
                Minecraft.getMinecraft().thePlayer.addChatMessage((IChatComponent)new ChatComponentText("\u00a7c\u64cd\u4f5c\u8fc7\u4e8e\u9891\u7e41\uff0c\u8bf7\u51e0\u5206\u949f\u540e\u518d\u8bd5\uff01"));
                return;
            }
            Minecraft.getMinecraft().thePlayer.addChatMessage((IChatComponent)new ChatComponentText("\u00a7a\u6b63\u5728\u4e0a\u4f20\u914d\u7f6e\u6587\u4ef6..."));
            UViewSettings.saveConfig();
            String json = UViewSettings.getConfigJson();
            try {
                HttpUtils.HttpResponse httpResponse = HttpUtils.postJson("https://upload.server.domcer.com:25566/uview/upload?key=" + response + "&uuid=" + Minecraft.getMinecraft().thePlayer.getCommandSenderEntity().getUniqueID().toString(), json);
                Minecraft.getMinecraft().thePlayer.addChatMessage((IChatComponent)new ChatComponentText("\u00a7a\u4e0a\u4f20\u6210\u529f\uff01\u73a9\u5bb6\u4f7f\u7528'/ucfg " + ((JsonObject)gson.fromJson(httpResponse.getContent(), JsonObject.class)).get("data").getAsString() + "'\u5373\u53ef\u52a0\u8f7d\u4f60\u7684\u914d\u7f6e\uff01"));
            }
            catch (Exception ex) {
                Minecraft.getMinecraft().thePlayer.addChatMessage((IChatComponent)new ChatComponentText("\u00a7c\u4e0a\u4f20\u5931\u8d25\uff01"));
            }
        };
    }
}

