// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.player.AttackEntityEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.domcer.uview.module.impl.game;

import com.domcer.uview.module.AbstractModule;
import com.domcer.uview.settings.UViewSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SharpnessParticles
extends AbstractModule {
    public static final SharpnessParticles INSTANCE = new SharpnessParticles();

    @Override
    public void onLoad() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void playerHit(AttackEntityEvent event) {
        if (!(event.target instanceof EntityPlayer)) {
            return;
        }
        int strength = UViewSettings.sharpnessParticlesSetting.value().intValue();
        if (strength <= 0) {
            return;
        }
        for (int i = 0; i < strength; ++i) {
            Minecraft.getMinecraft().effectRenderer.emitParticleAtEntity(event.target, EnumParticleTypes.CRIT_MAGIC);
        }
    }
}

