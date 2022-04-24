// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Pre
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.domcer.uview.module.impl.hud;

import com.domcer.uview.module.AbstractModule;
import com.domcer.uview.module.IRenderer;
import com.domcer.uview.settings.UViewSettings;
import com.domcer.uview.settings.types.NumberSetting;
import com.domcer.uview.util.ChromaUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FPSDisplay
extends AbstractModule
implements IRenderer {
    public static final FPSDisplay INSTANCE = new FPSDisplay();
    private final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void onLoad() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Pre event) {
        if (this.isDisplable() && UViewSettings.fpsDisplayEnabled.value().booleanValue() && event.type == RenderGameOverlayEvent.ElementType.TEXT) {
            this.render(this.mc, UViewSettings.fpsDisplayX.value().floatValue() * (float)event.resolution.getScaledWidth(), UViewSettings.fpsDisplayY.value().floatValue() * (float)event.resolution.getScaledHeight());
        }
    }

    @Override
    public NumberSetting getX() {
        return UViewSettings.fpsDisplayX;
    }

    @Override
    public NumberSetting getY() {
        return UViewSettings.fpsDisplayY;
    }

    @Override
    public NumberSetting getScale() {
        return UViewSettings.fpsDisplayScale;
    }

    @Override
    public int getHeight() {
        if (!UViewSettings.fpsDisplayBackground.value().booleanValue()) {
            return (int)((float)Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT * this.getScale().value().floatValue());
        }
        return (int)(16.0f * UViewSettings.fpsDisplayScale.value().floatValue());
    }

    @Override
    public int getWidth() {
        if (!UViewSettings.pingDisplayBackground.value().booleanValue()) {
            return (int)((float)Minecraft.getMinecraft().fontRendererObj.getStringWidth(Minecraft.getDebugFPS() + " FPS") * this.getScale().value().floatValue());
        }
        return (int)(70.0f * UViewSettings.fpsDisplayScale.value().floatValue());
    }

    @Override
    public boolean isGuiEnabled() {
        return UViewSettings.fpsDisplayEnabled.value();
    }

    @Override
    public void render(Minecraft minecraft, float x, float y) {
        int offsetX = 0;
        int offsetY = 0;
        if (UViewSettings.fpsDisplayBackground.value().booleanValue()) {
            offsetX = this.getWidth();
            offsetY = this.getHeight();
            Gui.drawRect((int)((int)x), (int)((int)y), (int)((int)x + offsetX), (int)((int)y + offsetY), (int)UViewSettings.fpsDisplayBackgroundColor.value());
        }
        GlStateManager.pushMatrix();
        String text = Minecraft.getDebugFPS() + " FPS";
        GlStateManager.translate((float)(x + ((float)offsetX - (UViewSettings.fpsDisplayBackground.value() != false ? (float)this.mc.fontRendererObj.getStringWidth(text) * this.getScale().value().floatValue() : 0.0f)) / 2.0f), (float)(y + ((float)offsetY - (UViewSettings.fpsDisplayBackground.value() != false ? (float)this.mc.fontRendererObj.FONT_HEIGHT * this.getScale().value().floatValue() : 0.0f)) / 2.0f), (float)0.0f);
        GlStateManager.scale((float)this.getScale().value().floatValue(), (float)this.getScale().value().floatValue(), (float)this.getScale().value().floatValue());
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        if (UViewSettings.fpsDisplayColor.isChroma().booleanValue()) {
            ChromaUtils.drawChromaString(text, 0.0f, 0.0f, false, true);
        } else {
            this.mc.fontRendererObj.drawStringWithShadow(text, 0.0f, 0.0f, UViewSettings.fpsDisplayColor.value().intValue());
        }
        GlStateManager.popMatrix();
    }
}

