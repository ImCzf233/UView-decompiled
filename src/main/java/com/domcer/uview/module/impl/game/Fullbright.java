// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 */
package com.domcer.uview.module.impl.game;

import com.domcer.uview.module.AbstractModule;
import com.domcer.uview.settings.UViewSettings;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Fullbright
extends AbstractModule {
    public static final Fullbright INSTANCE = new Fullbright();

    @Override
    public void onLoad() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (UViewSettings.fullBrightEnabled.value().booleanValue()) {
            if (Minecraft.getMinecraft().gameSettings.gammaSetting != 100.0f) {
                Minecraft.getMinecraft().gameSettings.gammaSetting = 100.0f;
            }
        } else if ((double)Minecraft.getMinecraft().gameSettings.gammaSetting != 1.0 && (double)Minecraft.getMinecraft().gameSettings.gammaSetting != 15.0) {
            Minecraft.getMinecraft().gameSettings.gammaSetting = 1.0f;
        }
    }
}

