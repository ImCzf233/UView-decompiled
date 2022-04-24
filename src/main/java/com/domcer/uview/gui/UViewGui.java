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
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package com.domcer.uview.gui;

import com.domcer.uview.gui.component.UViewButton;
import com.domcer.uview.gui.component.UViewCheckBox;
import com.domcer.uview.gui.component.UViewTextField;
import com.domcer.uview.gui.interfaces.UViewComponent;
import com.domcer.uview.messager.UViewMessager;
import com.domcer.uview.packet.packets.PacketCloseGui;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SideOnly(value=Side.CLIENT)
public class UViewGui
extends GuiScreen {
    private static final Logger LOGGER = LogManager.getLogger();
    private int guiWidth;
    private int guiHeight;
    private int defaultGuiWidth;
    private int defaultGuiHeight;
    private double scaled = 1.0;
    private ResourceLocation backgroundLocation;
    private List<UViewComponent> components = new ArrayList<UViewComponent>();

    public UViewGui(int guiWidth, int guiHeight) {
        this(guiWidth, guiHeight, null);
    }

    public UViewGui(int guiWidth, int guiHeight, ResourceLocation backgroundLocation) {
        this.guiWidth = guiWidth;
        this.guiHeight = guiHeight;
        this.defaultGuiWidth = guiWidth;
        this.defaultGuiHeight = guiHeight;
        this.backgroundLocation = backgroundLocation;
    }

    public void initGui() {
        this.buttonList.clear();
        for (UViewComponent component : this.components) {
            if (!(component instanceof GuiButton)) continue;
            this.buttonList.add((GuiButton)component);
        }
        super.initGui();
    }

    public int getOffsetX() {
        return (this.width - this.guiWidth) / 2;
    }

    public int getOffsetY() {
        return (this.height - this.guiHeight) / 2;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        while (this.guiWidth > this.width || this.guiHeight > this.height) {
            this.guiWidth = (int)((double)this.guiWidth * 0.7);
            this.guiHeight = (int)((double)this.guiHeight * 0.7);
            this.scaled *= 0.7;
        }
        if (this.defaultGuiWidth < this.width && this.defaultGuiHeight < this.height) {
            this.scaled = 1.0;
            this.guiWidth = this.defaultGuiWidth;
            this.guiHeight = this.defaultGuiHeight;
        }
        try {
            this.drawDefaultBackground();
        }
        catch (Exception exception) {
            // empty catch block
        }
        int centerX = this.getOffsetX();
        int centerY = this.getOffsetY();
        if (this.backgroundLocation != null) {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.backgroundLocation);
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            UViewGui.drawModalRectWithCustomSizedTexture((int)centerX, (int)centerY, (float)0.0f, (float)0.0f, (int)this.guiWidth, (int)this.guiHeight, (float)this.guiWidth, (float)this.guiHeight);
        }
        for (UViewComponent component : this.components) {
            try {
                component.beforeDraw(this.mc, mouseX, mouseY, centerX, centerY, this.scaled);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (UViewComponent component : this.components) {
            try {
                component.draw(this.mc, mouseX, mouseY, centerX, centerY, this.scaled);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (UViewComponent component : this.components) {
            try {
                component.afterDraw(this.mc, mouseX, mouseY, centerX, centerY, this.scaled);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void updateScreen() {
        for (UViewComponent component : this.components) {
            if (!(component instanceof UViewTextField)) continue;
            ((UViewTextField)component).updateCursorCounter();
        }
        super.updateScreen();
    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        for (UViewComponent component : this.components) {
            UViewTextField textField;
            if (!(component instanceof UViewTextField) || !(textField = (UViewTextField)component).isFocused()) continue;
            textField.textboxKeyTyped(typedChar, keyCode);
        }
        if (keyCode == 1 || keyCode == this.mc.gameSettings.keyBindInventory.getKeyCode()) {
            this.mc.thePlayer.closeScreen();
            UViewMessager.sendPacket(new PacketCloseGui());
        }
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        int offsetX = this.getOffsetX();
        int offsetY = this.getOffsetY();
        for (UViewComponent component : this.components) {
            if (!((double)mouseX >= (double)component.getX() * this.scaled + (double)offsetX) || !((double)mouseY >= (double)component.getY() * this.scaled + (double)offsetY) || !((double)mouseX < (double)component.getX() * this.scaled + (double)offsetX + (double)component.getWidth() * this.scaled) || !((double)mouseY < (double)component.getY() * this.scaled + (double)offsetY + (double)component.getHeight() * this.scaled)) continue;
            if (component instanceof UViewTextField) {
                UViewTextField textField = (UViewTextField)component;
                textField.mouseClicked(mouseX, mouseY, mouseButton, offsetX, offsetY, this.scaled);
                for (UViewComponent viewComponent : this.components) {
                    if (!(viewComponent instanceof UViewTextField) || viewComponent.equals(component)) continue;
                    ((UViewTextField)viewComponent).setFocused(false);
                }
                continue;
            }
            if (component instanceof UViewButton) {
                UViewButton button = (UViewButton)component;
                if (!button.mousePressed(this.mc, mouseX, mouseY, offsetX, offsetY, this.scaled)) continue;
                button.playPressSound(this.mc.getSoundHandler());
                button.onClick();
                if (button.getOpenUrl() == null) continue;
                try {
                    this.openWebLink(new URI(button.getOpenUrl()));
                }
                catch (Exception exception) {}
                continue;
            }
            if (!(component instanceof UViewCheckBox)) continue;
            UViewCheckBox checkBox = (UViewCheckBox)component;
            checkBox.checked = !checkBox.checked;
            checkBox.senddUpdatePacket();
        }
    }

    public UViewComponent getComponentByMouse(int mouseX, int mouseY, int offsetX, int offsetY, double scaled) {
        for (UViewComponent component : this.components) {
            if (!((double)mouseX >= (double)component.getX() * scaled + (double)offsetX) || !((double)mouseY >= (double)component.getY() * scaled + (double)offsetY) || !((double)mouseX < (double)component.getX() * scaled + (double)offsetX + (double)component.getWidth() * scaled) || !((double)mouseY < (double)component.getY() * scaled + (double)offsetY + (double)component.getHeight() * scaled)) continue;
            return component;
        }
        return null;
    }

    public UViewComponent getComponentById(int id) {
        for (UViewComponent component : this.components) {
            if (component.getId() != id) continue;
            return component;
        }
        return null;
    }

    public void setBackgroundLocation(ResourceLocation backgroundLocation) {
        this.backgroundLocation = backgroundLocation;
    }

    public ResourceLocation getBackgroundLocation() {
        return this.backgroundLocation;
    }

    public List<UViewComponent> getComponents() {
        return this.components;
    }

    public void addComponent(UViewComponent component) {
        this.components.add(component);
    }

    public int getGuiWidth() {
        return this.guiWidth;
    }

    public int getGuiHeight() {
        return this.guiHeight;
    }

    public void setGuiWidth(int guiWidth) {
        this.guiWidth = guiWidth;
    }

    public void setGuiHeight(int guiHeight) {
        this.guiHeight = guiHeight;
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    private void openWebLink(URI uri) {
        try {
            Class<?> oclass = Class.forName("java.awt.Desktop");
            Object object = oclass.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
            oclass.getMethod("browse", URI.class).invoke(object, uri);
        }
        catch (Throwable throwable) {
            LOGGER.error("Couldn't open link", throwable);
        }
    }
}

