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
 *  net.minecraftforge.fml.common.gameevent.InputEvent$KeyInputEvent
 *  net.minecraftforge.fml.common.gameevent.InputEvent$MouseInputEvent
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 */
package com.domcer.uview.module.impl.hud;

import com.domcer.uview.module.AbstractModule;
import com.domcer.uview.module.IRenderer;
import com.domcer.uview.settings.UViewSettings;
import com.domcer.uview.settings.types.NumberSetting;
import com.domcer.uview.util.ChromaUtils;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class CPSDisplay
extends AbstractModule
implements IRenderer {
    public static final CPSDisplay INSTANCE = new CPSDisplay();
    public final List<Long> leftClicks = new ArrayList<Long>();
    public final List<Long> rightClicks = new ArrayList<Long>();
    private final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void onLoad() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    public int getLeftCPS() {
        long time = System.currentTimeMillis();
        this.leftClicks.removeIf(o -> o + 1000L < time);
        return this.leftClicks.size();
    }

    public int getRightCPS() {
        long time = System.currentTimeMillis();
        this.rightClicks.removeIf(o -> o + 1000L < time);
        return this.rightClicks.size();
    }

    @SubscribeEvent
    public void onMouseInput(InputEvent.MouseInputEvent event) {
        int leftClick = this.mc.gameSettings.keyBindAttack.getKeyCode();
        if (Mouse.getEventButtonState() && Mouse.getEventButton() == leftClick + 100) {
            this.leftClicks.add(System.currentTimeMillis());
        }
        int rightClick = this.mc.gameSettings.keyBindUseItem.getKeyCode();
        if (Mouse.getEventButtonState() && Mouse.getEventButton() == rightClick + 100) {
            this.rightClicks.add(System.currentTimeMillis());
        }
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        int leftClick = this.mc.gameSettings.keyBindAttack.getKeyCode();
        if (Keyboard.getEventKeyState() && Keyboard.getEventKey() == leftClick) {
            this.leftClicks.add(System.currentTimeMillis());
        }
        int rightClick = this.mc.gameSettings.keyBindUseItem.getKeyCode();
        if (Keyboard.getEventKeyState() && Keyboard.getEventKey() == rightClick) {
            this.rightClicks.add(System.currentTimeMillis());
        }
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Pre event) {
        if (this.isDisplable() && UViewSettings.cpsDisplayEnabled.value().booleanValue() && event.type == RenderGameOverlayEvent.ElementType.TEXT) {
            this.render(this.mc, UViewSettings.cpsDisplayX.value().floatValue() * (float)event.resolution.getScaledWidth(), UViewSettings.cpsDisplayY.value().floatValue() * (float)event.resolution.getScaledHeight());
        }
    }

    @Override
    public NumberSetting getX() {
        return UViewSettings.cpsDisplayX;
    }

    @Override
    public NumberSetting getY() {
        return UViewSettings.cpsDisplayY;
    }

    @Override
    public NumberSetting getScale() {
        return UViewSettings.cpsDisplayScale;
    }

    @Override
    public int getHeight() {
        if (!UViewSettings.cpsDisplayBackground.value().booleanValue()) {
            return (int)((float)Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT * this.getScale().value().floatValue());
        }
        return (int)(16.0f * UViewSettings.cpsDisplayScale.value().floatValue());
    }

    @Override
    public int getWidth() {
        if (!UViewSettings.cpsDisplayBackground.value().booleanValue()) {
            return (int)((float)Minecraft.getMinecraft().fontRendererObj.getStringWidth(this.getLeftCPS() + (UViewSettings.cpsDisplayRightEnabled.value() != false ? " | " + this.getRightCPS() : "") + (UViewSettings.cpsDisplayDigitalOnly.value() != false ? "" : " CPS")) * this.getScale().value().floatValue());
        }
        return (int)(70.0f * UViewSettings.cpsDisplayScale.value().floatValue());
    }

    @Override
    public boolean isGuiEnabled() {
        return UViewSettings.cpsDisplayEnabled.value();
    }

    @Override
    public void render(Minecraft minecraft, float x, float y) {
        Mouse.poll();
        int offsetX = 0;
        int offsetY = 0;
        if (UViewSettings.cpsDisplayBackground.value().booleanValue()) {
            offsetX = this.getWidth();
            offsetY = this.getHeight();
            Gui.drawRect((int)((int)x), (int)((int)y), (int)((int)x + offsetX), (int)((int)y + offsetY), (int)UViewSettings.cpsDisplayBackgroundColor.value());
        }
        String text = this.getLeftCPS() + (UViewSettings.cpsDisplayRightEnabled.value() != false ? " | " + this.getRightCPS() : "") + (UViewSettings.cpsDisplayDigitalOnly.value() != false ? "" : " CPS");
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)(x + ((float)offsetX - (UViewSettings.cpsDisplayBackground.value() != false ? (float)this.mc.fontRendererObj.getStringWidth(text) * this.getScale().value().floatValue() : 0.0f)) / 2.0f), (float)(y + ((float)offsetY - (UViewSettings.cpsDisplayBackground.value() != false ? (float)this.mc.fontRendererObj.FONT_HEIGHT * this.getScale().value().floatValue() : 0.0f)) / 2.0f), (float)0.0f);
        GlStateManager.scale((float)this.getScale().value().floatValue(), (float)this.getScale().value().floatValue(), (float)this.getScale().value().floatValue());
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        if (UViewSettings.cpsDisplayColor.isChroma().booleanValue()) {
            ChromaUtils.drawChromaString(text, 0.0f, 0.0f, false, true);
        } else {
            this.mc.fontRendererObj.drawStringWithShadow(text, 0.0f, 0.0f, UViewSettings.cpsDisplayColor.value().intValue());
        }
        GlStateManager.popMatrix();
    }
}

