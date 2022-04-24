// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiChat
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.EntityRenderer
 *  net.minecraft.client.resources.IResourceManagerReloadListener
 *  net.minecraft.client.resources.SimpleReloadableResourceManager
 *  net.minecraft.client.shader.Shader
 *  net.minecraft.client.shader.ShaderGroup
 *  net.minecraft.client.shader.ShaderUniform
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.event.GuiOpenEvent
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.common.gameevent.TickEvent$RenderTickEvent
 *  net.minecraftforge.fml.relauncher.ReflectionHelper
 *  org.apache.commons.lang3.ArrayUtils
 */
package com.domcer.uview.module.impl.blur;

import com.domcer.uview.module.AbstractModule;
import com.domcer.uview.resource.pack.ShaderResourcePack;
import com.domcer.uview.settings.UViewSettings;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.client.shader.Shader;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.shader.ShaderUniform;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import org.apache.commons.lang3.ArrayUtils;

public class GuiBlur
extends AbstractModule {
    public static GuiBlur INSTANCE = new GuiBlur();
    private String[] blurExclusions;
    private Field _listShaders;
    private Method _loadShader;
    private long start;
    private boolean lastGuiOpened = false;
    private boolean motionEnabled = true;

    @Override
    public void onLoad() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        try {
            ((SimpleReloadableResourceManager)Minecraft.getMinecraft().getResourceManager()).registerReloadListener((IResourceManagerReloadListener)ShaderResourcePack.INSTANCE);
            this._listShaders = ReflectionHelper.findField(ShaderGroup.class, (String[])new String[]{"listShaders", "listShaders"});
            this._listShaders.setAccessible(true);
            this._loadShader.setAccessible(true);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.blurExclusions = new String[]{GuiChat.class.getName(), "net.optifine.gui.GuiChatOF"};
    }

    @SubscribeEvent
    public void onGuiChange(GuiOpenEvent event) {
        EntityRenderer er;
        boolean excluded;
        this.lastGuiOpened = true;
        this.motionEnabled = false;
        boolean bl = excluded = event.gui == null || ArrayUtils.contains((Object[])this.blurExclusions, (Object)event.gui.getClass().getName());
        if (UViewSettings.blurSetting.value().booleanValue() && Minecraft.getMinecraft().theWorld != null) {
            EntityRenderer er2 = Minecraft.getMinecraft().entityRenderer;
            if (!er2.isShaderActive() && !excluded) {
                try {
                    this._loadShader.invoke(er2, new ResourceLocation("shaders/post/fade_in_blur.json"));
                }
                catch (Exception exception) {
                    // empty catch block
                }
                this.start = System.currentTimeMillis();
            }
        }
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }
        if (this.lastGuiOpened && Minecraft.getMinecraft().currentScreen == null) {
            this.lastGuiOpened = false;
            this.motionEnabled = true;
        }
        if (UViewSettings.blurSetting.value().booleanValue() && Minecraft.getMinecraft().currentScreen != null && Minecraft.getMinecraft().entityRenderer.isShaderActive()) {
            try {
                List<Shader> shaders = (List)this._listShaders.get(Minecraft.getMinecraft().entityRenderer.getShaderGroup());
                for (Shader s : shaders) {
                    ShaderUniform su = s.getShaderManager().getShaderUniform("Progress");
                    if (su == null) continue;
                    su.set(this.getProgress());
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private float getProgress() {
        if (!UViewSettings.blurSetting.value().booleanValue() || UViewSettings.blurSpeed.value().intValue() >= 60000 || this.motionEnabled) {
            return 0.0f;
        }
        return Math.min((float)(System.currentTimeMillis() - this.start) / (float)UViewSettings.blurSpeed.value().longValue(), 1.0f);
    }

    public static void drawBlurBackground() {
        ScaledResolution resolution = new ScaledResolution(Minecraft.getMinecraft());
        Gui.drawRect((int)0, (int)0, (int)resolution.getScaledWidth(), (int)resolution.getScaledHeight(), (int)0x13000000);
    }
}

