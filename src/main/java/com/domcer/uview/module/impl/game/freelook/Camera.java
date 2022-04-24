// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  org.lwjgl.input.Mouse
 */
package com.domcer.uview.module.impl.game.freelook;

import com.domcer.uview.module.impl.game.freelook.FreeLook;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import org.lwjgl.input.Mouse;

public class Camera {
    public static boolean enabled = false;
    public static float cameraYaw = 0.0f;
    public static float cameraPitch = 0.0f;
    public static float playerYaw = 0.0f;
    public static float playerPitch = 0.0f;
    public static float originalYaw = 0.0f;
    public static float originalPitch = 0.0f;

    public static void update(boolean start) {
        Minecraft mc = Minecraft.getMinecraft();
        Entity player = mc.getRenderViewEntity();
        if (player == null) {
            return;
        }
        if (enabled) {
            Camera.updateCamera();
            if (start) {
                player.rotationYaw = player.prevRotationYaw = cameraYaw;
                player.rotationPitch = player.prevRotationPitch = -cameraPitch;
            } else {
                player.rotationYaw = mc.thePlayer.rotationYaw - cameraYaw + playerYaw;
                player.prevRotationYaw = mc.thePlayer.prevRotationYaw - cameraYaw + playerYaw;
                player.rotationPitch = -playerPitch;
                player.prevRotationPitch = -playerPitch;
            }
        }
    }

    private static void updateCamera() {
        Minecraft mc = Minecraft.getMinecraft();
        if (!mc.inGameHasFocus && !enabled) {
            return;
        }
        float f = mc.gameSettings.mouseSensitivity * 0.6f + 0.2f;
        float f1 = f * f * f * 8.0f;
        double dx = (double)((float)Mouse.getDX() * f1) * 0.15;
        double dy = (double)((float)Mouse.getDY() * f1) * 0.15;
        if (FreeLook.INSTANCE.keyBinding.isKeyDown()) {
            cameraYaw = (float)((double)cameraYaw + dx);
            cameraPitch = (float)((double)cameraPitch + dy);
            cameraPitch = Camera.clamp(cameraPitch, -90.0f, 90.0f);
            cameraYaw = Camera.clamp(cameraYaw, originalYaw + -180.0f, originalYaw + 180.0f);
        }
    }

    private static float clamp(float number, float min, float max) {
        return number < min ? min : Math.min(number, max);
    }

    public static void reset() {
        cameraYaw = originalYaw;
        cameraPitch = originalPitch;
        playerYaw = originalYaw;
        playerPitch = originalPitch;
    }

    public static void setCamera() {
        Minecraft mc = Minecraft.getMinecraft();
        playerYaw = originalYaw = mc.thePlayer.rotationYaw;
        cameraYaw = originalYaw;
        playerPitch = cameraPitch = (originalPitch = -mc.thePlayer.rotationPitch);
        enabled = true;
    }
}

