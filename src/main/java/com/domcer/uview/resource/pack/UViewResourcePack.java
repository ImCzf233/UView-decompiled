// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.resources.AbstractResourcePack
 *  net.minecraft.client.resources.IResourcePack
 *  net.minecraft.client.resources.SimpleReloadableResourceManager
 *  net.minecraft.client.resources.data.IMetadataSection
 *  net.minecraft.client.resources.data.IMetadataSerializer
 *  net.minecraft.client.resources.data.PackMetadataSection
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraftforge.fml.relauncher.ReflectionHelper
 */
package com.domcer.uview.resource.pack;

import com.domcer.uview.resource.CachedImage;
import com.domcer.uview.resource.ResourceManager;
import com.google.common.collect.ImmutableSet;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Base64;
import java.util.Collections;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.client.resources.data.PackMetadataSection;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class UViewResourcePack
extends AbstractResourcePack {
    public static final UViewResourcePack INSTANCE = new UViewResourcePack(new File(Minecraft.getMinecraft().mcDataDir, "uview"));

    public UViewResourcePack(File file) {
        super(file);
    }

    protected InputStream getInputStreamByName(String name) throws IOException {
        if ((name = name.substring(7)).endsWith(".json")) {
            try {
                String id = name.replace("uview/caches/", "").replace(".json", "");
                CachedImage cachedImage = ResourceManager.getCachedImageById(id);
                if (cachedImage == null) {
                    return null;
                }
                return new ByteArrayInputStream(Base64.getDecoder().decode(cachedImage.getJsonObject().get("data").getAsString()));
            }
            catch (Exception e) {
                return null;
            }
        }
        if (name.endsWith(".sys")) {
            try {
                return new ByteArrayInputStream(Base64.getDecoder().decode("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"));
            }
            catch (Exception e) {
                return null;
            }
        }
        File file = new File(Minecraft.getMinecraft().mcDataDir, name);
        return file.exists() ? new FileInputStream(file) : null;
    }

    protected boolean hasResourceName(String name) {
        if ((name = name.substring(7)).endsWith("json")) {
            try {
                String id = name.replace("uview/caches/", "").replace(".json", "");
                CachedImage cachedImage = ResourceManager.getCachedImageById(id);
                return cachedImage != null;
            }
            catch (Exception e) {
                return false;
            }
        }
        if (name.endsWith(".sys")) {
            return true;
        }
        return new File(Minecraft.getMinecraft().mcDataDir, name).exists();
    }

    public Set<String> getResourceDomains() {
        return Collections.singleton("uview");
    }

    public <T extends IMetadataSection> T getPackMetadata(IMetadataSerializer iMetadataSerializer, String metadataSectionName) throws IOException {
        if ("pack".equals(metadataSectionName)) {
            return (T)new PackMetadataSection((IChatComponent)new ChatComponentText("UView"), 3);
        }
        return null;
    }

    public BufferedImage getPackImage() throws IOException {
        throw new FileNotFoundException("pack.png");
    }

    public String getPackName() {
        return "UView resource pack";
    }
}

