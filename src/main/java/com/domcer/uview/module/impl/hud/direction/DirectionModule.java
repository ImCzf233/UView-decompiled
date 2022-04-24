// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Pre
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.domcer.uview.module.impl.hud.direction;

import com.domcer.uview.module.AbstractModule;
import com.domcer.uview.module.IRenderer;
import com.domcer.uview.settings.UViewSettings;
import com.domcer.uview.settings.types.NumberSetting;
import com.domcer.uview.util.ChromaUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DirectionModule
extends AbstractModule
implements IRenderer {
    public static final DirectionModule INSTANCE = new DirectionModule();
    private final String[] directions = new String[]{"\u5317", "\u4e1c\u5317", "\u4e1c", "\u4e1c\u5357", "\u5357", "\u897f\u5357", "\u897f", "\u897f\u5317"};

    @Override
    public void onLoad() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Pre event) {
        if (UViewSettings.directionHudEnabled.value().booleanValue() && this.isDisplable() && event.type == RenderGameOverlayEvent.ElementType.TEXT) {
            this.renderDirection(this.getDirection(), (float)((double)UViewSettings.directionHudX.value().floatValue() * event.resolution.getScaledWidth_double()), (float)((double)UViewSettings.directionHudY.value().floatValue() * event.resolution.getScaledHeight_double()));
        }
    }

    public void renderDirection(String direction, float x, float y) {
        GlStateManager.pushMatrix();
        Minecraft mc = Minecraft.getMinecraft();
        GlStateManager.translate((float)((float)((int)x) + (direction.length() == 1 ? (float)mc.fontRendererObj.getStringWidth(direction) / 2.0f : 0.0f)), (float)y, (float)0.0f);
        GlStateManager.scale((float)this.getScale().value().floatValue(), (float)this.getScale().value().floatValue(), (float)this.getScale().value().floatValue());
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        if (UViewSettings.directionHudColor.isChroma().booleanValue()) {
            ChromaUtils.drawChromaString(direction, 0.0f, 0.0f, false, true);
        } else {
            mc.fontRendererObj.drawString(direction, 0, 0, UViewSettings.directionHudColor.value().intValue());
        }
        GlStateManager.popMatrix();
    }

    public String getDirection() {
        int yaw;
        for (yaw = (int)(Minecraft.getMinecraft().thePlayer.getRotationYawHead() + 180.0f); yaw < 0; yaw += 360) {
        }
        int partSize = 360 / this.directions.length;
        int index = (yaw + partSize / 2) / partSize;
        return this.directions[index % this.directions.length];
    }

    @Override
    public NumberSetting getX() {
        return UViewSettings.directionHudX;
    }

    @Override
    public NumberSetting getY() {
        return UViewSettings.directionHudY;
    }

    @Override
    public NumberSetting getScale() {
        return UViewSettings.directionHudScale;
    }

    @Override
    public int getHeight() {
        return (int)((float)Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT * this.getScale().value().floatValue());
    }

    @Override
    public int getWidth() {
        return (int)((float)Minecraft.getMinecraft().fontRendererObj.getStringWidth(this.directions[1]) * this.getScale().value().floatValue());
    }

    @Override
    public boolean isGuiEnabled() {
        return UViewSettings.directionHudEnabled.value();
    }

    @Override
    public void render(Minecraft minecraft, float x, float y) {
        this.renderDirection(this.directions[1], x, y);
    }
}

