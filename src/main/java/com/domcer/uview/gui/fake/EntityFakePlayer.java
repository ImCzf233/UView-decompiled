// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityOtherPlayerMP
 *  net.minecraft.client.network.NetworkPlayerInfo
 *  net.minecraft.client.resources.DefaultPlayerSkin
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldSettings$GameType
 */
package com.domcer.uview.gui.fake;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;

public class EntityFakePlayer
extends EntityOtherPlayerMP {
    private NetworkPlayerInfo playerInfo;

    public EntityFakePlayer(World world, GameProfile gameProfile, NetworkPlayerInfo playerInfo) {
        super(world, gameProfile);
        this.playerInfo = playerInfo;
    }

    public boolean isSpectator() {
        NetworkPlayerInfo networkplayerinfo = Minecraft.getMinecraft().getNetHandler().getPlayerInfo(this.getGameProfile().getId());
        return networkplayerinfo != null && networkplayerinfo.getGameType() == WorldSettings.GameType.SPECTATOR;
    }

    public boolean hasPlayerInfo() {
        return this.getPlayerInfo() != null;
    }

    public boolean hasSkin() {
        NetworkPlayerInfo networkplayerinfo = this.getPlayerInfo();
        return networkplayerinfo != null && networkplayerinfo.hasLocationSkin();
    }

    public ResourceLocation getLocationSkin() {
        NetworkPlayerInfo networkplayerinfo = this.getPlayerInfo();
        return networkplayerinfo == null ? DefaultPlayerSkin.getDefaultSkin((UUID)this.getUniqueID()) : networkplayerinfo.getLocationSkin();
    }

    public ResourceLocation getLocationCape() {
        NetworkPlayerInfo networkplayerinfo = this.getPlayerInfo();
        return networkplayerinfo == null ? null : networkplayerinfo.getLocationCape();
    }

    public String getSkinType() {
        NetworkPlayerInfo networkplayerinfo = this.getPlayerInfo();
        return networkplayerinfo == null ? DefaultPlayerSkin.getSkinType((UUID)this.getUniqueID()) : networkplayerinfo.getSkinType();
    }

    protected NetworkPlayerInfo getPlayerInfo() {
        return this.playerInfo;
    }

    public void onUpdate() {
    }

    public void onLivingUpdate() {
    }

    protected void onDeathUpdate() {
    }

    public void onEntityUpdate() {
    }

    public void addChatMessage(IChatComponent chatComponent) {
    }

    public void addChatComponentMessage(IChatComponent chatComponent) {
    }
}

