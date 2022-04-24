// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.resources.I18n
 */
package com.domcer.uview.settings.gui;

import com.domcer.uview.command.CommandUCfg;
import com.domcer.uview.module.IRenderer;
import com.domcer.uview.module.ModuleManager;
import com.domcer.uview.settings.UViewSettings;
import com.domcer.uview.settings.gui.GuiHudPosition;
import com.domcer.uview.settings.gui.GuiSettingList;
import com.domcer.uview.settings.gui.button.GuiColorPicker;
import com.domcer.uview.settings.gui.button.RendererButton;
import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiSettings
extends GuiScreen {
    private String screenTitle;
    private GuiSettingList settingList;
    public GuiColorPicker colorPicker;
    private String rgbText = "";

    public void initGui() {
        this.screenTitle = "\u8bbe\u7f6e";
        this.settingList = new GuiSettingList(this, this.mc);
        this.buttonList.add(new GuiButton(199, this.width / 2 - 55 - 110, this.height - 29, 100, 20, I18n.format((String)"gui.done", (Object[])new Object[0])));
        this.buttonList.add(new GuiButton(200, this.width / 2 - 55, this.height - 29, 100, 20, "\u5206\u4eab\u914d\u7f6e"));
        this.buttonList.add(new GuiButton(201, this.width / 2 - 55 + 110, this.height - 29, 100, 20, "\u8c03\u6574\u4f4d\u7f6e"));
    }

    public void onGuiClosed() {
        UViewSettings.saveConfig();
    }

    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        if (this.colorPicker == null) {
            this.settingList.handleMouseInput();
        }
    }

    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 199) {
            Minecraft.getMinecraft().displayGuiScreen(null);
            UViewSettings.saveConfig();
            return;
        }
        if (button.id == 200) {
            Minecraft.getMinecraft().displayGuiScreen(null);
            CommandUCfg.shareConfig();
            return;
        }
        if (button.id == 201) {
            Minecraft.getMinecraft().displayGuiScreen(null);
            ArrayList<RendererButton> rendererButtons = new ArrayList<RendererButton>();
            for (IRenderer renderer : ModuleManager.INSTANCE.getGuiEnabledRenderers()) {
                rendererButtons.add(new RendererButton(renderer));
            }
            Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiHudPosition(rendererButtons.toArray(new RendererButton[0])));
            return;
        }
        super.actionPerformed(button);
    }

    protected void keyTyped(char typed, int key) throws IOException {
        if (key == 1) {
            UViewSettings.saveConfig();
            this.mc.displayGuiScreen(null);
            return;
        }
        if (this.colorPicker != null) {
            if (typed >= 'a' && typed <= 'f' || typed >= 'A' && typed <= 'F' || typed >= '0' && typed <= '9') {
                if (this.rgbText.length() < 5) {
                    this.colorPicker.text = this.rgbText = this.rgbText + typed;
                } else {
                    this.rgbText = this.rgbText + typed;
                    try {
                        int hex = -16777216 + Integer.parseInt(this.rgbText, 16);
                        this.colorPicker.colorSetting.setValue(hex);
                        this.colorPicker.text = null;
                        this.rgbText = "";
                    }
                    catch (Exception ignored) {
                        this.colorPicker.text = this.rgbText = "";
                    }
                }
            } else if (key == 14) {
                if (!this.rgbText.isEmpty()) {
                    this.rgbText = this.rgbText.substring(0, this.rgbText.length() - 1);
                }
                this.colorPicker.text = this.rgbText;
            }
            return;
        }
        super.keyTyped(typed, key);
        this.settingList.keyTyped(typed, key);
    }

    protected void mouseClicked(int p_mouseClicked_1_, int p_mouseClicked_2_, int p_mouseClicked_3_) throws IOException {
        if (this.colorPicker != null && this.colorPicker.mousePressed(this.mc, p_mouseClicked_2_, p_mouseClicked_3_)) {
            return;
        }
        if (!this.settingList.mouseClicked(p_mouseClicked_1_, p_mouseClicked_2_, p_mouseClicked_3_)) {
            super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_2_, p_mouseClicked_3_);
        }
    }

    protected void mouseReleased(int p_mouseReleased_1_, int p_mouseReleased_2_, int p_mouseReleased_3_) {
        if (!this.settingList.mouseReleased(p_mouseReleased_1_, p_mouseReleased_2_, p_mouseReleased_3_)) {
            super.mouseReleased(p_mouseReleased_1_, p_mouseReleased_2_, p_mouseReleased_3_);
        }
    }

    public void drawScreen(int p_drawScreen_1_, int p_drawScreen_2_, float p_drawScreen_3_) {
        this.drawDefaultBackground();
        this.settingList.drawScreen(p_drawScreen_1_, p_drawScreen_2_, p_drawScreen_3_);
        this.drawCenteredString(this.fontRendererObj, this.screenTitle, this.width / 2, 28, 0xFFFFFF);
        super.drawScreen(p_drawScreen_1_, p_drawScreen_2_, p_drawScreen_3_);
    }

    public boolean doesGuiPauseGame() {
        return false;
    }
}

