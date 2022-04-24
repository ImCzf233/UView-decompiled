// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 */
package com.domcer.uview.settings.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiConfirm
extends GuiScreen {
    private String screenTitle;
    private Runnable confirm;
    private Runnable cancel;

    public GuiConfirm(String screenTitle, Runnable confirm, Runnable cancel) {
        this.screenTitle = screenTitle;
        this.confirm = confirm;
        this.cancel = cancel;
    }

    public void initGui() {
        this.buttonList.add(new GuiButton(200, this.width / 2 - 155, this.height / 2 - 10, 150, 20, "确定"));
        this.buttonList.add(new GuiButton(201, this.width / 2 - 155 + 160, this.height / 2 - 10, 150, 20, "取消"));
    }

    protected void actionPerformed(GuiButton button) {
        if (button.id == 200) {
            try {
                this.confirm.run();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } else if (button.id == 201) {
            try {
                this.cancel.run();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void drawScreen(int p_drawScreen_1_, int p_drawScreen_2_, float p_drawScreen_3_) {
        this.drawCenteredString(this.fontRendererObj, this.screenTitle, this.width / 2, this.height / 2 - 60, -1);
        super.drawScreen(p_drawScreen_1_, p_drawScreen_2_, p_drawScreen_3_);
    }

    public boolean doesGuiPauseGame() {
        return false;
    }
}

