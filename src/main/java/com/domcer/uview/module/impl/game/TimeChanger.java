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
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.common.gameevent.TickEvent$RenderTickEvent
 */
package com.domcer.uview.module.impl.game;

import com.domcer.uview.module.AbstractModule;
import com.domcer.uview.settings.UViewSettings;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class TimeChanger
extends AbstractModule {
    public static final TimeChanger INSTANCE = new TimeChanger();

    @Override
    public void onLoad() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    public static long getTime(long time) {
        if (UViewSettings.timeChangerSetting.value().booleanValue()) {
            return UViewSettings.timeChangerTime.value().longValue();
        }
        return time;
    }

    @SubscribeEvent
    public void onTick(TickEvent.RenderTickEvent event) {
        long worldTime;
        if (!UViewSettings.timeChangerSetting.value().booleanValue() || event.phase != TickEvent.Phase.END) {
            return;
        }
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.theWorld != null && (worldTime = mc.theWorld.getWorldTime()) != UViewSettings.timeChangerTime.value().longValue()) {
            mc.theWorld.setWorldTime(UViewSettings.timeChangerTime.value().longValue());
        }
    }
}

