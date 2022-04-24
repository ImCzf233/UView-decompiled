/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  org.apache.commons.io.FileUtils
 */
package com.domcer.uview.settings;

import com.domcer.uview.module.impl.game.blockoverlay.BlockOverlayMode;
import com.domcer.uview.settings.ISetting;
import com.domcer.uview.settings.types.BooleanSetting;
import com.domcer.uview.settings.types.ColorSetting;
import com.domcer.uview.settings.types.EnumSetting;
import com.domcer.uview.settings.types.ItemCounterSetting;
import com.domcer.uview.settings.types.NumberSetting;
import com.domcer.uview.settings.types.StringSetting;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.io.FileUtils;

public class UViewSettings {
    public static BooleanSetting tabHealthColorEnabled = new BooleanSetting("tabHealthColorEnabled", "TAB\u754c\u9762\u8840\u91cf\u989c\u8272", true);
    public static BooleanSetting blurSetting = new BooleanSetting("blurEnabled", "\u754c\u9762\u6a21\u7cca", false);
    public static NumberSetting blurSpeed = new NumberSetting("blurSpeed", "\u6a21\u7cca\u901f\u5ea6", 200);
    public static NumberSetting blurRadius = new NumberSetting("blurRadius", "\u6a21\u7cca\u7a0b\u5ea6", 12);
    public static BooleanSetting motionBlurSetting = new BooleanSetting("motionBlurSetting", "\u52a8\u6001\u6a21\u7cca", false);
    public static NumberSetting motionBlurAmount = new NumberSetting("motionBlurAmount", "\u6a21\u7cca\u7a0b\u5ea6", 0);
    public static BooleanSetting timeChangerSetting = new BooleanSetting("timeChangerSetting", "\u5f00\u542f\u65f6\u95f4\u66f4\u6539", false);
    public static NumberSetting timeChangerTime = new NumberSetting("timeChangerTime", "\u65f6\u95f4\u66f4\u6539\u663e\u793a\u65f6\u95f4", 1000);
    public static BooleanSetting tagSetting = new BooleanSetting("tagEnabled", "\u4eba\u7269\u6807\u7b7e", true);
    public static NumberSetting sharpnessParticlesSetting = new NumberSetting("sharpnessParticlesEnabled", "\u950b\u5229\u7c92\u5b50\u589e\u5f3a", 0);
    public static BooleanSetting freeLookSetting = new BooleanSetting("freeLookEnabled", "\u81ea\u7531\u89c6\u89d2", true);
    public static BooleanSetting fullBrightEnabled = new BooleanSetting("fullBrightEnabled", "\u65e0\u9650\u591c\u89c6", false);
    public static BooleanSetting itemPhysicsEnabled = new BooleanSetting("itemPhysicsEnabled", "\u7269\u7406\u6389\u843d", false);
    public static NumberSetting itemPhysicsRotateSpeed = new NumberSetting("itemPhysicsRotateSpeed", "\u7269\u7406\u6389\u843d\u65cb\u8f6c\u901f\u5ea6", Float.valueOf(1.0f));
    public static BooleanSetting hideAchievementEnabled = new BooleanSetting("hideAchievementEnabled", "\u9690\u85cf\u6210\u5c31\u63d0\u793a\u754c\u9762", false);
    public static BooleanSetting tntTimeEnabled = new BooleanSetting("tntTimeEnabled", "TNT\u5012\u8ba1\u65f6", false);
    public static BooleanSetting betterChatSmooth = new BooleanSetting("betterChatSmooth", "\u5e73\u6ed1\u804a\u5929\u680f", false);
    public static NumberSetting betterChatClear = new NumberSetting("betterChatClear", "\u804a\u5929\u680f\u900f\u660e\u5ea6", Float.valueOf(1.0f));
    public static BooleanSetting toggleSprintSetting = new BooleanSetting("toggleSprintEnabled", "\u5f3a\u5236\u75be\u8dd1", true);
    public static NumberSetting toggleSprintTextX = new NumberSetting("toggleSprintTextX", null, Float.valueOf(0.1f));
    public static NumberSetting toggleSprintTextY = new NumberSetting("toggleSprintTextY", null, Float.valueOf(0.96f));
    public static NumberSetting toggleSprintScale = new NumberSetting("toggleSprintScale", "\u5f3a\u5236\u75be\u8dd1\u6587\u672c\u5927\u5c0f", Float.valueOf(1.0f));
    public static StringSetting toggleSprintText = new StringSetting("toggleSprintText", "\u5f3a\u5236\u75be\u8dd1\u6587\u672c", "&f\u5f3a\u5236\u75be\u8dd1&a(\u53f3SHIFT\u952e\u6253\u5f00\u8bbe\u7f6e)");
    public static ColorSetting toggleSprintColor = new ColorSetting("toggleSprintColor", "\u5f3a\u5236\u75be\u8dd1\u989c\u8272", -1, false).enableChroma();
    public static BooleanSetting armorHudEnabled = new BooleanSetting("armorHudEnabled", "\u662f\u5426\u5f00\u542f", false);
    public static NumberSetting armorHudX = new NumberSetting("armorHudX", null, Float.valueOf(0.0f));
    public static NumberSetting armorHudY = new NumberSetting("armorHudY", null, Float.valueOf(0.5f));
    public static NumberSetting armorHudScale = new NumberSetting("armorHudScale", "\u88c5\u5907\u663e\u793a\u5927\u5c0f", Float.valueOf(1.0f));
    public static BooleanSetting armorHudhorizontalAlignment = new BooleanSetting("armorHudhorizontalAlignment", "\u662f\u5426\u6c34\u5e73\u7ed8\u5236", false);
    public static NumberSetting armorHudSpacing = new NumberSetting("armorHudSpacing", "\u88c5\u5907\u95f4\u9694", 0);
    public static BooleanSetting armorHudDurability = new BooleanSetting("armorHudDurability", "\u663e\u793a\u88c5\u5907\u8010\u4e45", true){

        @Override
        public Boolean value() {
            return false;
        }
    };
    public static BooleanSetting armorHudDurabilityPercentage = new BooleanSetting("armorHudDurabilityPercentage", "\u4ee5\u767e\u5206\u6bd4\u663e\u793a\u88c5\u5907\u8010\u4e45", true);
    public static ColorSetting armorHudDurabilityColor = new ColorSetting("armorHudDurabilityColor", "\u88c5\u5907\u8010\u4e45\u663e\u793a\u989c\u8272", -1, false).enableChroma();
    public static BooleanSetting potionEffectHudEnabled = new BooleanSetting("potionEffectHudEnabled", "\u836f\u6c34\u663e\u793a", false);
    public static NumberSetting potionEffectHudX = new NumberSetting("potionEffectHudX", null, Float.valueOf(0.0f));
    public static NumberSetting potionEffectHudY = new NumberSetting("potionEffectHudY", null, Float.valueOf(0.0f));
    public static ColorSetting potionEffectHudTextColor = new ColorSetting("potionEffectHudTextColor", "\u836f\u6c34\u540d\u79f0\u5b57\u4f53\u989c\u8272", -1, false).enableChroma();
    public static ColorSetting potionEffectHudDurationColor = new ColorSetting("potionEffectHudDurationColor", "\u836f\u6c34\u65f6\u95f4\u5b57\u4f53\u989c\u8272", -8421505, false).enableChroma();
    public static BooleanSetting potionEffectHudRenderBackground = new BooleanSetting("potionEffectHudRenderBackground", "\u836f\u6c34\u663e\u793a\u7ed8\u5236\u80cc\u666f", true);
    public static NumberSetting potionEffectHudScale = new NumberSetting("potionEffectHudScale", "\u836f\u6c34\u663e\u793a\u7ed8\u5236\u5927\u5c0f", Float.valueOf(1.0f));
    public static BooleanSetting potionEffectHudShowName = new BooleanSetting("potionEffectHudShowName", "\u836f\u6c34\u663e\u793a\u7ed8\u5236\u540d\u79f0", true);
    public static BooleanSetting potionEffectHudShowIcon = new BooleanSetting("potionEffectHudShowIcon", "\u836f\u6c34\u663e\u793a\u7ed8\u5236\u56fe\u6807", true);
    public static BooleanSetting potionEffectHudShowDuration = new BooleanSetting("potionEffectHudShowDuration", "\u836f\u6c34\u663e\u793a\u7ed8\u5236\u65f6\u95f4", true);
    public static BooleanSetting directionHudEnabled = new BooleanSetting("directionHudEnabled", "\u65b9\u5411\u663e\u793a", false);
    public static NumberSetting directionHudX = new NumberSetting("directionHudX", null, Float.valueOf(0.48f));
    public static NumberSetting directionHudY = new NumberSetting("directionHudY", null, Float.valueOf(0.0f));
    public static NumberSetting directionHudScale = new NumberSetting("directionHudScale", "\u65b9\u5411\u663e\u793a\u5927\u5c0f", Float.valueOf(1.0f));
    public static ColorSetting directionHudColor = new ColorSetting("directionHudColor", "\u65b9\u5411\u663e\u793a\u989c\u8272", -1, false).enableChroma();
    public static BooleanSetting bossbarEnabled = new BooleanSetting("bossbarEnabled", "Boss\u8840\u6761", true);
    public static NumberSetting bossbarX = new NumberSetting("bossbarX", null, Float.valueOf(0.4f));
    public static NumberSetting bossbarY = new NumberSetting("bossbarY", null, Float.valueOf(0.0f));
    public static BooleanSetting bossbarText = new BooleanSetting("bossbarText", "\u663e\u793aBoss\u8840\u6761\u6587\u672c", true);
    public static BooleanSetting bossbarProgress = new BooleanSetting("bossbarProgress", "\u663e\u793aBoss\u8840\u6761\u8fdb\u5ea6", true);
    public static NumberSetting bossbarScale = new NumberSetting("bossbarScale", "Boss\u8840\u6761\u5927\u5c0f", Float.valueOf(1.0f));
    public static BooleanSetting coordinatesEnabled = new BooleanSetting("coordinatesEnabled", "\u5750\u6807\u663e\u793a", false);
    public static NumberSetting coordinatesX = new NumberSetting("coordinatesX", null, Float.valueOf(0.0f));
    public static NumberSetting coordinatesY = new NumberSetting("coordinatesY", null, Float.valueOf(0.8f));
    public static BooleanSetting coordinatesTrend = new BooleanSetting("coordinatesTrend", "\u663e\u793a\u5750\u6807\u53d8\u5316\u8d8b\u52bf", true);
    public static NumberSetting coordinatesScale = new NumberSetting("coordinatesScale", "\u5750\u6807\u663e\u793a\u5927\u5c0f", Float.valueOf(1.0f));
    public static ColorSetting coordinatesColor = new ColorSetting("coordinatesColor", "\u5750\u6807\u663e\u793a\u989c\u8272", -1, false).enableChroma();
    public static BooleanSetting coordinatesBackground = new BooleanSetting("coordinatesBackground", "\u5750\u6807\u663e\u793a\u80cc\u666f", true);
    public static ColorSetting coordinatesBackgroundColor = new ColorSetting("coordinatesBackgroundColor", "\u5750\u6807\u663e\u793a\u80cc\u666f\u989c\u8272", -1, false).enableAlpha();
    public static BooleanSetting cpsDisplayEnabled = new BooleanSetting("cpsDisplayEnabled", "CPS\u663e\u793a", false);
    public static NumberSetting cpsDisplayX = new NumberSetting("cpsDisplayX", null, Float.valueOf(0.0f));
    public static NumberSetting cpsDisplayY = new NumberSetting("cpsDisplayY", null, Float.valueOf(0.3f));
    public static BooleanSetting cpsDisplayRightEnabled = new BooleanSetting("cpsDisplayRightEnabled", "\u53f3\u952eCPS\u663e\u793a", false);
    public static BooleanSetting cpsDisplayDigitalOnly = new BooleanSetting("cpsDisplayDigitalOnly", "\u4ec5\u663e\u793aCPS\u6570\u503c", false);
    public static NumberSetting cpsDisplayScale = new NumberSetting("cpsDisplayScale", "CPS\u663e\u793a\u5927\u5c0f", Float.valueOf(1.0f));
    public static ColorSetting cpsDisplayColor = new ColorSetting("cpsDisplayColor", "CPS\u663e\u793a\u989c\u8272", -1, false).enableChroma();
    public static BooleanSetting cpsDisplayBackground = new BooleanSetting("cpsDisplayBackground", "CPS\u663e\u793a\u80cc\u666f", true);
    public static ColorSetting cpsDisplayBackgroundColor = new ColorSetting("cpsDisplayBackgroundColor", "CPS\u663e\u793a\u80cc\u666f\u989c\u8272", -1, false).enableAlpha();
    public static BooleanSetting fpsDisplayEnabled = new BooleanSetting("fpsDisplayEnabled", "FPS\u663e\u793a", false);
    public static NumberSetting fpsDisplayX = new NumberSetting("fpsDisplayX", null, Float.valueOf(0.0f));
    public static NumberSetting fpsDisplayY = new NumberSetting("fpsDisplayY", null, Float.valueOf(0.35f));
    public static NumberSetting fpsDisplayScale = new NumberSetting("fpsDisplayScale", "FPS\u663e\u793a\u5927\u5c0f", Float.valueOf(1.0f));
    public static ColorSetting fpsDisplayColor = new ColorSetting("fpsDisplayColor", "FPS\u663e\u793a\u989c\u8272", -1, false).enableChroma();
    public static BooleanSetting fpsDisplayBackground = new BooleanSetting("fpsDisplayBackground", "FPS\u663e\u793a\u80cc\u666f", true);
    public static ColorSetting fpsDisplayBackgroundColor = new ColorSetting("fpsDisplayBackgroundColor", "FPS\u663e\u793a\u80cc\u666f\u989c\u8272", -1, false).enableAlpha();
    public static BooleanSetting pingDisplayEnabled = new BooleanSetting("pingDisplayEnabled", "Ping\u663e\u793a", false);
    public static NumberSetting pingDisplayX = new NumberSetting("pingDisplayX", null, Float.valueOf(0.0f));
    public static NumberSetting pingDisplayY = new NumberSetting("pingDisplayY", null, Float.valueOf(0.4f));
    public static NumberSetting pingDisplayScale = new NumberSetting("pingDisplayScale", "Ping\u663e\u793a\u5927\u5c0f", Float.valueOf(1.0f));
    public static ColorSetting pingDisplayColor = new ColorSetting("pingDisplayColor", "Ping\u663e\u793a\u989c\u8272", -1, false).enableChroma();
    public static BooleanSetting pingDisplayBackground = new BooleanSetting("pingDisplayBackground", "Ping\u663e\u793a\u80cc\u666f", true);
    public static ColorSetting pingDisplayBackgroundColor = new ColorSetting("pingDisplayBackgroundColor", "Ping\u663e\u793a\u80cc\u666f\u989c\u8272", -1, false).enableAlpha();
    public static BooleanSetting keyStrokeEnabled = new BooleanSetting("keyStrokeEnabled", "\u6309\u952e\u663e\u793a", false);
    public static NumberSetting keyStrokeX = new NumberSetting("keyStrokeX", null, Float.valueOf(0.0f));
    public static NumberSetting keyStrokeY = new NumberSetting("keyStrokeY", null, Float.valueOf(0.0f));
    public static NumberSetting keyStrokeScale = new NumberSetting("keyStrokeScale", "\u6309\u952e\u663e\u793a\u5927\u5c0f", Float.valueOf(1.0f));
    public static BooleanSetting keyStrokeArrowKeys = new BooleanSetting("keyStrokeArrowKeys", "\u663e\u793aWASD\u56fe\u6807", false);
    public static BooleanSetting keyStrokeShowSpacebar = new BooleanSetting("keyStrokeShowSpacebar", "\u663e\u793a\u7a7a\u683c", false);
    public static BooleanSetting keyStrokeShowSneak = new BooleanSetting("keyStrokeShowSneak", "\u663e\u793a\u4e0b\u8e72\u952e", false);
    public static BooleanSetting keyStrokeShowMouse = new BooleanSetting("keyStrokeShowMouse", "\u663e\u793a\u9f20\u6807\u6309\u952e", true);
    public static BooleanSetting keyStrokeShowWASD = new BooleanSetting("keyStrokeShowWASD", "\u663e\u793aWASD\u952e", false);
    public static ColorSetting keyStrokeColor = new ColorSetting("pingDisplayColor", "\u6309\u952e\u663e\u793a\u989c\u8272", -1, false).enableChroma();
    public static ColorSetting keyStrokeBackgroundColor = new ColorSetting("keyStrokeBackgroundColor", "\u6309\u952e\u663e\u793a\u80cc\u666f\u989c\u8272", 0x1F000000, false).enableAlpha();
    public static ColorSetting keyStrokePressedBackgroundColor = new ColorSetting("keyStrokePressedBackgroundColor", "\u6309\u952e\u663e\u793a\u6309\u538b\u65f6\u80cc\u666f\u989c\u8272", -1, false).enableAlpha();
    public static BooleanSetting fovDefaultEnabled = new BooleanSetting("fovDefaultEnabled", "\u4fee\u6539\u9ed8\u8ba4\u89c6\u91ce", false);
    public static NumberSetting fovDefault = new NumberSetting("fovDefault", "\u9ed8\u8ba4\u89c6\u91ce", 90);
    public static BooleanSetting fovSprintingEnabled = new BooleanSetting("fovSprintingEnabled", "\u4fee\u6539\u75be\u8dd1\u89c6\u91ce", false);
    public static NumberSetting fovSprinting = new NumberSetting("fovSprinting", "\u75be\u8dd1\u89c6\u91ce", 90);
    public static BooleanSetting fovSpeedEnabled = new BooleanSetting("fovSpeedEnabled", "\u4fee\u6539\u901f\u5ea6\u89c6\u91ce", false);
    public static NumberSetting fovSpeed = new NumberSetting("fovSpeed", "\u901f\u5ea6\u89c6\u91ce", 90);
    public static BooleanSetting fovSlownessEnabled = new BooleanSetting("fovSlownessEnabled", "\u4fee\u6539\u7f13\u6162\u89c6\u91ce", false);
    public static NumberSetting fovSlowness = new NumberSetting("fovSlowness", "\u7f13\u6162\u89c6\u91ce", 90);
    public static BooleanSetting fovFlyingEnabled = new BooleanSetting("fovFlying", "\u4fee\u6539\u98de\u884c\u89c6\u91ce", false);
    public static NumberSetting fovFlying = new NumberSetting("fovFlying", "\u98de\u884c\u89c6\u91ce", 90);
    public static BooleanSetting crosshairEnabled = new BooleanSetting("crosshairEnabled", "\u5f00\u542f\u9f20\u6807\u6307\u9488", false);
    public static NumberSetting crosshairType = new NumberSetting("crosshairType", "\u6307\u9488\u7c7b\u578b", 0);
    public static NumberSetting crosshairDotType = new NumberSetting("crosshairDotType", "\u6307\u9488\u4e2d\u5fc3\u70b9\u7c7b\u578b", 0);
    public static NumberSetting crosshairWidth = new NumberSetting("crosshairWidth", "\u6307\u9488\u5bbd", 3);
    public static NumberSetting crosshairHeight = new NumberSetting("crosshairHeight", "\u6307\u9488\u9ad8", 3);
    public static NumberSetting crosshairGap = new NumberSetting("crosshairGap", "\u6307\u9488\u95f4\u9694", 0);
    public static NumberSetting crosshairThickness = new NumberSetting("crosshairThickness", "\u6307\u9488\u7c97\u7ec6", 1);
    public static NumberSetting crosshairDotSize = new NumberSetting("crosshairDotSize", "\u6307\u9488\u4e2d\u5fc3\u70b9\u5c3a\u5bf8", 0);
    public static BooleanSetting crosshairOutline = new BooleanSetting("crosshairOutline", "\u6307\u9488\u8f6e\u5ed3", false);
    public static BooleanSetting crosshairDot = new BooleanSetting("crosshairDot", "\u6307\u9488\u4e2d\u5fc3\u70b9", false);
    public static ColorSetting crosshairCrosshairColor = new ColorSetting("crosshairCrosshairColor", "\u6307\u9488\u989c\u8272", -15732736, false).enableAlpha();
    public static ColorSetting crosshairOutlineColor = new ColorSetting("crosshairOutlineColor", "\u8f6e\u5ed3\u989c\u8272", -65536, false).enableAlpha();
    public static ColorSetting crosshairDotColor = new ColorSetting("crosshairDotColor", "\u4e2d\u5fc3\u70b9\u989c\u8272", -1, false).enableAlpha();
    public static NumberSetting crosshairOutlineThickness = new NumberSetting("crosshairOutlineThickness", "\u6307\u9488\u8f6e\u5ed3\u7c97\u7ec6", 1);
    public static NumberSetting crosshairSize = new NumberSetting("crosshairSize", "\u6307\u9488\u5927\u5c0f", 1){

        @Override
        public Number value() {
            return Float.valueOf(1.0f);
        }
    };
    public static BooleanSetting crosshairShowIn3rdPerson = new BooleanSetting("crosshairShowIn3rdPerson", "\u7b2c\u4e09\u4eba\u79f0\u663e\u793a\u6307\u9488", true);
    public static BooleanSetting crosshairShowInGuis = new BooleanSetting("crosshairShowInGuis", "\u6253\u5f00\u754c\u9762\u65f6\u663e\u793a\u6307\u9488", true);
    public static BooleanSetting crosshairHighlightHostile = new BooleanSetting("crosshairHighlightHostile", "\u9ad8\u4eae\u654c\u5bf9\u751f\u7269", true);
    public static BooleanSetting crosshairHighlightFriend = new BooleanSetting("crosshairHighlightFriend", "\u9ad8\u4eae\u53cb\u597d\u751f\u7269", true);
    public static BooleanSetting crosshairHighlightPlayer = new BooleanSetting("crosshairHighlightPlayer", "\u9ad8\u4eae\u73a9\u5bb6", true);
    public static ColorSetting crosshairHostileColor = new ColorSetting("crosshairHostileColor", "\u9ad8\u4eae\u654c\u5bf9\u751f\u7269\u989c\u8272", -15732736, false);
    public static ColorSetting crosshairFriendColor = new ColorSetting("crosshairFriendColor", "\u9ad8\u4eae\u53cb\u597d\u751f\u7269\u989c\u8272", -15732736, false);
    public static ColorSetting crosshairPlayerColor = new ColorSetting("crosshairPlayerColor", "\u9ad8\u4eae\u73a9\u5bb6\u989c\u8272", -15732736, false);
    public static BooleanSetting crosshairDynamicBowAnimation = new BooleanSetting("crosshairDynamicBowAnimation", "\u52a8\u6001\u5f13\u7bad\u6307\u9488", true);
    public static BooleanSetting crosshairDynamicAttackAnimation = new BooleanSetting("crosshairDynamicAttackAnimation", "\u52a8\u6001\u653b\u51fb\u6307\u9488", true);
    public static BooleanSetting itemCounterEnabled = new BooleanSetting("itemCounterEnabled", "\u7269\u54c1\u8ba1\u6570\u5668", false);
    public static NumberSetting itemCounterX = new NumberSetting("itemCounterX", null, Float.valueOf(0.1f));
    public static NumberSetting itemCounterY = new NumberSetting("itemCounterY", null, Float.valueOf(0.7f));
    public static BooleanSetting itemCounterhorizontalAlignment = new BooleanSetting("itemCounterhorizontalAlignment", "\u662f\u5426\u6c34\u5e73\u7ed8\u5236", false);
    public static NumberSetting itemCounterScale = new NumberSetting("itemCounterScale", "\u7269\u54c1\u8ba1\u6570\u5668\u5927\u5c0f", Float.valueOf(1.0f));
    public static ColorSetting itemCounterColor = new ColorSetting("itemCounterColor", "\u7269\u54c1\u8ba1\u6570\u5668\u5b57\u4f53\u989c\u8272", -1, false).enableChroma();
    public static ColorSetting itemCounterBackgroundColor = new ColorSetting("itemCounterBackgroundColor", "\u7269\u54c1\u8ba1\u6570\u5668\u5b57\u4f53\u80cc\u666f", 0x5F000000, false).enableAlpha();
    public static ItemCounterSetting itemCounterItems = new ItemCounterSetting("itemCounterItems", "\u7269\u54c1\u663e\u793a\u5185\u5bb9", Arrays.asList(new ItemCounterSetting.DisplayItem(1, false, "minecraft:iron_ingot"), new ItemCounterSetting.DisplayItem(2, false, "minecraft:diamond")));
    public static BooleanSetting saturationEnabled = new BooleanSetting("saturationEnabled", "\u9971\u548c\u5ea6\u663e\u793a", false);
    public static NumberSetting saturationX = new NumberSetting("saturationX", null, Float.valueOf(0.5f));
    public static NumberSetting saturationY = new NumberSetting("saturationY", null, Float.valueOf(0.9f));
    public static NumberSetting saturationScale = new NumberSetting("saturationScale", "\u9971\u548c\u5ea6\u663e\u793a\u5927\u5c0f", Float.valueOf(1.0f));
    public static BooleanSetting blockOverlayEnabled = new BooleanSetting("blockOverlayEnabled", "\u65b9\u5757\u8fb9\u7f18", false);
    public static ColorSetting blockOverlayColor = new ColorSetting("blockOverlayColor", "\u65b9\u5757\u8fb9\u7f18\u989c\u8272", -1, false).enableAlpha().enableChroma();
    public static NumberSetting blockOverlayLineWidth = new NumberSetting("blockOverlayLineWidth", "\u65b9\u5757\u8fb9\u7f18\u7ebf\u5bbd", Float.valueOf(2.0f));
    public static EnumSetting<BlockOverlayMode> blockOverlayMode = new EnumSetting("blockOverlayMode", "\u65b9\u5757\u8fb9\u7f18\u6e32\u67d3\u6a21\u5f0f", BlockOverlayMode.DEFAULT);
    private static final Gson GSON = new Gson();
    private static final File CONFIG_FILE = new File("./uviewconfig.json");

