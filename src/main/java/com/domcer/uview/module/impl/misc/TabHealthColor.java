// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.network.NetworkPlayerInfo
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.EnumChatFormatting
 */
package com.domcer.uview.module.impl.misc;

import com.domcer.uview.settings.UViewSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;

public class TabHealthColor {
    public static EnumChatFormatting getColor(NetworkPlayerInfo playerInfo, int health) {
        if (UViewSettings.tabHealthColorEnabled.value().booleanValue() && Minecraft.getMinecraft().thePlayer != null) {
            float maxHealth;
            EntityPlayer entityPlayer = TabHealthColor.getEntityPlayer(playerInfo);
            float f = maxHealth = entityPlayer == null ? Minecraft.getMinecraft().thePlayer.getMaxHealth() : entityPlayer.getMaxHealth();
            if ((float)health > maxHealth * 0.75f) {
                return EnumChatFormatting.GREEN;
            }
            if ((float)health > maxHealth * 0.5f) {
                return EnumChatFormatting.YELLOW;
            }
            if ((float)health > maxHealth * 0.25f) {
                return EnumChatFormatting.RED;
            }
            return EnumChatFormatting.DARK_RED;
        }
        return EnumChatFormatting.YELLOW;
    }

    private static EntityPlayer getEntityPlayer(NetworkPlayerInfo playerInfo) {
        if (Minecraft.getMinecraft().theWorld != null && playerInfo.getGameProfile() != null) {
            return Minecraft.getMinecraft().theWorld.getPlayerEntityByUUID(playerInfo.getGameProfile().getId());
        }
        return null;
    }
}

