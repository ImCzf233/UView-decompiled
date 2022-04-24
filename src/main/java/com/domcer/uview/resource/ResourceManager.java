// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  com.google.gson.stream.JsonReader
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.ResourceLocation
 */
package com.domcer.uview.resource;

import com.domcer.uview.resource.CachedImage;
import com.domcer.uview.resource.pack.UViewResourcePack;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Base64;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class ResourceManager {
    private static final Map<String, ResourceLocation> resourceLocationMap = new ConcurrentHashMap<String, ResourceLocation>();
    public static final Map<String, CachedImage> cachedImages = new ConcurrentHashMap<String, CachedImage>();
    private static final JsonParser jsonParser = new JsonParser();

    public static void loadCaches() {
        File dirFile = new File(Minecraft.getMinecraft().mcDataDir, "uview/caches");
        dirFile.mkdirs();
        try {
            for (File file : Objects.requireNonNull(dirFile.listFiles((dir, name) -> name.endsWith(".json")))) {
                try {
                    JsonObject jsonObject = (JsonObject)jsonParser.parse(new JsonReader((Reader)new FileReader(file)));
                    cachedImages.put(jsonObject.get("key").getAsString(), new CachedImage(file.getName().replace(".json", ""), jsonObject));
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static CachedImage getCachedImageByKey(String key) {
        return cachedImages.get(key);
    }

    public static CachedImage getCachedImageById(String id) {
        for (CachedImage cachedImage : cachedImages.values()) {
            if (!cachedImage.getId().equalsIgnoreCase(id)) continue;
            return cachedImage;
        }
        return null;
    }

    public static ResourceLocation getResourceLocation(String key) {
        if (resourceLocationMap.containsKey(key)) {
            return resourceLocationMap.get(key);
        }
        ResourceLocation resourceLocation = new ResourceLocation("uview", key);
        resourceLocationMap.put(key, resourceLocation);
        return resourceLocation;
    }

    public static ResourceLocation parseLocation(String data) {
        if (data == null || data.isEmpty() || "none".equals(data)) {
            return null;
        }
        if (data.startsWith("base64:")) {
            try {
                String fileName;
                data = data.replace("base64:", "");
                if (data.contains(":")) {
                    String[] split = data.split(":");
                    fileName = split[0].toLowerCase();
                    data = split[1];
                } else {
                    fileName = UUID.randomUUID().toString().replaceAll("-", "");
                }
                ByteArrayInputStream bais = new ByteArrayInputStream(Base64.getDecoder().decode(data));
                BufferedImage bi1 = ImageIO.read(bais);
                File file = new File(Minecraft.getMinecraft().mcDataDir, "uview/textures/" + fileName + ".png");
                if (!file.exists()) {
                    file.mkdirs();
                    file.createNewFile();
                }
                ImageIO.write((RenderedImage)bi1, "png", file);
                return new ResourceLocation("uview", "textures/" + fileName + ".png");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } else if (data.startsWith("cachedbase64:")) {
            try {
                String key;
                data = data.replace("cachedbase64:", "");
                if (data.contains(":")) {
                    String[] split = data.split(":");
                    key = split[0];
                    data = split[1];
                } else {
                    key = UUID.randomUUID().toString().replaceAll("-", "");
                }
                if (cachedImages.containsKey(key)) {
                    return new ResourceLocation("uview", "caches/" + cachedImages.get(key).getId() + ".json");
                }
                String id = UUID.randomUUID().toString().replaceAll("-", "");
                File file = new File(Minecraft.getMinecraft().mcDataDir, "uview/caches/" + id + ".json");
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("key", key);
                jsonObject.addProperty("timestamp", (Number)System.currentTimeMillis());
                jsonObject.addProperty("data", data);
                bw.write(jsonObject.toString());
                bw.flush();
                bw.close();
                cachedImages.put(key, new CachedImage(id, jsonObject));
                return new ResourceLocation("uview", "caches/" + id + ".json");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResourceManager.getResourceLocation(data);
    }

    static {
        UViewResourcePack.INSTANCE.getResourceDomains();
        ResourceManager.loadCaches();
    }
}

