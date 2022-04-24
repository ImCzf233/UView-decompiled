// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Pre
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.domcer.uview.module.impl.hud.keystroke;

import com.domcer.uview.module.AbstractModule;
import com.domcer.uview.module.IRenderer;
import com.domcer.uview.module.impl.hud.keystroke.KeyRenderer;
import com.domcer.uview.module.impl.hud.keystroke.types.MouseButtonRenderer;
import com.domcer.uview.module.impl.hud.keystroke.types.MovementRenderer;
import com.domcer.uview.module.impl.hud.keystroke.types.SneakKeyRenderer;
import com.domcer.uview.module.impl.hud.keystroke.types.SpaceKeyRenderer;
import com.domcer.uview.settings.UViewSettings;
import com.domcer.uview.settings.types.NumberSetting;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class KeyStroke
extends AbstractModule
implements IRenderer {
    public static final KeyStroke INSTANCE = new KeyStroke();
    private final Minecraft mc = Minecraft.getMinecraft();
    private final KeyRenderer forwardKey;
    private final KeyRenderer leftKey;
    private final KeyRenderer backKey;
    private final KeyRenderer rightKey;
    private final KeyRenderer leftMouseKey;
    private final KeyRenderer rightMouseKey;
    private final KeyRenderer spaceKey;
    private final KeyRenderer sneakKey;

    public KeyStroke() {
        this.forwardKey = new MovementRenderer(this.mc.gameSettings.keyBindForward);
        this.leftKey = new MovementRenderer(this.mc.gameSettings.keyBindLeft);
        this.backKey = new MovementRenderer(this.mc.gameSettings.keyBindBack);
        this.rightKey = new MovementRenderer(this.mc.gameSettings.keyBindRight);
        this.leftMouseKey = new MouseButtonRenderer(this.mc.gameSettings.keyBindAttack);
        this.rightMouseKey = new MouseButtonRenderer(this.mc.gameSettings.keyBindUseItem);
        this.spaceKey = new SpaceKeyRenderer(this.mc.gameSettings.keyBindJump);
        this.sneakKey = new SneakKeyRenderer(this.mc.gameSettings.keyBindSneak);
    }

    @Override
    public void onLoad() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Pre event) {
        if (this.isDisplable() && UViewSettings.keyStrokeEnabled.value().booleanValue() && event.type == RenderGameOverlayEvent.ElementType.TEXT) {
            this.render(this.mc, UViewSettings.keyStrokeX.value().floatValue() * (float)event.resolution.getScaledWidth(), UViewSettings.keyStrokeY.value().floatValue() * (float)event.resolution.getScaledHeight());
        }
    }

    @Override
    public NumberSetting getX() {
        return UViewSettings.keyStrokeX;
    }

    @Override
    public NumberSetting getY() {
        return UViewSettings.keyStrokeY;
    }

    @Override
    public NumberSetting getScale() {
        return UViewSettings.keyStrokeScale;
    }

    @Override
    public int getHeight() {
        int height = 0;
        float scale = UViewSettings.keyStrokeScale.value().floatValue();
        if (UViewSettings.keyStrokeShowWASD.value().booleanValue()) {
            height = (int)((float)height + ((float)this.forwardKey.getHeight() + 2.0f * scale) * 2.0f);
        }
        if (UViewSettings.keyStrokeShowMouse.value().booleanValue()) {
            height = (int)((float)height + ((float)this.rightMouseKey.getHeight() + 2.0f * scale));
        }
        if (!UViewSettings.keyStrokeShowSpacebar.value().booleanValue() && UViewSettings.keyStrokeShowSneak.value().booleanValue()) {
            height = (int)((float)height + ((float)this.sneakKey.getHeight() + 2.0f * scale));
        } else if (UViewSettings.keyStrokeShowSpacebar.value().booleanValue() && !UViewSettings.keyStrokeShowSneak.value().booleanValue()) {
            height = (int)((float)height + ((float)this.spaceKey.getHeight() + 2.0f * scale));
        } else if (UViewSettings.keyStrokeShowSpacebar.value().booleanValue() && UViewSettings.keyStrokeShowSneak.value().booleanValue()) {
            height = (int)((float)height + ((float)this.sneakKey.getHeight() + 4.0f * scale + (float)this.spaceKey.getHeight()));
        }
        return height;
    }

    @Override
    public int getWidth() {
        return (int)(70.0f * UViewSettings.keyStrokeScale.value().floatValue());
    }

    @Override
    public boolean isGuiEnabled() {
        return UViewSettings.keyStrokeEnabled.value();
    }

    @Override
    public void render(Minecraft minecraft, float x, float y) {
        int ax = (int)x;
        int ay = (int)y;
        float scale = UViewSettings.keyStrokeScale.value().floatValue();
        if (UViewSettings.keyStrokeShowWASD.value().booleanValue()) {
            this.forwardKey.render((int)((float)(ax + this.leftKey.getWidth()) + 2.0f * scale), ay);
            ay = (int)((float)ay + ((float)this.forwardKey.getHeight() + 2.0f * scale));
            this.leftKey.render(ax, ay);
            this.backKey.render((int)((float)(ax + this.leftKey.getWidth()) + 2.0f * scale), ay);
            this.rightKey.render((int)((float)(ax + this.leftKey.getWidth() * 2) + 4.0f * scale), ay);
            ay = (int)((float)ay + ((float)this.rightKey.getHeight() + 2.0f * scale));
        }
        if (UViewSettings.keyStrokeShowMouse.value().booleanValue()) {
            this.leftMouseKey.render(ax, ay);
            this.rightMouseKey.render(ax + (int)((float)this.leftMouseKey.getWidth() + 2.0f * scale), ay);
            ay = (int)((float)ay + ((float)this.rightMouseKey.getHeight() + 2.0f * scale));
        }
        if (UViewSettings.keyStrokeShowSpacebar.value().booleanValue()) {
            this.spaceKey.render(ax, ay);
            ay = (int)((float)ay + ((float)this.spaceKey.getHeight() + 2.0f * scale));
        }
        if (UViewSettings.keyStrokeShowSneak.value().booleanValue()) {
            this.sneakKey.render(ax, ay);
        }
    }
}

