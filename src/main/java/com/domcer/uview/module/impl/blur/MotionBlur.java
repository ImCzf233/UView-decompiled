// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.EntityRenderer
 *  net.minecraft.client.resources.FallbackResourceManager
 *  net.minecraft.client.resources.SimpleReloadableResourceManager
 *  net.minecraft.client.resources.data.IMetadataSerializer
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.relauncher.ReflectionHelper
 */
package com.domcer.uview.module.impl.blur;

import com.domcer.uview.module.AbstractModule;
import com.domcer.uview.resource.pack.MotionBlurResourceManager;
import com.domcer.uview.settings.UViewSettings;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.resources.FallbackResourceManager;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class MotionBlur
extends AbstractModule {
    public static MotionBlur INSTANCE = new MotionBlur();
    private Map<String, FallbackResourceManager> domainResourceManagers;
    private Field cachedFastRender;
    private Method _loadShader;

    @Override
    public void onLoad() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        try {
           this._loadShader.setAccessible(true);
            this.cachedFastRender = GameSettings.class.getDeclaredField("ofFastRender");
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @SubscribeEvent
    public void tick(TickEvent.ClientTickEvent event) {
    }

    public boolean isFastRenderEnabled() {
        try {
            return this.cachedFastRender.getBoolean(Minecraft.getMinecraft().gameSettings);
        }
        catch (Exception ignored) {
            return false;
        }
    }

    public void turnOn() {
        EntityRenderer entityRenderer = Minecraft.getMinecraft().entityRenderer;
        if (!UViewSettings.motionBlurSetting.value().booleanValue() || UViewSettings.motionBlurAmount.value().intValue() <= 0 || this.isFastRenderEnabled() || entityRenderer == null || entityRenderer.isShaderActive()) {
            return;
        }
        try {
            this._loadShader.invoke(entityRenderer, new ResourceLocation("motionblur", "motionblur"));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

