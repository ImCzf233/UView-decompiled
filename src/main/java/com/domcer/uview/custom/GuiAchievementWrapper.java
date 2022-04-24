// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.achievement.GuiAchievement
 *  net.minecraft.stats.Achievement
 */
package com.domcer.uview.custom;

import com.domcer.uview.settings.UViewSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.achievement.GuiAchievement;
import net.minecraft.stats.Achievement;

public class GuiAchievementWrapper
extends GuiAchievement {
    private final GuiAchievement parent;

    public GuiAchievementWrapper(Minecraft mc, GuiAchievement parent) {
        super(mc);
        this.parent = parent;
    }

    public void displayAchievement(Achievement p_displayAchievement_1_) {
        if (UViewSettings.hideAchievementEnabled.value().booleanValue()) {
            return;
        }
        super.displayAchievement(p_displayAchievement_1_);
    }

    public void displayUnformattedAchievement(Achievement p_displayUnformattedAchievement_1_) {
        if (UViewSettings.hideAchievementEnabled.value().booleanValue()) {
            return;
        }
        super.displayUnformattedAchievement(p_displayUnformattedAchievement_1_);
    }

    public void updateAchievementWindow() {
        if (UViewSettings.hideAchievementEnabled.value().booleanValue()) {
            return;
        }
        super.updateAchievementWindow();
    }
}

