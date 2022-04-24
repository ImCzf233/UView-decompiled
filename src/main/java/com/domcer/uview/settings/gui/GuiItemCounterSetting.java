// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 */
package com.domcer.uview.settings.gui;

import com.domcer.uview.settings.types.ItemCounterSetting;
import com.domcer.uview.skin.DelayedTask;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiItemCounterSetting
extends GuiScreen {
    private final GuiScreen parentGui;
    private final ItemCounterSetting setting;
    private ItemCounterSetting.DisplayItem currentItem;

    public GuiItemCounterSetting(GuiScreen parentGui, ItemCounterSetting setting) {
        this.parentGui = parentGui;
        this.setting = setting;
        if (!(setting.value() instanceof ArrayList)) {
            setting.setValue((List<ItemCounterSetting.DisplayItem>)new ArrayList<ItemCounterSetting.DisplayItem>((Collection<ItemCounterSetting.DisplayItem>)setting.value()));
        }
    }

    public void initGui() {
        this.buttonList.add(new GuiButton(0, 5, this.height - 25, 100, 20, "\u8fd4\u56de"){

            public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
                if (super.mousePressed(mc, mouseX, mouseY)) {
                    GuiItemCounterSetting.this.openParentGui();
                    return true;
                }
                return false;
            }
        });
        this.buttonList.add(new GuiButton(1, 5, this.height - 50, 100, 20, "\u65b0\u589e\u624b\u4e0a\u7269\u54c1"){

            public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
                if (super.mousePressed(mc, mouseX, mouseY)) {
                    EntityPlayerSP thePlayer = Minecraft.getMinecraft().thePlayer;
                    if (thePlayer == null) {
                        return true;
                    }
                    if (thePlayer.inventory.getCurrentItem() == null) {
                        return true;
                    }
                    String seedName = ((ResourceLocation)Item.itemRegistry.getNameForObject((Item) thePlayer.inventory.getCurrentItem().getItem())).toString();
                    GuiItemCounterSetting.this.currentItem = null;
                    ItemCounterSetting.DisplayItem displayItem = new ItemCounterSetting.DisplayItem(GuiItemCounterSetting.this.setting.value().size(), false, seedName);
                    GuiItemCounterSetting.this.setting.value().add(displayItem);
                    GuiItemCounterSetting.this.buttonList.removeIf(button -> button.id >= 200);
                    GuiItemCounterSetting.this.initButtons();
                    return true;
                }
                return false;
            }
        });
        this.initButtons();
    }

    private void initButtons() {
        int i = 0;
        Iterator iterator = this.setting.value().iterator();
        while (iterator.hasNext()) {
            final ItemCounterSetting.DisplayItem displayItem = (ItemCounterSetting.DisplayItem)iterator.next();
            final int index = this.setting.value().indexOf(displayItem);
            Item item = Item.getByNameOrId((String)displayItem.itemName);
            this.buttonList.add(new GuiButton(200 + ++i, this.width / 2 - 75, 50 + 20 * i + (i - 1) * 5, 150, 20, I18n.format((String)item.getItemStackDisplayName(new ItemStack(item, 1)), (Object[])new Object[0])){
                private final ItemCounterSetting.DisplayItem item;
                {
                    this.item = displayItem;
                }

                public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
                    if (super.mousePressed(mc, mouseX, mouseY)) {
                        GuiItemCounterSetting.this.currentItem = this.item;
                        GuiItemCounterSetting.this.buttonList.add(new GuiButton(10, GuiItemCounterSetting.this.width / 2 + 90, 70, 60, 20, "\u5220\u9664"){

                            public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
                                if (super.mousePressed(mc, mouseX, mouseY)) {
                                    if (GuiItemCounterSetting.this.currentItem != null) {
                                        GuiItemCounterSetting.this.buttonList.removeIf(button -> button.id == 10);
                                        GuiItemCounterSetting.this.buttonList.removeIf(button -> button.id == 11);
                                        GuiItemCounterSetting.this.buttonList.removeIf(button -> button.id == 12);
                                        GuiItemCounterSetting.this.buttonList.removeIf(button -> button.id == 13);
                                        GuiItemCounterSetting.this.buttonList.removeIf(button -> button.id >= 200);
                                        GuiItemCounterSetting.this.setting.value().remove(GuiItemCounterSetting.this.currentItem);
                                        GuiItemCounterSetting.this.initButtons();
                                        GuiItemCounterSetting.this.currentItem = null;
                                    }
                                    return true;
                                }
                                return false;
                            }
                        });
                        GuiItemCounterSetting.this.buttonList.add(new GuiButton(11, GuiItemCounterSetting.this.width / 2 + 90, 95, 60, 20, "\u6570\u91cf\u4e3a0\u65f6\u9690\u85cf"){

                            public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
                                if (super.mousePressed(mc, mouseX, mouseY)) {
                                    if (GuiItemCounterSetting.this.currentItem != null) {
                                        ((GuiItemCounterSetting)GuiItemCounterSetting.this).currentItem.hideNone = !((GuiItemCounterSetting)GuiItemCounterSetting.this).currentItem.hideNone;
                                    }
                                    return true;
                                }
                                return false;
                            }

                            public void drawButton(Minecraft p_drawButton_1_, int p_drawButton_2_, int p_drawButton_3_) {
                                if (this.visible) {
                                    FontRenderer fontrenderer = p_drawButton_1_.fontRendererObj;
                                    p_drawButton_1_.getTextureManager().bindTexture(buttonTextures);
                                    GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                                    this.hovered = p_drawButton_2_ >= this.xPosition && p_drawButton_3_ >= this.yPosition && p_drawButton_2_ < this.xPosition + this.width && p_drawButton_3_ < this.yPosition + this.height;
                                    int i = !((GuiItemCounterSetting)GuiItemCounterSetting.this).currentItem.hideNone ? 0 : this.getHoverState(this.hovered);
                                    GlStateManager.enableBlend();
                                    GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
                                    GlStateManager.blendFunc((int)770, (int)771);
                                    this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + i * 20, this.width / 2, this.height);
                                    this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
                                    this.mouseDragged(p_drawButton_1_, p_drawButton_2_, p_drawButton_3_);
                                    int j = 0xE0E0E0;
                                    if (this.packedFGColour != 0) {
                                        j = this.packedFGColour;
                                    } else if (!((GuiItemCounterSetting)GuiItemCounterSetting.this).currentItem.hideNone) {
                                        j = 0xA0A0A0;
                                    } else if (this.hovered) {
                                        j = 0xFFFFA0;
                                    }
                                    this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, j);
                                }
                            }
                        });
                        GuiItemCounterSetting.this.buttonList.add(new GuiButton(12, GuiItemCounterSetting.this.width / 2 + 90, 120, 60, 20, "\u4e0a\u79fb"){

                            public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
                                if (super.mousePressed(mc, mouseX, mouseY)) {
                                    if (GuiItemCounterSetting.this.currentItem != null && index > 0) {
                                        GuiItemCounterSetting.this.setting.value().remove(GuiItemCounterSetting.this.currentItem);
                                        GuiItemCounterSetting.this.setting.value().add(index - 1, GuiItemCounterSetting.this.currentItem);
                                        GuiItemCounterSetting.this.buttonList.removeIf(button -> button.id == 10);
                                        GuiItemCounterSetting.this.buttonList.removeIf(button -> button.id == 11);
                                        GuiItemCounterSetting.this.buttonList.removeIf(button -> button.id == 12);
                                        GuiItemCounterSetting.this.buttonList.removeIf(button -> button.id == 13);
                                        GuiItemCounterSetting.this.buttonList.removeIf(button -> button.id >= 200);
                                        GuiItemCounterSetting.this.initButtons();
                                    }
                                    return true;
                                }
                                return false;
                            }

                            public void drawButton(Minecraft p_drawButton_1_, int p_drawButton_2_, int p_drawButton_3_) {
                                if (this.visible) {
                                    FontRenderer fontrenderer = p_drawButton_1_.fontRendererObj;
                                    p_drawButton_1_.getTextureManager().bindTexture(buttonTextures);
                                    GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                                    this.hovered = p_drawButton_2_ >= this.xPosition && p_drawButton_3_ >= this.yPosition && p_drawButton_2_ < this.xPosition + this.width && p_drawButton_3_ < this.yPosition + this.height;
                                    int i = index == 0 ? 0 : this.getHoverState(this.hovered);
                                    GlStateManager.enableBlend();
                                    GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
                                    GlStateManager.blendFunc((int)770, (int)771);
                                    this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + i * 20, this.width / 2, this.height);
                                    this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
                                    this.mouseDragged(p_drawButton_1_, p_drawButton_2_, p_drawButton_3_);
                                    int j = 0xE0E0E0;
                                    if (this.packedFGColour != 0) {
                                        j = this.packedFGColour;
                                    } else if (index == 0) {
                                        j = 0xA0A0A0;
                                    } else if (this.hovered) {
                                        j = 0xFFFFA0;
                                    }
                                    this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, j);
                                }
                            }
                        });
                        GuiItemCounterSetting.this.buttonList.add(new GuiButton(13, GuiItemCounterSetting.this.width / 2 + 90, 145, 60, 20, "\u4e0b\u79fb"){

                            public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
                                if (super.mousePressed(mc, mouseX, mouseY)) {
                                    if (GuiItemCounterSetting.this.currentItem != null && index <= GuiItemCounterSetting.this.setting.value().size() - 1) {
                                        GuiItemCounterSetting.this.setting.value().remove(GuiItemCounterSetting.this.currentItem);
                                        GuiItemCounterSetting.this.setting.value().add(index, GuiItemCounterSetting.this.currentItem);
                                        GuiItemCounterSetting.this.buttonList.removeIf(button -> button.id == 10);
                                        GuiItemCounterSetting.this.buttonList.removeIf(button -> button.id == 11);
                                        GuiItemCounterSetting.this.buttonList.removeIf(button -> button.id == 12);
                                        GuiItemCounterSetting.this.buttonList.removeIf(button -> button.id == 13);
                                        GuiItemCounterSetting.this.buttonList.removeIf(button -> button.id >= 200);
                                        GuiItemCounterSetting.this.initButtons();
                                    }
                                    return true;
                                }
                                return false;
                            }

                            public void drawButton(Minecraft p_drawButton_1_, int p_drawButton_2_, int p_drawButton_3_) {
                                if (this.visible) {
                                    FontRenderer fontrenderer = p_drawButton_1_.fontRendererObj;
                                    p_drawButton_1_.getTextureManager().bindTexture(buttonTextures);
                                    GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                                    this.hovered = p_drawButton_2_ >= this.xPosition && p_drawButton_3_ >= this.yPosition && p_drawButton_2_ < this.xPosition + this.width && p_drawButton_3_ < this.yPosition + this.height;
                                    int i = index >= GuiItemCounterSetting.this.setting.value().size() - 1 ? 0 : this.getHoverState(this.hovered);
                                    GlStateManager.enableBlend();
                                    GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
                                    GlStateManager.blendFunc((int)770, (int)771);
                                    this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + i * 20, this.width / 2, this.height);
                                    this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
                                    this.mouseDragged(p_drawButton_1_, p_drawButton_2_, p_drawButton_3_);
                                    int j = 0xE0E0E0;
                                    if (this.packedFGColour != 0) {
                                        j = this.packedFGColour;
                                    } else if (index >= GuiItemCounterSetting.this.setting.value().size() - 1) {
                                        j = 0xA0A0A0;
                                    } else if (this.hovered) {
                                        j = 0xFFFFA0;
                                    }
                                    this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, j);
                                }
                            }
                        });
                        return true;
                    }
                    return false;
                }
            });
        }
    }

    public void openParentGui() {
        new DelayedTask(() -> {
            Minecraft.getMinecraft().displayGuiScreen(null);
            Minecraft.getMinecraft().displayGuiScreen(this.parentGui);
        });
    }

    protected void keyTyped(char typed, int key) throws IOException {
        if (key == 1) {
            this.openParentGui();
            return;
        }
        super.keyTyped(typed, key);
    }

    public void onGuiClosed() {
        this.openParentGui();
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GuiItemCounterSetting.drawRect((int)(this.width / 2 - 80), (int)65, (int)(this.width / 2 + 80), (int)(65 + this.setting.value().size() * 25 + 5), (int)0x3F000000);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public boolean doesGuiPauseGame() {
        return false;
    }
}

