/*
 * Decompiled with CFR 0.152.
 */
package com.domcer.uview.module;

import com.domcer.uview.module.AbstractModule;
import com.domcer.uview.module.IRenderer;
import com.domcer.uview.module.impl.chat.BetterChat;
import com.domcer.uview.module.impl.game.Bossbar;
import com.domcer.uview.module.impl.game.FovChanger;
import com.domcer.uview.module.impl.game.Fullbright;
import com.domcer.uview.module.impl.game.ItemPhysics;
import com.domcer.uview.module.impl.game.SharpnessParticles;
import com.domcer.uview.module.impl.game.TNTTime;
import com.domcer.uview.module.impl.game.TimeChanger;
import com.domcer.uview.module.impl.game.blockoverlay.BlockOverlay;
import com.domcer.uview.module.impl.game.crosshair.Crosshair;
import com.domcer.uview.module.impl.game.freelook.FreeLook;
import com.domcer.uview.module.impl.game.togglesprint.ToggleSprint;
import com.domcer.uview.module.impl.hud.CPSDisplay;
import com.domcer.uview.module.impl.hud.Coordinates;
import com.domcer.uview.module.impl.hud.FPSDisplay;
import com.domcer.uview.module.impl.hud.PingDisplay;
import com.domcer.uview.module.impl.hud.Saturation;
import com.domcer.uview.module.impl.hud.armor.ArmorModule;
import com.domcer.uview.module.impl.hud.direction.DirectionModule;
import com.domcer.uview.module.impl.hud.itemcounter.ItemCounter;
import com.domcer.uview.module.impl.hud.keystroke.KeyStroke;
import com.domcer.uview.module.impl.hud.potioneffect.PotionEffectModule;
import com.domcer.uview.module.impl.tag.TagRenderer;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class ModuleManager {
    public static final Logger LOGGER = Logger.getLogger("ModuleManager");
    public static final ModuleManager INSTANCE = new ModuleManager();
    private final Set<AbstractModule> modules = new HashSet<AbstractModule>();

    public void init() {
        this.modules.add(BetterChat.INSTANCE);
        this.modules.add(TagRenderer.INSTANCE);
        this.modules.add(SharpnessParticles.INSTANCE);
        this.modules.add(FreeLook.INSTANCE);
        this.modules.add(ArmorModule.INSTANCE);
        this.modules.add(DirectionModule.INSTANCE);
        this.modules.add(PotionEffectModule.INSTANCE);
        this.modules.add(ToggleSprint.INSTANCE);
        this.modules.add(Bossbar.INSTANCE);
        this.modules.add(Coordinates.INSTANCE);
        this.modules.add(CPSDisplay.INSTANCE);
        this.modules.add(FPSDisplay.INSTANCE);
        this.modules.add(PingDisplay.INSTANCE);
        this.modules.add(KeyStroke.INSTANCE);
        this.modules.add(TimeChanger.INSTANCE);
        this.modules.add(Fullbright.INSTANCE);
        this.modules.add(FovChanger.INSTANCE);
        this.modules.add(Crosshair.INSTANCE);
        this.modules.add(ItemPhysics.INSTANCE);
        this.modules.add(TNTTime.INSTANCE);
        this.modules.add(ItemCounter.INSTANCE);
        this.modules.add(Saturation.INSTANCE);
        this.modules.add(BlockOverlay.INSTANCE);
        for (AbstractModule module : this.modules) {
            try {
                module.onLoad();
            }
            catch (Exception ex) {
                LOGGER.warning("Module " + module.getClass().getSimpleName() + " load failed!");
                ex.printStackTrace();
            }
        }
    }

    public Set<IRenderer> getRenderers() {
        HashSet<IRenderer> renderers = new HashSet<IRenderer>();
        for (AbstractModule module : this.modules) {
            if (!IRenderer.class.isAssignableFrom(module.getClass())) continue;
            renderers.add((IRenderer)((Object)module));
        }
        return renderers;
    }

    public Set<IRenderer> getGuiEnabledRenderers() {
        HashSet<IRenderer> renderers = new HashSet<IRenderer>();
        for (AbstractModule module : this.modules) {
            if (!IRenderer.class.isAssignableFrom(module.getClass()) || !((IRenderer)((Object)module)).isGuiEnabled()) continue;
            renderers.add((IRenderer)((Object)module));
        }
        return renderers;
    }
}

