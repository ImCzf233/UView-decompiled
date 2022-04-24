// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Pre
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.client.registry.ClientRegistry
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  org.lwjgl.input.Keyboard
 */
package com.domcer.uview.module.impl.game.togglesprint;

import com.domcer.uview.module.AbstractModule;
import com.domcer.uview.module.IRenderer;
import com.domcer.uview.settings.UViewSettings;
import com.domcer.uview.settings.types.NumberSetting;
import com.domcer.uview.util.ChromaUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

public class ToggleSprint
extends AbstractModule
implements IRenderer {
    public static final ToggleSprint INSTANCE = new ToggleSprint();
    private KeyBinding toggle;

    @Override
    public void onLoad() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.toggle = new KeyBinding("\u5f3a\u5236\u75be\u8dd1", 23, "key.categories.movement");
        ClientRegistry.registerKeyBinding((KeyBinding)this.toggle);
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent e) {
        int key = Minecraft.getMinecraft().gameSettings.keyBindSprint.getKeyCode();
        if (this.toggle.isPressed()) {
            UViewSettings.toggleSprintSetting.setValue(UViewSettings.toggleSprintSetting.value() == false);
            if (!UViewSettings.toggleSprintSetting.value().booleanValue() && key > 0) {
                KeyBinding.setKeyBindState((int)key, (boolean)Keyboard.isKeyDown((int)key));
            }
        }
        if (UViewSettings.toggleSprintSetting.value().booleanValue()) {
            KeyBinding.setKeyBindState((int)key, (boolean)true);
        }
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Pre event) {
        if (this.isDisplable() && UViewSettings.toggleSprintSetting.value().booleanValue() && event.type == RenderGameOverlayEvent.ElementType.TEXT) {
            this.render(UViewSettings.toggleSprintTextX.value().floatValue() * (float)event.resolution.getScaledWidth(), UViewSettings.toggleSprintTextY.value().floatValue() * (float)event.resolution.getScaledHeight());
        }
    }

    public void render(float x, float y) {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y, (float)0.0f);
        GlStateManager.scale((float)this.getScale().value().floatValue(), (float)this.getScale().value().floatValue(), (float)this.getScale().value().floatValue());
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        String text = this.getShownText();
        Minecraft mc = Minecraft.getMinecraft();
        if (UViewSettings.toggleSprintColor.isChroma().booleanValue()) {
            ChromaUtils.drawChromaString(text, 0.0f, 0.0f, false, true);
        } else {
            mc.fontRendererObj.drawStringWithShadow(text, 0.0f, 0.0f, UViewSettings.toggleSprintColor.value().intValue());
        }
        GlStateManager.popMatrix();
    }

    private String getShownText() {
        String trimmed = UViewSettings.toggleSprintText.value().trim();
        trimmed = trimmed.replace("&", "\u00a7");
        return trimmed;
    }

    @Override
    public NumberSetting getX() {
        return UViewSettings.toggleSprintTextX;
    }

    @Override
    public NumberSetting getY() {
        return UViewSettings.toggleSprintTextY;
    }

    @Override
    public NumberSetting getScale() {
        return UViewSettings.toggleSprintScale;
    }

    @Override
    public int getHeight() {
        return (int)((float)Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT * this.getScale().value().floatValue());
    }

    @Override
    public int getWidth() {
        return (int)((float)Minecraft.getMinecraft().fontRendererObj.getStringWidth(this.getShownText()) * this.getScale().value().floatValue());
    }

    @Override
    public boolean isGuiEnabled() {
        return UViewSettings.toggleSprintSetting.value();
    }

    @Override
    public void render(Minecraft minecraft, float x, float y) {
        this.render(x, y);
    }
}