    public static String getConfigJson() {
        try {
            return FileUtils.readFileToString((File)CONFIG_FILE, (String)"UTF-8");
        }
        catch (Exception ex) {
            return new JsonObject().toString();
        }
    }

    public static void loadConfig(String json) {
        try {
            JsonObject jsonObject = (JsonObject)GSON.fromJson(json, JsonObject.class);
            for (Field field : UViewSettings.class.getFields()) {
                if (!ISetting.class.isAssignableFrom(field.getType())) continue;
                ISetting setting = (ISetting)field.get(null);
                if (setting instanceof StringSetting) {
                    setting.setValue(UViewSettings.getString(jsonObject.get(setting.getPath()), (String)setting.defaultValue()));
                    continue;
                }
                if (setting instanceof BooleanSetting) {
                    setting.setValue(UViewSettings.getBoolean(jsonObject.get(setting.getPath()), (Boolean)setting.defaultValue()));
                    continue;
                }
                if (setting instanceof NumberSetting) {
                    setting.setValue(UViewSettings.getNumber(jsonObject.get(setting.getPath()), (Number)setting.defaultValue()));
                    continue;
                }
                if (setting instanceof ColorSetting) {
                    setting.setValue(UViewSettings.getInteger(jsonObject.get(setting.getPath()), (Integer)setting.defaultValue()));
                    ((ColorSetting)setting).setChroma(UViewSettings.getBoolean(jsonObject.get(setting.getPath() + "_chroma"), ((ColorSetting)setting).defaultChroma()));
                    continue;
                }
                if (setting instanceof ItemCounterSetting) {
                    setting.setValue(UViewSettings.getList(jsonObject.get(setting.getPath()), ((ItemCounterSetting)setting).defaultValue(), ItemCounterSetting.DisplayItem.class));
                    continue;
                }
                if (!(setting instanceof EnumSetting)) continue;
                setting.setValue(Enum.valueOf(((EnumSetting)setting).getClassOfType(), UViewSettings.getString(jsonObject.get(setting.getPath()), ((Enum)setting.defaultValue()).name())));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static <T> List<T> getList(JsonElement element, List<T> def, Class<T> clazzOfType) {
        ArrayList<Object> list = new ArrayList<Object>();
        try {
            JsonArray jsonArray = element.getAsJsonArray();
            for (JsonElement jsonElement : jsonArray) {
                list.add(GSON.fromJson(jsonElement, clazzOfType));
            }
        }
        catch (Exception ignored) {
            return def;
        }
        return (List<T>) list;
    }

    private static boolean getBoolean(JsonElement element, boolean def) {
        try {
            return element.getAsBoolean();
        }
        catch (Exception ignored) {
            return def;
        }
    }

    private static String getString(JsonElement element, String def) {
        try {
            return element.getAsString();
        }
        catch (Exception ignored) {
            return def;
        }
    }

    private static Integer getInteger(JsonElement element, Integer def) {
        try {
            return element.getAsInt();
        }
        catch (Exception ignored) {
            return def;
        }
    }

    private static Number getNumber(JsonElement element, Number def) {
        try {
            return element.getAsNumber();
        }
        catch (Exception ignored) {
            return def;
        }
    }

    public static void saveConfig() {
        try {
            JsonObject jsonObject = new JsonObject();
            for (Field field : UViewSettings.class.getFields()) {
                if (!ISetting.class.isAssignableFrom(field.getType())) continue;
                ISetting setting = (ISetting)field.get(null);
                Object value = setting.value();
                if (value instanceof String) {
                    jsonObject.addProperty(setting.getPath(), (String)value);
                } else if (value instanceof Boolean) {
                    jsonObject.addProperty(setting.getPath(), (Boolean)value);
                } else if (value instanceof Number) {
                    jsonObject.addProperty(setting.getPath(), (Number)value);
                } else if (value instanceof List) {
                    JsonArray jsonArray = new JsonArray();
                    List list = (List)value;
                    for (Object o : list) {
                        jsonArray.add(GSON.toJsonTree(o));
                    }
                    jsonObject.add(setting.getPath(), (JsonElement)jsonArray);
                } else if (value instanceof Enum) {
                    jsonObject.addProperty(setting.getPath(), ((Enum)value).name());
                }
                if (!(setting instanceof ColorSetting)) continue;
                jsonObject.addProperty(setting.getPath() + "_chroma", ((ColorSetting)setting).isChroma());
            }
            FileUtils.writeStringToFile((File)CONFIG_FILE, (String)jsonObject.toString(), (String)"UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

