// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.network.NetHandlerPlayClient
 *  net.minecraft.client.network.NetworkPlayerInfo
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
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PingDisplay
extends AbstractModule
implements IRenderer {
    public static final PingDisplay INSTANCE = new PingDisplay();
    private final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void onLoad() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Pre event) {
        if (this.isDisplable() && UViewSettings.pingDisplayEnabled.value().booleanValue() && event.type == RenderGameOverlayEvent.ElementType.TEXT) {
            this.render(this.mc, UViewSettings.pingDisplayX.value().floatValue() * (float)event.resolution.getScaledWidth(), UViewSettings.pingDisplayY.value().floatValue() * (float)event.resolution.getScaledHeight());
        }
    }

    @Override
    public NumberSetting getX() {
        return UViewSettings.pingDisplayX;
    }

    @Override
    public NumberSetting getY() {
        return UViewSettings.pingDisplayY;
    }

    @Override
    public NumberSetting getScale() {
        return UViewSettings.pingDisplayScale;
    }

    @Override
    public int getHeight() {
        if (!UViewSettings.pingDisplayBackground.value().booleanValue()) {
            return (int)((float)Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT * this.getScale().value().floatValue());
        }
        return (int)(16.0f * UViewSettings.pingDisplayScale.value().floatValue());
    }

    @Override
    public int getWidth() {
        if (!UViewSettings.pingDisplayBackground.value().booleanValue()) {
            return (int)((float)Minecraft.getMinecraft().fontRendererObj.getStringWidth("0ms") * this.getScale().value().floatValue());
        }
        return (int)(70.0f * UViewSettings.pingDisplayScale.value().floatValue());
    }

    @Override
    public boolean isGuiEnabled() {
        return UViewSettings.pingDisplayEnabled.value();
    }

    @Override
    public void render(Minecraft minecraft, float x, float y) {
        NetworkPlayerInfo playerInfo;
        int offsetX = 0;
        int offsetY = 0;
        if (UViewSettings.pingDisplayBackground.value().booleanValue()) {
            offsetX = this.getWidth();
            offsetY = this.getHeight();
            Gui.drawRect((int)((int)x), (int)((int)y), (int)((int)x + offsetX), (int)((int)y + offsetY), (int)UViewSettings.pingDisplayBackgroundColor.value());
        }
        GlStateManager.pushMatrix();
        String ping = null;
        NetHandlerPlayClient netHandler = this.mc.getNetHandler();
        if (netHandler != null && (playerInfo = netHandler.getPlayerInfo(Minecraft.getMinecraft().getSession().getProfile().getId())) != null) {
            ping = Integer.toString(playerInfo.getResponseTime());
        }
        String text = ping == null ? "\u65e0" : ping + "ms";
        GlStateManager.translate((float)(x + ((float)offsetX - (UViewSettings.pingDisplayBackground.value() != false ? (float)this.mc.fontRendererObj.getStringWidth(text) * this.getScale().value().floatValue() : 0.0f)) / 2.0f), (float)(y + ((float)offsetY - (UViewSettings.pingDisplayBackground.value() != false ? (float)this.mc.fontRendererObj.FONT_HEIGHT * this.getScale().value().floatValue() : 0.0f)) / 2.0f), (float)0.0f);
        GlStateManager.scale((float)this.getScale().value().floatValue(), (float)this.getScale().value().floatValue(), (float)this.getScale().value().floatValue());
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        if (UViewSettings.pingDisplayColor.isChroma().booleanValue()) {
            ChromaUtils.drawChromaString(text, 0.0f, 0.0f, false, true);
        } else {
            this.mc.fontRendererObj.drawStringWithShadow(text, 0.0f, 0.0f, UViewSettings.pingDisplayColor.value().intValue());
        }
        GlStateManager.popMatrix();
    }
}

