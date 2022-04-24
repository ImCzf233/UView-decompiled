// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.ITextureObject
 *  net.minecraft.client.renderer.texture.SimpleTexture
 *  net.minecraft.util.ResourceLocation
 */
package com.domcer.uview.resource;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.util.ResourceLocation;

public class ResourceConstants {
    private static final Map<String, ResourceLocation> RESOURCE_LOCATION_SET = new ConcurrentHashMap<String, ResourceLocation>();
    public static final ResourceLocation SYSTEM_WARNING_ICON = ResourceConstants.create("warning_icon.sys");
    public static final ResourceLocation COSMETIC_BANDANA_NIKE = ResourceConstants.create("textures/bandana.res");
    public static final ResourceLocation COSMETIC_HAT = ResourceConstants.create("textures/hat.res");
    public static final ResourceLocation COSMETIC_FORMAL_HAT = ResourceConstants.create("textures/formalhat.res");

    public static void reloadAll() {
        for (ResourceLocation resourceLocation : RESOURCE_LOCATION_SET.values()) {
            Minecraft.getMinecraft().getTextureManager().deleteTexture(resourceLocation);
            Minecraft.getMinecraft().getTextureManager().loadTexture(resourceLocation, (ITextureObject)new SimpleTexture(resourceLocation));
        }
    }

    public static ResourceLocation create(String path) {
        return RESOURCE_LOCATION_SET.computeIfAbsent(path, k -> new ResourceLocation("uview", path));
    }
}

