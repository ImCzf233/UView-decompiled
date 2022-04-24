// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.network.NetworkPlayerInfo
 *  net.minecraft.client.renderer.texture.ITextureObject
 *  net.minecraft.client.renderer.texture.SimpleTexture
 *  net.minecraft.network.play.server.S38PacketPlayerListItem$AddPlayerData
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.WorldSettings$GameType
 */
package com.domcer.uview.gui.fake;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.network.play.server.S38PacketPlayerListItem;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldSettings;

public class UNetworkPlayerInfo
extends NetworkPlayerInfo {
    private final GameProfile gameProfile;
    private WorldSettings.GameType gameType;
    private int responseTime;
    private ResourceLocation locationSkin;
    private ResourceLocation locationCape;
    private String skinType;
    private IChatComponent displayName;
    private boolean playerTexturesLoaded = false;

    public UNetworkPlayerInfo(GameProfile gameProfile, ResourceLocation locationSkin) {
        super(gameProfile);
        this.gameProfile = gameProfile;
        this.locationSkin = locationSkin;
    }

    public UNetworkPlayerInfo(S38PacketPlayerListItem.AddPlayerData addPlayerData, ResourceLocation locationSkin) {
        super(addPlayerData);
        this.gameProfile = addPlayerData.getProfile();
        this.gameType = addPlayerData.getGameMode();
        this.responseTime = addPlayerData.getPing();
        this.displayName = addPlayerData.getDisplayName();
        this.locationSkin = locationSkin;
    }

    public boolean hasLocationSkin() {
        return this.locationSkin != null;
    }

    public ResourceLocation getLocationSkin() {
        return this.locationSkin;
    }

    public ResourceLocation getLocationCape() {
        return this.locationCape;
    }

    public GameProfile getGameProfile() {
        return this.gameProfile;
    }

    public WorldSettings.GameType getGameType() {
        return this.gameType;
    }

    protected void setGameType(WorldSettings.GameType gameType) {
        this.gameType = gameType;
    }

    public int getResponseTime() {
        return this.responseTime;
    }

    protected void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    public IChatComponent getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(IChatComponent displayName) {
        this.displayName = displayName;
    }

    public String getSkinType() {
        return "default";
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void loadPlayerTextures() {
        UNetworkPlayerInfo uNetworkPlayerInfo = this;
        synchronized (uNetworkPlayerInfo) {
            if (!this.playerTexturesLoaded) {
                this.playerTexturesLoaded = true;
                Minecraft.getMinecraft().getTextureManager().loadTexture(this.locationSkin, (ITextureObject)new SimpleTexture(this.locationSkin));
            }
        }
    }
}

