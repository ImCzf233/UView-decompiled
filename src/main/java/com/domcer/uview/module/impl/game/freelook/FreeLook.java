// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.client.registry.ClientRegistry
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.InputEvent$KeyInputEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.common.gameevent.TickEvent$RenderTickEvent
 */
package com.domcer.uview.module.impl.game.freelook;

import com.domcer.uview.module.AbstractModule;
import com.domcer.uview.module.impl.game.freelook.Camera;
import com.domcer.uview.settings.UViewSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class FreeLook
extends AbstractModule {
    public static final FreeLook INSTANCE = new FreeLook();
    public KeyBinding keyBinding;
    private boolean altIsDown = false;
    private boolean last = false;

    @Override
    public void onLoad() {
        this.keyBinding = new KeyBinding("\u81ea\u7531\u89c6\u89d2", 56, "key.categories.movement");
        ClientRegistry.registerKeyBinding((KeyBinding)this.keyBinding);
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        if (UViewSettings.freeLookSetting.value().booleanValue() && this.keyBinding.isKeyDown() && this.last) {
            Camera.update(event.phase == TickEvent.Phase.START);
        }
        if (!this.keyBinding.isKeyDown() && this.last) {
            Camera.reset();
            this.last = false;
        }
    }

    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent event) {
        if (UViewSettings.freeLookSetting.value().booleanValue() && this.keyBinding.isPressed()) {
            if (!this.altIsDown) {
                Camera.setCamera();
            }
            this.altIsDown = true;
            this.last = true;
        } else {
            this.altIsDown = false;
        }
    }
}

