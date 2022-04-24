// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package com.domcer.uview.packet.packets;

import com.domcer.uview.gui.UViewGui;
import com.domcer.uview.gui.component.UViewButton;
import com.domcer.uview.gui.component.UViewCheckBox;
import com.domcer.uview.gui.component.UViewImage;
import com.domcer.uview.gui.component.UViewScrollingList;
import com.domcer.uview.gui.component.UViewSkinDraw;
import com.domcer.uview.gui.component.UViewText;
import com.domcer.uview.gui.component.UViewTextField;
import com.domcer.uview.gui.interfaces.UViewComponent;
import com.domcer.uview.packet.Packet;
import com.domcer.uview.packet.PacketSerializer;
import com.domcer.uview.packet.exception.DeserializationException;
import com.domcer.uview.packet.exception.SerializationException;
import com.domcer.uview.resource.ResourceManager;
import com.domcer.uview.util.IOUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;

public class PacketOpenGui
extends Packet
implements PacketSerializer<PacketOpenGui> {
    public UViewGui gui;

    @Override
    public String name() {
        return "PacketOpenGui";
    }

    @Override
    public byte[] encode(PacketOpenGui packet) throws SerializationException {
        if (packet.gui == null) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream(64);
        try {
            IOUtils.writeString(out, packet.name());
            IOUtils.writeInt(out, packet.gui.getGuiWidth());
            IOUtils.writeInt(out, packet.gui.getGuiHeight());
            IOUtils.writeString(out, packet.gui.getBackgroundLocation() == null ? "none" : packet.gui.getBackgroundLocation().getResourcePath());
            IOUtils.writeInt(out, packet.gui.getComponents().size());
            for (UViewComponent component : packet.gui.getComponents()) {
                IOUtils.writeInt(out, component.getId());
                IOUtils.writeInt(out, component.getX());
                IOUtils.writeInt(out, component.getY());
                IOUtils.writeInt(out, component.getWidth());
                IOUtils.writeInt(out, component.getHeight());
                IOUtils.writeString(out, component.getType());
                this.writeComponent(out, component);
            }
            return out.toByteArray();
        }
        catch (IOException ex) {
            throw new SerializationException(ex.getMessage(), ex);
        }
    }

    @Override
    public PacketOpenGui decode(byte[] bytes) throws DeserializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            UViewGui gui;
            IOUtils.readString(in);
            PacketOpenGui packet = new PacketOpenGui();
            int guiWidth = IOUtils.readInt(in);
            int guiHeight = IOUtils.readInt(in);
            String background = IOUtils.readString(in);
            packet.gui = gui = new UViewGui(guiWidth, guiHeight, ResourceManager.parseLocation(background));
            int size = IOUtils.readInt(in);
            for (int i = 0; i < size; ++i) {
                gui.addComponent(this.readComponent(in));
            }
            return packet;
        }
        catch (IOException ex) {
            throw new DeserializationException(ex.getMessage(), ex);
        }
    }

    private UViewComponent readComponent(InputStream in) throws IOException {
        int id = IOUtils.readInt(in);
        int x = IOUtils.readInt(in);
        int y = IOUtils.readInt(in);
        int width = IOUtils.readInt(in);
        int height = IOUtils.readInt(in);
        String type = IOUtils.readString(in);
        if ("UViewButton".equals(type)) {
            String text = IOUtils.readString(in);
            String image = IOUtils.readString(in);
            String imageHover = IOUtils.readString(in);
            String sound = IOUtils.readString(in);
            String openUrl = IOUtils.readString(in);
            UViewButton button = new UViewButton(id, x, y, width, height, "none".equals(text) ? null : text);
            button.imageLocation = ResourceManager.parseLocation(image);
            button.imageHoverLocation = ResourceManager.parseLocation(imageHover);
            button.soundLocation = ResourceManager.parseLocation(sound);
            button.setOpenUrl("none".equals(openUrl) ? null : openUrl);
            int size = IOUtils.readInt(in);
            button.setHoverText(new ArrayList<String>());
            for (int i = 0; i < size; ++i) {
                button.getHoverText().add(IOUtils.readString(in));
            }
            return button;
        }
        if ("UViewImage".equals(type)) {
            String s = IOUtils.readString(in);
            UViewImage image = new UViewImage(id, x, y, width, height, ResourceManager.parseLocation(s));
            int size = IOUtils.readInt(in);
            image.setHoverText(new ArrayList<String>());
            for (int i = 0; i < size; ++i) {
                image.getHoverText().add(IOUtils.readString(in));
            }
            return image;
        }
        if ("UViewText".equals(type)) {
            int i;
            UViewText text = new UViewText(Minecraft.getMinecraft().fontRendererObj, id, x, y, width, height);
            int size = IOUtils.readInt(in);
            for (i = 0; i < size; ++i) {
                text.getLines().add(IOUtils.readString(in));
            }
            size = IOUtils.readInt(in);
            text.setHoverText(new ArrayList<String>());
            for (i = 0; i < size; ++i) {
                text.getHoverText().add(IOUtils.readString(in));
            }
            return text;
        }
        if ("UViewScrollingList".equals(type)) {
            int screenWidth = IOUtils.readInt(in);
            int screenHeight = IOUtils.readInt(in);
            int entryHeight = IOUtils.readInt(in);
            int border = IOUtils.readInt(in);
            String s = IOUtils.readString(in);
            UViewScrollingList scrollingList = new UViewScrollingList(id, x, y, width, height, entryHeight, screenWidth, screenHeight, border, ResourceManager.parseLocation(s));
            int size = IOUtils.readInt(in);
            for (int i = 0; i < size; ++i) {
                scrollingList.addComponent(this.readComponent(in));
            }
            return scrollingList;
        }
        if ("UViewTextField".equals(type)) {
            UViewTextField textField = new UViewTextField(id, Minecraft.getMinecraft().fontRendererObj, x, y, width, height);
            textField.setText(IOUtils.readString(in));
            textField.setMaxStringLength(IOUtils.readInt(in));
            textField.setEnableBackgroundDrawing(IOUtils.readInt(in) == 1);
            return textField;
        }
        if ("UViewCheckBox".equals(type)) {
            UViewCheckBox checkBox = new UViewCheckBox(id, x, y, width, height);
            checkBox.checked = IOUtils.readInt(in) == 1;
            return checkBox;
        }
        if ("UViewSkinDraw".equals(type)) {
            String s = IOUtils.readString(in);
            UViewSkinDraw skinDraw = new UViewSkinDraw(id, x, y, width, height, ResourceManager.parseLocation(s));
            return skinDraw;
        }
        return null;
    }

    private void writeComponent(OutputStream out, UViewComponent component) throws IOException {
        if (component instanceof UViewButton) {
            UViewButton button = (UViewButton)component;
            IOUtils.writeString(out, button.displayString == null || button.displayString.isEmpty() ? "none" : button.displayString);
            IOUtils.writeString(out, button.imageLocation == null ? "none" : button.imageLocation.getResourcePath());
            IOUtils.writeString(out, button.imageHoverLocation == null ? "none" : button.imageHoverLocation.getResourcePath());
            IOUtils.writeString(out, button.soundLocation == null ? "none" : button.soundLocation.getResourcePath());
            IOUtils.writeString(out, button.getOpenUrl() == null ? "none" : button.getOpenUrl());
            IOUtils.writeInt(out, button.getHoverText() == null ? 0 : button.getHoverText().size());
            if (button.getHoverText() != null) {
                for (String line : button.getHoverText()) {
                    IOUtils.writeString(out, line);
                }
            }
        } else if (component instanceof UViewImage) {
            UViewImage image = (UViewImage)component;
            IOUtils.writeString(out, image.imageLocation == null ? "none" : image.imageLocation.getResourcePath());
            IOUtils.writeInt(out, image.getHoverText() == null ? 0 : image.getHoverText().size());
            if (image.getHoverText() != null) {
                for (String line : image.getHoverText()) {
                    IOUtils.writeString(out, line);
                }
            }
        } else if (component instanceof UViewText) {
            UViewText text = (UViewText)component;
            IOUtils.writeInt(out, text.getLines() == null ? 0 : text.getLines().size());
            if (text.getLines() != null) {
                for (String line : text.getLines()) {
                    IOUtils.writeString(out, line);
                }
            }
            IOUtils.writeInt(out, text.getHoverText() == null ? 0 : text.getHoverText().size());
            if (text.getHoverText() != null) {
                for (String line : text.getHoverText()) {
                    IOUtils.writeString(out, line);
                }
            }
        } else if (component instanceof UViewScrollingList) {
            UViewScrollingList scrollingList = (UViewScrollingList)component;
            IOUtils.writeInt(out, scrollingList.screenWidth);
            IOUtils.writeInt(out, scrollingList.screenHeight);
            IOUtils.writeInt(out, scrollingList.entryHeight);
            IOUtils.writeInt(out, scrollingList.border);
            IOUtils.writeString(out, scrollingList.backgroundLocation == null ? "none" : scrollingList.backgroundLocation.getResourcePath());
            IOUtils.writeInt(out, scrollingList.getComponents() == null ? 0 : scrollingList.getComponents().size());
            if (scrollingList.getComponents() != null) {
                for (UViewComponent scrollingListComponent : scrollingList.getComponents()) {
                    this.writeComponent(out, scrollingListComponent);
                }
            }
        } else if (component instanceof UViewTextField) {
            UViewTextField textField = (UViewTextField)component;
            IOUtils.writeString(out, textField.getText() == null || textField.getText().isEmpty() ? "none" : textField.getText());
            IOUtils.writeInt(out, textField.getMaxStringLength());
            IOUtils.writeInt(out, textField.getEnableBackgroundDrawing() ? 1 : 0);
        } else if (component instanceof UViewCheckBox) {
            UViewCheckBox checkBox = (UViewCheckBox)component;
            IOUtils.writeInt(out, checkBox.checked ? 1 : 0);
        } else if (component instanceof UViewSkinDraw) {
            UViewSkinDraw skinDraw = (UViewSkinDraw)component;
            IOUtils.writeString(out, skinDraw.locationSkin == null ? "none" : skinDraw.locationSkin.getResourcePath());
        }
    }
}

