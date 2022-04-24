// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiIngameMenu
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.network.NetHandlerPlayClient
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.command.ICommand
 *  net.minecraftforge.client.ClientCommandHandler
 *  net.minecraftforge.client.event.GuiOpenEvent
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.Mod
 *  net.minecraftforge.fml.common.Mod$EventHandler
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.InputEvent$KeyInputEvent
 *  net.minecraftforge.fml.common.network.FMLEventChannel
 *  net.minecraftforge.fml.common.network.FMLNetworkEvent$CustomPacketRegistrationEvent
 *  net.minecraftforge.fml.common.network.NetworkRegistry
 *  org.lwjgl.opengl.Display
 */
package com.domcer.uview;

import com.domcer.uview.command.CommandUCfg;
import com.domcer.uview.custom.CustomIngameMenu;
import com.domcer.uview.custom.GuiAchievementWrapper;
import com.domcer.uview.messager.UViewMessager;
import com.domcer.uview.module.ModuleManager;
import com.domcer.uview.module.impl.game.Bossbar;
import com.domcer.uview.module.impl.tag.TagRenderer;
import com.domcer.uview.packet.packets.PacketCloseGui;
import com.domcer.uview.settings.UViewSettings;
import com.domcer.uview.settings.gui.GuiSettings;
import com.domcer.uview.skin.SkinManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.command.ICommand;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.lwjgl.opengl.Display;

@Mod(modid="uview", name="UView", version="1.0.0", acceptedMinecraftVersions="1.8.9")
public class UView {
    public static final String MODID = "uview";
    public static final String NAME = "UView";
    public static final String VERSION = "1.0.0";
    public static final String CHANNEL_NAME = "UView";
    public static UView instance;
    public static UViewMessager messager;
    public static FMLEventChannel channel;
    private static final KeyBinding keyBinding;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        instance = this;
        ClientCommandHandler.instance.registerCommand((ICommand)new CommandUCfg());
        MinecraftForge.EVENT_BUS.register((Object)this);
        channel = NetworkRegistry.INSTANCE.newEventDrivenChannel("UView");
        channel.register((Object)this);
        messager = new UViewMessager();
        MinecraftForge.EVENT_BUS.register((Object)messager);
        Display.setTitle((String)"我的世界 DoMCer 中国版");
        UViewSettings.loadConfig(UViewSettings.getConfigJson());
        SkinManager.init();
        ModuleManager.INSTANCE.init();
        try {
            Minecraft.getMinecraft().guiAchievement = new GuiAchievementWrapper(Minecraft.getMinecraft(), Minecraft.getMinecraft().guiAchievement);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @SubscribeEvent
    public void onGuiOpen(GuiOpenEvent event) {
        if (event.gui instanceof GuiIngameMenu) {
            event.gui = new CustomIngameMenu(null, Minecraft.getMinecraft().gameSettings);
        }
    }

    @SubscribeEvent
    public void onGuiOpen(InputEvent.KeyInputEvent event) {
        if (keyBinding.isPressed()) {
            Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiSettings());
        }
    }

    @SubscribeEvent
    public void onClientConnectedToServer(FMLNetworkEvent.CustomPacketRegistrationEvent<NetHandlerPlayClient> event) {
        if ("REGISTER".equals(event.operation) && event.registrations.contains((Object)"UView")) {
            TagRenderer.INSTANCE.clear();
            Bossbar.INSTANCE.clear();
            UViewMessager.sendPacket(new PacketCloseGui());
        }
    }

    static {
        keyBinding = new KeyBinding("游戏设置", 54, "key.categories.misc");
    }
}

