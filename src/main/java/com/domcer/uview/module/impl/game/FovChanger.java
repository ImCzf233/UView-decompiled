// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.potion.Potion
 *  net.minecraftforge.client.event.FOVUpdateEvent
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.domcer.uview.module.impl.game;

import com.domcer.uview.module.AbstractModule;
import com.domcer.uview.settings.UViewSettings;
import com.domcer.uview.settings.types.NumberSetting;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.potion.Potion;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FovChanger
extends AbstractModule {
    public static final FovChanger INSTANCE = new FovChanger();

    @Override
    public void onLoad() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onFOVUpdate(FOVUpdateEvent event) {
        if (event.entity instanceof AbstractClientPlayer) {
            try {
                float fov = this.getNewFov((AbstractClientPlayer)event.entity);
                if (fov < 0.0f) {
                    return;
                }
                event.newfov = fov;
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    private float getNewFov(AbstractClientPlayer player) {
        if (player.capabilities.isFlying) {
            if (!UViewSettings.fovFlyingEnabled.value().booleanValue()) {
                return -1.0f;
            }
            return this.getFov(UViewSettings.fovFlying);
        }
        if (player.isPotionActive(Potion.moveSpeed)) {
            if (!UViewSettings.fovSpeedEnabled.value().booleanValue()) {
                return -1.0f;
            }
            return this.getFov(UViewSettings.fovSpeed);
        }
        if (player.isSprinting()) {
            if (!UViewSettings.fovSprintingEnabled.value().booleanValue()) {
                return -1.0f;
            }
            return this.getFov(UViewSettings.fovSprinting);
        }
        if (player.isPotionActive(Potion.moveSlowdown)) {
            if (!UViewSettings.fovSlownessEnabled.value().booleanValue()) {
                return -1.0f;
            }
            return this.getFov(UViewSettings.fovSlowness);
        }
        if (!UViewSettings.fovDefaultEnabled.value().booleanValue()) {
            return -1.0f;
        }
        return this.getFov(UViewSettings.fovDefault);
    }

    private float getFov(NumberSetting numberSetting) {
        return (float)(numberSetting.value().intValue() - 30) / 80.0f;
    }
}

