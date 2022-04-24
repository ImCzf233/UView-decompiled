// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 */
package com.domcer.uview.util;

import com.domcer.uview.gui.fake.EntityFakePlayer;
import com.domcer.uview.gui.fake.UNetworkPlayerInfo;
import com.mojang.authlib.GameProfile;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class FakeEntityUtils {
    public static EntityFakePlayer createFakePlayer(Minecraft mc, ResourceLocation locationSkin) {
        UUID uuid = UUID.randomUUID();
        GameProfile gameProfile = new GameProfile(uuid, uuid.toString().substring(0, 6));
        UNetworkPlayerInfo playerInfo = new UNetworkPlayerInfo(gameProfile, locationSkin);
        return new EntityFakePlayer((World)mc.theWorld, gameProfile, playerInfo);
    }
}

