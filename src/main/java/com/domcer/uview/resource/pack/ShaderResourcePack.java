// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.client.resources.IResourceManagerReloadListener
 *  net.minecraft.client.resources.IResourcePack
 *  net.minecraft.client.resources.data.IMetadataSection
 *  net.minecraft.client.resources.data.IMetadataSerializer
 *  net.minecraft.client.resources.data.PackMetadataSection
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.ResourceLocation
 */
package com.domcer.uview.resource.pack;

import com.domcer.uview.UView;
import com.domcer.uview.settings.UViewSettings;
import com.google.common.collect.ImmutableSet;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.client.resources.data.PackMetadataSection;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;

public class ShaderResourcePack
implements IResourcePack,
IResourceManagerReloadListener {
    public static final ShaderResourcePack INSTANCE = new ShaderResourcePack();
    private final Map<ResourceLocation, String> loadedData = new HashMap<ResourceLocation, String>();

    protected boolean validPath(ResourceLocation location) {
        return location.getResourceDomain().equals("minecraft") && location.getResourcePath().startsWith("shaders/");
    }

    public InputStream getInputStream(ResourceLocation location) throws IOException {
        if (this.validPath(location)) {
            String s = this.loadedData.computeIfAbsent(location, loc -> {
                InputStream in = UView.class.getResourceAsStream("/" + location.getResourcePath());
                StringBuilder data = new StringBuilder();
                try (Scanner scan = new Scanner(in);){
                    while (scan.hasNextLine()) {
                        data.append(scan.nextLine().replaceAll("@radius@", Integer.toString(UViewSettings.blurRadius.value().intValue()))).append('\n');
                    }
                }
                return data.toString();
            });
            return new ByteArrayInputStream(s.getBytes());
        }
        throw new FileNotFoundException(location.toString());
    }

    public boolean resourceExists(ResourceLocation location) {
        return this.validPath(location) && UView.class.getResource("/" + location.getResourcePath()) != null;
    }

    public Set<String> getResourceDomains() {
        return Collections.singleton("minecraft");
    }

    public <T extends IMetadataSection> T getPackMetadata(IMetadataSerializer iMetadataSerializer, String metadataSectionName) throws IOException {
        if ("pack".equals(metadataSectionName)) {
            return (T)new PackMetadataSection((IChatComponent)new ChatComponentText("Blur's default shaders"), 3);
        }
        return null;
    }

    public BufferedImage getPackImage() throws IOException {
        throw new FileNotFoundException("pack.png");
    }

    public String getPackName() {
        return "Blur dummy resource pack";
    }

    public void onResourceManagerReload(IResourceManager resourceManager) {
        this.loadedData.clear();
    }
}

