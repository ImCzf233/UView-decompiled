// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.passive.EntityAmbientCreature
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Pre
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.InputEvent$MouseInputEvent
 *  org.lwjgl.input.Mouse
 */
package com.domcer.uview.module.impl.game.crosshair;

import com.domcer.uview.module.AbstractModule;
import com.domcer.uview.settings.UViewSettings;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Mouse;

public class Crosshair
extends AbstractModule {
    public static final Crosshair INSTANCE = new Crosshair();
    private float percentDone = 0.0f;

    @Override
    public void onLoad() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    public void render(ScaledResolution sr) {
        if (UViewSettings.crosshairEnabled.value().booleanValue()) {
            this.renderCrosshair(sr, UViewSettings.crosshairType.value().intValue());
        } else {
            this.renderDefaultCrosshair(sr);
        }
    }

    @SubscribeEvent
    public void onRenderEvent(RenderGameOverlayEvent.Pre event) {
        if (event.type == RenderGameOverlayEvent.ElementType.CROSSHAIRS) {
            Minecraft mc = Minecraft.getMinecraft();
            if (!UViewSettings.crosshairShowIn3rdPerson.value().booleanValue() && mc.gameSettings.thirdPersonView != 0 || !UViewSettings.crosshairShowInGuis.value().booleanValue() && mc.currentScreen != null) {
                event.setCanceled(true);
                return;
            }
            try {
                this.render(event.resolution);
                event.setCanceled(true);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @SubscribeEvent
    public void onMouseInput(InputEvent.MouseInputEvent event) {
        if (!UViewSettings.crosshairDynamicAttackAnimation.value().booleanValue()) {
            return;
        }
        int leftClick = Minecraft.getMinecraft().gameSettings.keyBindAttack.getKeyCode();
        if (Mouse.getEventButtonState() && Mouse.getEventButton() == leftClick + 100 && this.percentDone < 1.0f) {
            this.percentDone = 1.0f;
        }
    }

    public void renderDefaultCrosshair(ScaledResolution scaledResolution) {
        Minecraft mc = Minecraft.getMinecraft();
        mc.getTextureManager().bindTexture(Gui.icons);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate((int)775, (int)769, (int)1, (int)0);
        GlStateManager.enableAlpha();
        mc.ingameGUI.drawTexturedModalRect(scaledResolution.getScaledWidth() / 2 - 7, scaledResolution.getScaledHeight() / 2 - 7, 0, 0, 16, 16);
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        GlStateManager.disableBlend();
    }

    public void renderCrosshair(ScaledResolution scaledResolution, int number) {
        Minecraft mc = Minecraft.getMinecraft();
        float scale = UViewSettings.crosshairSize.value().floatValue();
        GlStateManager.pushMatrix();
        GlStateManager.enableAlpha();
        GlStateManager.scale((float)scale, (float)scale, (float)1.0f);
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        int color = this.getColor();
        int outlineColor = UViewSettings.crosshairOutlineColor.value();
        int width = UViewSettings.crosshairWidth.value().intValue();
        int height = UViewSettings.crosshairHeight.value().intValue();
        int thick = UViewSettings.crosshairThickness.value().intValue();
        int gap = UViewSettings.crosshairGap.value().intValue();
        int outlineThick = UViewSettings.crosshairOutlineThickness.value().intValue();
        if (UViewSettings.crosshairDot.value().booleanValue()) {
            int dot = UViewSettings.crosshairDotSize.value().intValue();
            int halfDot = dot / 2;
            if (thick > 0) {
                Gui.drawRect((int)(scaledResolution.getScaledWidth() / 2 - halfDot - outlineThick), (int)(scaledResolution.getScaledHeight() / 2 - halfDot - outlineThick), (int)(scaledResolution.getScaledWidth() / 2 + halfDot + outlineThick + dot % 2), (int)(scaledResolution.getScaledHeight() / 2 + halfDot + outlineThick + dot % 2), (int)outlineColor);
            }
            Gui.drawRect((int)(scaledResolution.getScaledWidth() / 2 - halfDot), (int)(scaledResolution.getScaledHeight() / 2 - halfDot), (int)(scaledResolution.getScaledWidth() / 2 + halfDot + dot % 2), (int)(scaledResolution.getScaledHeight() / 2 + halfDot + dot % 2), (int)color);
        }
        int offsetWidth = (int)(this.percentDone * (float)width);
        int offsetHeight = (int)(this.percentDone * (float)height);
        int halfThick = thick / 2;
        if (thick > 0) {
            Gui.drawRect((int)(scaledResolution.getScaledWidth() / 2 - gap - width - outlineThick - offsetWidth), (int)(scaledResolution.getScaledHeight() / 2 - halfThick - outlineThick), (int)(scaledResolution.getScaledWidth() / 2 - gap + outlineThick - offsetWidth + thick % 2), (int)(scaledResolution.getScaledHeight() / 2 + halfThick + outlineThick + thick % 2), (int)outlineColor);
            Gui.drawRect((int)(scaledResolution.getScaledWidth() / 2 - halfThick - outlineThick), (int)(scaledResolution.getScaledHeight() / 2 - gap - height - outlineThick - offsetHeight), (int)(scaledResolution.getScaledWidth() / 2 + halfThick + outlineThick + thick % 2), (int)(scaledResolution.getScaledHeight() / 2 - gap + outlineThick - offsetHeight + thick % 2), (int)outlineColor);
            Gui.drawRect((int)(scaledResolution.getScaledWidth() / 2 + gap - outlineThick + offsetWidth), (int)(scaledResolution.getScaledHeight() / 2 - halfThick - outlineThick), (int)(scaledResolution.getScaledWidth() / 2 + gap + width + outlineThick + offsetWidth + thick % 2), (int)(scaledResolution.getScaledHeight() / 2 + halfThick + outlineThick + thick % 2), (int)outlineColor);
            Gui.drawRect((int)(scaledResolution.getScaledWidth() / 2 - halfThick - outlineThick), (int)(scaledResolution.getScaledHeight() / 2 + gap - outlineThick + offsetHeight), (int)(scaledResolution.getScaledWidth() / 2 + halfThick + outlineThick + thick % 2), (int)(scaledResolution.getScaledHeight() / 2 + gap + height + outlineThick + offsetHeight + thick % 2), (int)outlineColor);
        }
        Gui.drawRect((int)(scaledResolution.getScaledWidth() / 2 - gap - width - offsetWidth), (int)(scaledResolution.getScaledHeight() / 2 - halfThick), (int)(scaledResolution.getScaledWidth() / 2 - gap - offsetWidth + thick % 2), (int)(scaledResolution.getScaledHeight() / 2 + halfThick + thick % 2), (int)color);
        Gui.drawRect((int)(scaledResolution.getScaledWidth() / 2 - halfThick), (int)(scaledResolution.getScaledHeight() / 2 - gap - height - offsetHeight), (int)(scaledResolution.getScaledWidth() / 2 + halfThick + thick % 2), (int)(scaledResolution.getScaledHeight() / 2 - gap - offsetHeight + thick % 2), (int)color);
        Gui.drawRect((int)(scaledResolution.getScaledWidth() / 2 + gap + offsetWidth), (int)(scaledResolution.getScaledHeight() / 2 - halfThick), (int)(scaledResolution.getScaledWidth() / 2 + gap + width + offsetWidth + thick % 2), (int)(scaledResolution.getScaledHeight() / 2 + halfThick + thick % 2), (int)color);
        Gui.drawRect((int)(scaledResolution.getScaledWidth() / 2 - halfThick), (int)(scaledResolution.getScaledHeight() / 2 + gap + offsetHeight), (int)(scaledResolution.getScaledWidth() / 2 + halfThick + thick % 2), (int)(scaledResolution.getScaledHeight() / 2 + gap + height + offsetHeight + thick % 2), (int)color);
        this.percentDone = this.percentDone > 0.0f ? (this.percentDone -= 0.048f) : 0.0f;
        if (UViewSettings.crosshairDynamicBowAnimation.value().booleanValue() && mc.thePlayer.getHeldItem() != null) {
            float bowExtension = 0.0f;
            if (mc.thePlayer.getItemInUse() != null && mc.thePlayer.getItemInUse().getItem() instanceof ItemBow) {
                ItemStack bow = mc.thePlayer.getItemInUse();
                int useTime = mc.thePlayer.getItemInUseCount();
                bowExtension = 1.0f - (float)(bow.getItem().getMaxItemUseDuration(bow) - useTime) / 20.0f;
                if (useTime == 0 || bowExtension < 0.0f) {
                    bowExtension = 0.0f;
                }
            }
            if (mc.thePlayer.getHeldItem().getItem() instanceof ItemBow) {
                if (bowExtension == 0.95f) {
                    this.percentDone = UViewSettings.crosshairSize.value().floatValue();
                }
                this.percentDone = this.easeOut(this.percentDone, UViewSettings.crosshairSize.value().floatValue() * bowExtension);
            }
        }
        GlStateManager.popMatrix();
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    public void renderCrosshair(int number, int x, int y) {
        Minecraft mc = Minecraft.getMinecraft();
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        int color = this.getColor();
        int length = UViewSettings.crosshairWidth.value().intValue();
        int gap = UViewSettings.crosshairGap.value().intValue();
        int dot = UViewSettings.crosshairDotSize.value().intValue();
        int thick = UViewSettings.crosshairThickness.value().intValue() - 1;
        int gap2 = UViewSettings.crosshairGap.value().intValue();
        int length2 = UViewSettings.crosshairHeight.value().intValue();
        if ((float)dot != 0.0f) {
            Gui.drawRect((int)(x + 8 - dot), (int)(y + 8 - dot), (int)(x + 7 + dot), (int)(y + 7 + dot), (int)color);
        }
        if (thick != -1) {
            Gui.drawRect((int)(x + 7 - length - gap), (int)(y + 7 - thick), (int)(x + 7 - gap), (int)(y + 8 + thick), (int)color);
            Gui.drawRect((int)(x + 7 - thick), (int)(y + 7 - length - gap), (int)(x + 8 + thick), (int)(y + 7 - gap), (int)color);
            Gui.drawRect((int)(x + 8 + gap), (int)(y + 7 - thick), (int)(x + 8 + length + gap), (int)(y + 8 + thick), (int)color);
            Gui.drawRect((int)(x + 7 - thick), (int)(y + 8 + gap), (int)(x + 8 + thick), (int)(y + 8 + length + gap), (int)color);
            Gui.drawRect((int)(x + 7 - length2 - gap2), (int)(y + 7 - thick), (int)(x + 7 - gap2), (int)(y + 8 + thick), (int)color);
            Gui.drawRect((int)(x + 7 - thick), (int)(y + 7 - length2 - gap2), (int)(x + 8 + thick), (int)(y + 7 - gap2), (int)color);
            Gui.drawRect((int)(x + 8 + gap2), (int)(y + 7 - thick), (int)(x + 8 + length2 + gap2), (int)(y + 8 + thick), (int)color);
            Gui.drawRect((int)(x + 7 - thick), (int)(y + 8 + gap2), (int)(x + 8 + thick), (int)(y + 8 + length2 + gap2), (int)color);
        }
    }

    public int getColor() {
        Minecraft mc = Minecraft.getMinecraft();
        if (UViewSettings.crosshairHighlightFriend.value().booleanValue() && (mc.pointedEntity instanceof EntityAmbientCreature || mc.pointedEntity instanceof EntityAgeable)) {
            return UViewSettings.crosshairFriendColor.value();
        }
        if (UViewSettings.crosshairHighlightHostile.value().booleanValue() && mc.pointedEntity instanceof EntityMob) {
            return UViewSettings.crosshairHostileColor.value();
        }
        if (UViewSettings.crosshairHighlightPlayer.value().booleanValue() && mc.pointedEntity instanceof EntityPlayer) {
            return UViewSettings.crosshairPlayerColor.value();
        }
        return UViewSettings.crosshairCrosshairColor.value();
    }

    public void setGlRGB() {
        float val = 760.0f;
        Color color = new Color(Color.HSBtoRGB((float)(System.currentTimeMillis() % (long)((int)val)) / val, 0.8f, 0.8f));
        float red = (float)color.getRed() / 255.0f;
        float green = (float)color.getGreen() / 255.0f;
        float blue = (float)color.getBlue() / 255.0f;
        float alpha = (float)color.getAlpha() / 255.0f;
        GlStateManager.color((float)red, (float)green, (float)blue, (float)alpha);
    }

    private float easeOut(float current, float goal) {
        if (Math.floor(Math.abs(goal - current) / 0.01f) > 0.0) {
            return current + (goal - current) / 20.0f;
        }
        return goal;
    }

    private int descaleNum(int num) {
        return Math.round((float)num / UViewSettings.crosshairSize.value().floatValue());
    }
}

