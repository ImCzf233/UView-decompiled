// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.cache.LoadingCache
 *  com.google.common.collect.Iterables
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonSerializationContext
 *  com.google.gson.JsonSerializer
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.minecraft.MinecraftProfileTexture
 *  com.mojang.authlib.minecraft.MinecraftProfileTexture$Type
 *  com.mojang.authlib.properties.Property
 *  com.mojang.authlib.properties.PropertyMap
 *  com.mojang.authlib.properties.PropertyMap$Serializer
 *  com.mojang.authlib.yggdrasil.response.MinecraftTexturesPayload
 *  com.mojang.authlib.yggdrasil.response.ProfileSearchResultsResponse
 *  com.mojang.authlib.yggdrasil.response.ProfileSearchResultsResponse$Serializer
 *  com.mojang.util.UUIDTypeAdapter
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.ITextureObject
 *  net.minecraft.client.renderer.texture.SimpleTexture
 *  net.minecraft.client.resources.DefaultPlayerSkin
 *  net.minecraft.client.resources.DefaultResourcePack
 *  net.minecraft.client.resources.SkinManager
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.relauncher.ReflectionHelper
 *  org.apache.commons.codec.binary.Base64
 *  org.apache.commons.io.FileUtils
 *  org.apache.commons.io.FilenameUtils
 *  org.apache.commons.lang3.StringUtils
 */
package com.domcer.uview.skin;

import com.domcer.uview.resource.ResourceManager;
import com.domcer.uview.resource.pack.UViewResourcePack;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Iterables;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import com.mojang.authlib.yggdrasil.response.MinecraftTexturesPayload;
import com.mojang.authlib.yggdrasil.response.ProfileSearchResultsResponse;
import com.mojang.util.UUIDTypeAdapter;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

public class SkinManager {
    private static final File SKIN_CACHE_DIR = new File("czfsb");
    private static final File CACHE_TABLE = new File(SKIN_CACHE_DIR, "cache.json");
    private static final Map<String, Long> RESOURCE_CACHE_MAP = new ConcurrentHashMap<String, Long>();
    private static final Map<String, String> HASH_MAP = new HashMap<String, String>();
    private static final Map<UUID, String> skinMap = new ConcurrentHashMap<UUID, String>();
    private static final Map<UUID, String> capeMap = new ConcurrentHashMap<UUID, String>();
    private static final Map<String, ResourceLocation> RESOURCE_LOCATION_MAP = new ConcurrentHashMap<String, ResourceLocation>();
    private static final Gson GSON = new GsonBuilder().registerTypeAdapter(GameProfile.class, (Object)new GameProfileSerializer()).registerTypeAdapter(PropertyMap.class, (Object)new PropertyMap.Serializer()).registerTypeAdapter(UUID.class, (Object)new UUIDTypeAdapter()).registerTypeAdapter(ProfileSearchResultsResponse.class, (Object)new ProfileSearchResultsResponse.Serializer()).create();
    private static LoadingCache<GameProfile, Map<MinecraftProfileTexture.Type, MinecraftProfileTexture>> cache;
    private static Method _loadSkinFromCacheWrapper;
    private static Method _getTexturesWrapper;

    public static void init() {
        ResourceManager.loadCaches();
        UViewResourcePack.INSTANCE.getResourceDomains();
    }

