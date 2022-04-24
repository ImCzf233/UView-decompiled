// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiIngame
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
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Saturation
extends AbstractModule
implements IRenderer {
    public static final Saturation INSTANCE = new Saturation();
    private static final GuiIngame guiIngame = new GuiIngame(Minecraft.getMinecraft());
    private final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void onLoad() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Pre event) {
        if (this.isDisplable() && UViewSettings.saturationEnabled.value().booleanValue() && event.type == RenderGameOverlayEvent.ElementType.TEXT) {
            this.renderInternal(UViewSettings.saturationX.value().floatValue() * (float)event.resolution.getScaledWidth(), UViewSettings.saturationY.value().floatValue() * (float)event.resolution.getScaledHeight(), (int)this.mc.thePlayer.getFoodStats().getSaturationLevel());
        }
    }

    @Override
    public NumberSetting getX() {
        return UViewSettings.saturationX;
    }

    @Override
    public NumberSetting getY() {
        return UViewSettings.saturationY;
    }

    @Override
    public NumberSetting getScale() {
        return UViewSettings.saturationScale;
    }

    @Override
    public int getHeight() {
        return (int)(10.0f * this.getScale().value().floatValue());
    }

    @Override
    public int getWidth() {
        return (int)(83.0f * this.getScale().value().floatValue());
    }

    @Override
    public boolean isGuiEnabled() {
        return UViewSettings.saturationEnabled.value();
    }

    @Override
    public void render(Minecraft minecraft, float x, float y) {
        this.renderInternal(x, y, 20);
    }

    public void renderInternal(float x, float y, int food) {
        if (food > 1) {
            int j = food / 2;
            this.mc.getTextureManager().bindTexture(Gui.icons);
            for (int i = 0; i < j; ++i) {
                GlStateManager.pushMatrix();
                GlStateManager.translate((float)(x + 1.0f + (float)(8 * i) * this.getScale().value().floatValue()), (float)(y + 1.0f), (float)0.0f);
                GlStateManager.scale((float)this.getScale().value().floatValue(), (float)this.getScale().value().floatValue(), (float)this.getScale().value().floatValue());
                GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                guiIngame.drawTexturedModalRect(0, 0, 16, 27, 9, 9);
                guiIngame.drawTexturedModalRect(0, 0, 52, 27, 9, 9);
                GlStateManager.popMatrix();
            }
        }
    }
}

