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
 *  net.minecraft.util.MathHelper
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Pre
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  org.lwjgl.input.Mouse
 */
package com.domcer.uview.module.impl.hud;

import com.domcer.uview.module.AbstractModule;
import com.domcer.uview.module.IRenderer;
import com.domcer.uview.module.impl.hud.direction.DirectionModule;
import com.domcer.uview.settings.UViewSettings;
import com.domcer.uview.settings.types.NumberSetting;
import com.domcer.uview.util.ChromaUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Mouse;

public class Coordinates
extends AbstractModule
implements IRenderer {
    public static final Coordinates INSTANCE = new Coordinates();
    private final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void onLoad() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Pre event) {
        if (this.isDisplable() && UViewSettings.coordinatesEnabled.value().booleanValue() && event.type == RenderGameOverlayEvent.ElementType.TEXT) {
            this.render(this.mc, UViewSettings.coordinatesX.value().floatValue() * (float)event.resolution.getScaledWidth(), UViewSettings.coordinatesY.value().floatValue() * (float)event.resolution.getScaledHeight());
        }
    }

    @Override
    public NumberSetting getX() {
        return UViewSettings.coordinatesX;
    }

    @Override
    public NumberSetting getY() {
        return UViewSettings.coordinatesY;
    }

    @Override
    public NumberSetting getScale() {
        return UViewSettings.coordinatesScale;
    }

    @Override
    public int getHeight() {
        return (int)(32.0f * UViewSettings.coordinatesScale.value().floatValue());
    }

    @Override
    public int getWidth() {
        return (int)((float)(UViewSettings.coordinatesTrend.value() != false ? 46 : 20) * UViewSettings.coordinatesScale.value().floatValue());
    }

    @Override
    public boolean isGuiEnabled() {
        return UViewSettings.coordinatesEnabled.value();
    }

    @Override
    public void render(Minecraft mc, float x, float y) {
        String direction;
        Mouse.poll();
        if (UViewSettings.coordinatesBackground.value().booleanValue()) {
            int offsetX = this.getWidth();
            int offsetY = this.getHeight();
            Gui.drawRect((int)((int)x), (int)((int)y), (int)((int)x + offsetX), (int)((int)y + offsetY), (int)UViewSettings.coordinatesBackgroundColor.value());
        }
        int posX = MathHelper.floor_double((double)mc.thePlayer.posX);
        int posY = MathHelper.floor_double((double)mc.thePlayer.getEntityBoundingBox().minY);
        int posZ = MathHelper.floor_double((double)mc.thePlayer.posZ);
        this.drawString("X: " + posX, (int)(x + 2.0f * UViewSettings.coordinatesScale.value().floatValue()), (int)y);
        this.drawString("Y: " + posY, (int)(x + 2.0f * UViewSettings.coordinatesScale.value().floatValue()), (int)(y + (float)(mc.fontRendererObj.FONT_HEIGHT + 2) * UViewSettings.coordinatesScale.value().floatValue()));
        this.drawString("Z: " + posZ, (int)(x + 2.0f * UViewSettings.coordinatesScale.value().floatValue()), (int)(y + (float)((mc.fontRendererObj.FONT_HEIGHT + 2) * 2) * UViewSettings.coordinatesScale.value().floatValue()));
        if (!UViewSettings.coordinatesTrend.value().booleanValue()) {
            return;
        }
        String xDir = null;
        String zDir = null;
        switch (direction = DirectionModule.INSTANCE.getDirection()) {
            case "\u5317": {
                zDir = "--";
                break;
            }
            case "\u4e1c\u5317": {
                xDir = "+";
                zDir = "-";
                break;
            }
            case "\u4e1c": {
                xDir = "++";
                break;
            }
            case "\u4e1c\u5357": {
                xDir = "+";
                zDir = "+";
                break;
            }
            case "\u5357": {
                zDir = "++";
                break;
            }
            case "\u897f\u5357": {
                xDir = "-";
                zDir = "+";
                break;
            }
            case "\u897f": {
                xDir = "--";
                break;
            }
            case "\u897f\u5317": {
                xDir = "-";
                zDir = "-";
            }
        }
        float fontWidth = (float)mc.fontRendererObj.getStringWidth("X: 00000") * this.getScale().value().floatValue();
        if (xDir != null) {
            this.drawString(xDir, (int)(x + (fontWidth + 5.0f) * UViewSettings.coordinatesScale.value().floatValue()), (int)y);
        }
        if (zDir != null) {
            this.drawString(zDir, (int)(x + (fontWidth + 5.0f) * UViewSettings.coordinatesScale.value().floatValue()), (int)(y + (float)((mc.fontRendererObj.FONT_HEIGHT + 2) * 2) * UViewSettings.coordinatesScale.value().floatValue()));
        }
    }

    private void drawString(String text, int x, int y) {
        GlStateManager.pushMatrix();
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.translate((float)x, (float)y, (float)0.0f);
        GlStateManager.scale((float)this.getScale().value().floatValue(), (float)this.getScale().value().floatValue(), (float)this.getScale().value().floatValue());
        if (UViewSettings.coordinatesColor.isChroma().booleanValue()) {
            ChromaUtils.drawChromaString(text, 0.0f, 0.0f, false, true);
        } else {
            this.mc.fontRendererObj.drawStringWithShadow(text, 0.0f, 0.0f, UViewSettings.coordinatesColor.value().intValue());
        }
        GlStateManager.popMatrix();
    }
}