    private static void saveCacheTable() {
        try {
            JsonObject jsonObject = new JsonObject();
            for (Map.Entry<String, Long> entry : RESOURCE_CACHE_MAP.entrySet()) {
                jsonObject.addProperty(entry.getKey(), (Number)entry.getValue());
            }
            FileUtils.writeStringToFile((File)CACHE_TABLE, (String)GSON.toJson((JsonElement)jsonObject));
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static void applySkin(UUID uuid, String skinName) {
        if (StringUtils.isEmpty((CharSequence)skinName)) {
            SkinManager.restoreSkin(uuid);
            return;
        }
        skinMap.put(uuid, skinName);
    }

    public static void restoreSkin(UUID uuid) {
        skinMap.remove(uuid);
    }

    public static void applyCape(UUID uuid, String capeName) {
        if (StringUtils.isEmpty((CharSequence)capeName)) {
            SkinManager.restoreCape(uuid);
            return;
        }
        capeMap.put(uuid, capeName);
    }

    public static ResourceLocation getLocationSkin(GameProfile profile) {
        if (profile.getId() != null && skinMap.containsKey(profile.getId())) {
            return SkinManager.getResource(skinMap.get(profile.getId()));
        }
        return null;
    }

    public static ResourceLocation getLocationCape(GameProfile profile) {
        if (profile.getId() != null && capeMap.containsKey(profile.getId())) {
            return SkinManager.getResource(capeMap.get(profile.getId()));
        }
        return null;
    }

    private static ResourceLocation getResource(String url) {
        String hash = SkinManager.getHash(FilenameUtils.getBaseName((String)url));
        ResourceLocation resourceLocation = RESOURCE_LOCATION_MAP.get(hash);
        if (resourceLocation != null) {
            return resourceLocation;
        }
        String child = hash.length() > 2 ? hash.substring(0, 2) : "xx";
        resourceLocation = new ResourceLocation("skins/" + child + "/" + hash);
        File file = new File(new File(SKIN_CACHE_DIR, child), hash);
        if (!file.exists()) {
            System.out.println(hash);
            RESOURCE_LOCATION_MAP.put(hash, DefaultPlayerSkin.getDefaultSkinLegacy());
            SkinManager.downloadResource(file, url, hash, resourceLocation);
            return DefaultPlayerSkin.getDefaultSkinLegacy();
        }
        RESOURCE_LOCATION_MAP.put(hash, resourceLocation);
        Minecraft.getMinecraft().getTextureManager().deleteTexture(resourceLocation);
        Minecraft.getMinecraft().getTextureManager().loadTexture(resourceLocation, (ITextureObject)new SimpleTexture(resourceLocation));
        return resourceLocation;
    }

    private static void downloadResource(File file, String url, String hash, ResourceLocation resourceLocation) {
        new Thread(() -> {
            HttpURLConnection conn = null;
            try {
                conn = (HttpURLConnection)new URL(url).openConnection(Minecraft.getMinecraft().getProxy());
                conn.setDoInput(true);
                conn.setDoOutput(false);
                conn.connect();
                if (conn.getResponseCode() / 100 == 2) {
                    FileUtils.copyInputStreamToFile((InputStream)conn.getInputStream(), (File)file);
                    RESOURCE_CACHE_MAP.put(hash, System.currentTimeMillis());
                    RESOURCE_LOCATION_MAP.put(hash, resourceLocation);
                    SkinManager.saveCacheTable();
                    Minecraft.getMinecraft().addScheduledTask(() -> {
                        Minecraft.getMinecraft().getTextureManager().deleteTexture(resourceLocation);
                        Minecraft.getMinecraft().getTextureManager().loadTexture(resourceLocation, (ITextureObject)new SimpleTexture(resourceLocation));
                    });
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
        }).start();
    }

    private static String getHash(String input) {
        return HASH_MAP.computeIfAbsent(input, k -> UUID.nameUUIDFromBytes(input.getBytes()).toString());
    }

    public static void restoreCape(UUID uuid) {
        capeMap.remove(uuid);
    }

    public static Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> loadSkinFromCacheWrapper(GameProfile profile) {
        if (profile.getId() != null && (skinMap.containsKey(profile.getId()) || capeMap.containsKey(profile.getId()))) {
            HashMap<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = new HashMap<MinecraftProfileTexture.Type, MinecraftProfileTexture>(2);
            if (skinMap.containsKey(profile.getId())) {
                map.put(MinecraftProfileTexture.Type.SKIN, new MinecraftProfileTexture(skinMap.get(profile.getId()), null));
            }
            if (capeMap.containsKey(profile.getId())) {
                map.put(MinecraftProfileTexture.Type.CAPE, new MinecraftProfileTexture(capeMap.get(profile.getId()), null));
            }
            return map;
        }
        try {
            if (_loadSkinFromCacheWrapper == null) {
                _loadSkinFromCacheWrapper = Class.forName("com.netease.mc.mod.skin.SkinHandler").getMethod("loadSkinFromCacheWrapper", GameProfile.class);
                _loadSkinFromCacheWrapper.setAccessible(true);
            }
            if (_loadSkinFromCacheWrapper == null) {
                return Collections.emptyMap();
            }
            Object obj = _loadSkinFromCacheWrapper.invoke(null, profile);
            System.out.println("_loadSkinFromCacheWrapper=" + obj);
            return obj == null ? Collections.emptyMap() : (Map)obj;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return Collections.emptyMap();
        }
    }

    public static Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> getTexturesWrapper(GameProfile profile, boolean requireSecure) {
        if (profile.getId() != null && (skinMap.containsKey(profile.getId()) || capeMap.containsKey(profile.getId()))) {
            HashMap<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = new HashMap<MinecraftProfileTexture.Type, MinecraftProfileTexture>(2);
            if (skinMap.containsKey(profile.getId())) {
                map.put(MinecraftProfileTexture.Type.SKIN, new MinecraftProfileTexture(skinMap.get(profile.getId()), null));
            }
            if (capeMap.containsKey(profile.getId())) {
                map.put(MinecraftProfileTexture.Type.CAPE, new MinecraftProfileTexture(capeMap.get(profile.getId()), null));
            }
            return map;
        }
        try {
            if (_getTexturesWrapper == null) {
                _getTexturesWrapper = Class.forName("com.netease.mc.mod.skin.SkinHandler").getMethod("getTexturesWrapper", GameProfile.class, Boolean.TYPE);
                _getTexturesWrapper.setAccessible(true);
            }
            if (_getTexturesWrapper == null) {
                return Collections.emptyMap();
            }
            Object obj = _getTexturesWrapper.invoke(null, profile, requireSecure);
            return obj == null ? Collections.emptyMap() : (Map)obj;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return Collections.emptyMap();
        }
    }

    private static Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> getTexturesInternal(Property property) {
        MinecraftTexturesPayload result;
        if (property == null) {
            return null;
        }
        if (property.hasSignature()) {
            return null;
        }
        try {
            String json = new String(Base64.decodeBase64((String)property.getValue()), StandardCharsets.UTF_8);
            result = (MinecraftTexturesPayload)GSON.fromJson(json, MinecraftTexturesPayload.class);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        if (result.getTextures() == null) {
            return null;
        }
        return result.getTextures();
    }

    private static class GameProfileSerializer
    implements JsonSerializer<GameProfile>,
    JsonDeserializer<GameProfile> {
        private GameProfileSerializer() {
        }

        public GameProfile deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject object = (JsonObject)json;
            UUID id = object.has("id") ? (UUID)context.deserialize(object.get("id"), UUID.class) : null;
            String name = object.has("name") ? object.getAsJsonPrimitive("name").getAsString() : null;
            return new GameProfile(id, name);
        }

        public JsonElement serialize(GameProfile src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject result = new JsonObject();
            if (src.getId() != null) {
                result.add("id", context.serialize((Object)src.getId()));
            }
            if (src.getName() != null) {
                result.addProperty("name", src.getName());
            }
            return result;
        }
    }
}

