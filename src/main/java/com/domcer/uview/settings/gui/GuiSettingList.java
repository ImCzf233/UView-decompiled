// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiListExtended
 *  net.minecraft.client.gui.GuiListExtended$IGuiListEntry
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiTextField
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.domcer.uview.settings.gui;

import com.domcer.uview.module.impl.blur.MotionBlur;
import com.domcer.uview.settings.ISetting;
import com.domcer.uview.settings.UViewSettings;
import com.domcer.uview.settings.gui.GuiItemCounterSetting;
import com.domcer.uview.settings.gui.GuiSettings;
import com.domcer.uview.settings.gui.NumberSettingSliderWrapper;
import com.domcer.uview.settings.gui.button.BooleanSettingButton;
import com.domcer.uview.settings.gui.button.EnumSettingButton;
import com.domcer.uview.settings.gui.button.GuiColorPicker;
import com.domcer.uview.settings.gui.button.NumberSettingSlider;
import com.domcer.uview.settings.gui.button.StringSettingTextField;
import com.domcer.uview.settings.types.BooleanSetting;
import com.domcer.uview.settings.types.ColorSetting;
import com.domcer.uview.settings.types.EnumSetting;
import com.domcer.uview.settings.types.ItemCounterSetting;
import com.domcer.uview.settings.types.NumberSetting;
import com.domcer.uview.settings.types.StringSetting;
import com.domcer.uview.skin.DelayedTask;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class GuiSettingList
extends GuiListExtended {
    private final GuiSettings parentGui;
    private final Minecraft mc;
    private final List<GuiListExtended.IGuiListEntry> listEntries = new ArrayList<GuiListExtended.IGuiListEntry>();
    private int maxListLabelWidth = 0;

    public GuiSettingList(GuiSettings parentGui, Minecraft mc) {
        super(mc, parentGui.width, parentGui.height, 63, parentGui.height - 32, 20);
        Object[] settings;
        this.parentGui = parentGui;
        this.mc = mc;
        for (Object obj : settings = new Object[]{"\u6742\u9879", UViewSettings.tabHealthColorEnabled, UViewSettings.tagSetting, UViewSettings.sharpnessParticlesSetting, UViewSettings.freeLookSetting, UViewSettings.fullBrightEnabled, "\u804a\u5929", UViewSettings.betterChatSmooth, UViewSettings.betterChatClear, "\u6e38\u620f", UViewSettings.toggleSprintSetting, UViewSettings.toggleSprintText, UViewSettings.toggleSprintColor, UViewSettings.directionHudEnabled, UViewSettings.directionHudColor, UViewSettings.timeChangerSetting, UViewSettings.timeChangerTime, UViewSettings.itemPhysicsEnabled, UViewSettings.itemPhysicsRotateSpeed, UViewSettings.hideAchievementEnabled, UViewSettings.tntTimeEnabled, "\u88c5\u5907\u663e\u793a", UViewSettings.armorHudEnabled, UViewSettings.armorHudhorizontalAlignment, UViewSettings.armorHudSpacing, UViewSettings.armorHudDurabilityPercentage, UViewSettings.armorHudDurabilityColor, "\u836f\u6c34\u663e\u793a", UViewSettings.potionEffectHudEnabled, UViewSettings.potionEffectHudTextColor, UViewSettings.potionEffectHudDurationColor, UViewSettings.potionEffectHudRenderBackground, UViewSettings.potionEffectHudShowName, UViewSettings.potionEffectHudShowIcon, UViewSettings.potionEffectHudShowDuration, "Boss\u8840\u6761", UViewSettings.bossbarEnabled, UViewSettings.bossbarText, UViewSettings.bossbarProgress, "\u5750\u6807\u663e\u793a", UViewSettings.coordinatesEnabled, UViewSettings.coordinatesTrend, UViewSettings.coordinatesColor, UViewSettings.coordinatesBackground, UViewSettings.coordinatesBackgroundColor, "CPS\u663e\u793a", UViewSettings.cpsDisplayEnabled, UViewSettings.cpsDisplayRightEnabled, UViewSettings.cpsDisplayDigitalOnly, UViewSettings.cpsDisplayColor, UViewSettings.cpsDisplayBackground, UViewSettings.cpsDisplayBackgroundColor, "FPS\u663e\u793a", UViewSettings.fpsDisplayEnabled, UViewSettings.fpsDisplayColor, UViewSettings.fpsDisplayBackground, UViewSettings.fpsDisplayBackgroundColor, "Ping\u663e\u793a", UViewSettings.pingDisplayEnabled, UViewSettings.pingDisplayColor, UViewSettings.pingDisplayBackground, UViewSettings.pingDisplayBackgroundColor, "\u7269\u54c1\u8ba1\u6570\u5668", UViewSettings.itemCounterEnabled, UViewSettings.itemCounterhorizontalAlignment, UViewSettings.itemCounterColor, UViewSettings.itemCounterBackgroundColor, UViewSettings.itemCounterItems, "\u9971\u548c\u5ea6\u663e\u793a", UViewSettings.saturationEnabled, "\u6309\u952e\u663e\u793a", UViewSettings.keyStrokeEnabled, UViewSettings.keyStrokeColor, UViewSettings.keyStrokeBackgroundColor, UViewSettings.keyStrokePressedBackgroundColor, UViewSettings.keyStrokeArrowKeys, UViewSettings.keyStrokeShowSpacebar, UViewSettings.keyStrokeShowSneak, UViewSettings.keyStrokeShowMouse, UViewSettings.keyStrokeShowWASD, "\u89c6\u91ce\u4fee\u6539", UViewSettings.fovDefaultEnabled, UViewSettings.fovDefault, UViewSettings.fovSprintingEnabled, UViewSettings.fovSprinting, UViewSettings.fovSpeedEnabled, UViewSettings.fovSpeed, UViewSettings.fovSlownessEnabled, UViewSettings.fovSlowness, UViewSettings.fovFlyingEnabled, UViewSettings.fovFlying, "\u65b9\u5757\u8fb9\u7f18", UViewSettings.blockOverlayEnabled, UViewSettings.blockOverlayColor, UViewSettings.blockOverlayLineWidth, UViewSettings.blockOverlayMode, "\u9f20\u6807\u6307\u9488", UViewSettings.crosshairEnabled, UViewSettings.crosshairWidth, UViewSettings.crosshairHeight, UViewSettings.crosshairGap, UViewSettings.crosshairThickness, UViewSettings.crosshairDotSize, UViewSettings.crosshairOutline, UViewSettings.crosshairDot, UViewSettings.crosshairCrosshairColor, UViewSettings.crosshairOutlineColor, UViewSettings.crosshairDotColor, UViewSettings.crosshairOutlineThickness, UViewSettings.crosshairShowIn3rdPerson, UViewSettings.crosshairShowInGuis, UViewSettings.crosshairHostileColor, UViewSettings.crosshairFriendColor, UViewSettings.crosshairPlayerColor, UViewSettings.crosshairHighlightHostile, UViewSettings.crosshairHighlightFriend, UViewSettings.crosshairHighlightPlayer, UViewSettings.crosshairDynamicBowAnimation, UViewSettings.crosshairDynamicAttackAnimation}) {
            if (obj instanceof ISetting) {
                ISetting setting = (ISetting)obj;
                int width = mc.fontRendererObj.getStringWidth(setting.getDesc());
                if (width > this.maxListLabelWidth) {
                    this.maxListLabelWidth = width;
                }
                this.listEntries.add(new SettingEntry(setting));
                continue;
            }
            if (!(obj instanceof String)) continue;
            this.listEntries.add(new CategoryEntry((String)obj));
        }
    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        for (GuiListExtended.IGuiListEntry listEntry : this.listEntries) {
            if (!(listEntry instanceof SettingEntry) || !(((SettingEntry)listEntry).getComponent() instanceof StringSettingTextField)) continue;
            StringSettingTextField textField = (StringSettingTextField)((SettingEntry)listEntry).getComponent();
            textField.textboxKeyTyped(typedChar, keyCode);
            textField.onTyped();
        }
    }

    protected int getSize() {
        return this.listEntries.size();
    }

    public GuiListExtended.IGuiListEntry getListEntry(int index) {
        return this.listEntries.get(index);
    }

    protected int getScrollBarX() {
        return super.getScrollBarX() + 15;
    }

    public int getListWidth() {
        return super.getListWidth() + 32;
    }

    @SideOnly(value=Side.CLIENT)
    public class CategoryEntry
    implements GuiListExtended.IGuiListEntry {
        private final String labelText;
        private final int labelWidth;

        public CategoryEntry(String labelText) {
            this.labelText = labelText;
            this.labelWidth = ((GuiSettingList)GuiSettingList.this).mc.fontRendererObj.getStringWidth(this.labelText);
        }

        public void drawEntry(int p_drawEntry_1_, int p_drawEntry_2_, int p_drawEntry_3_, int p_drawEntry_4_, int p_drawEntry_5_, int p_drawEntry_6_, int p_drawEntry_7_, boolean p_drawEntry_8_) {
            ((GuiSettingList)GuiSettingList.this).mc.fontRendererObj.drawString(this.labelText, ((GuiSettingList)GuiSettingList.this).mc.currentScreen.width / 2 - this.labelWidth / 2, p_drawEntry_3_ + p_drawEntry_5_ - ((GuiSettingList)GuiSettingList.this).mc.fontRendererObj.FONT_HEIGHT - 1, 0xFFFFFF);
        }

        public boolean mousePressed(int p_mousePressed_1_, int p_mousePressed_2_, int p_mousePressed_3_, int p_mousePressed_4_, int p_mousePressed_5_, int p_mousePressed_6_) {
            return false;
        }

        public void mouseReleased(int p_mouseReleased_1_, int p_mouseReleased_2_, int p_mouseReleased_3_, int p_mouseReleased_4_, int p_mouseReleased_5_, int p_mouseReleased_6_) {
        }

        public void setSelected(int p_setSelected_1_, int p_setSelected_2_, int p_setSelected_3_) {
        }
    }

    @SideOnly(value=Side.CLIENT)
    public class SettingEntry
    implements GuiListExtended.IGuiListEntry {
        private final ISetting setting;
        private final Gui component;

        private SettingEntry(final ISetting setting) {
            this.setting = setting;
            if (setting instanceof BooleanSetting) {
                this.component = new BooleanSettingButton((BooleanSetting)setting, 0, 0, 0, 150, 20);
            } else if (setting instanceof StringSetting) {
                this.component = new StringSettingTextField((StringSetting)setting, 0, ((GuiSettingList)GuiSettingList.this).mc.fontRendererObj, 0, 0, 146, 16);
            } else if (setting instanceof NumberSetting) {
                this.component = setting == UViewSettings.fovDefault || setting == UViewSettings.fovSprinting || setting == UViewSettings.fovSpeed || setting == UViewSettings.fovSlowness || setting == UViewSettings.fovFlying ? new NumberSettingSlider(new NumberSettingSliderWrapper<Integer>((NumberSetting)setting){

                    @Override
                    public void handleSlide(float sliderValue) {
                        int value = (int)(sliderValue * 80.0f);
                        this.setting.setValue(30 + value);
                    }

                    @Override
                    public float getSliderValue() {
                        return (float)(this.setting.value().intValue() - 30) / 80.0f;
                    }

                    @Override
                    public String getButtonText() {
                        return this.setting.value() + "";
                    }
                }, 0, 0, 0) : (setting == UViewSettings.blurSpeed ? new NumberSettingSlider(new NumberSettingSliderWrapper<Integer>((NumberSetting)setting){
                    private final DecimalFormat df;
                    {
                        this.df = new DecimalFormat("0.0");
                    }

                    @Override
                    public void handleSlide(float sliderValue) {
                        int value = (int)(sliderValue * 60000.0f);
                        this.setting.setValue(value);
                    }

                    @Override
                    public float getSliderValue() {
                        return (float)this.setting.value().intValue() / 60000.0f;
                    }

                    @Override
                    public String getButtonText() {
                        if ((double)this.getSliderValue() >= 1.0) {
                            return "\u65e0\u9650";
                        }
                        if (this.getSliderValue() == 0.0f) {
                            return "\u7acb\u5373";
                        }
                        return this.df.format((double)this.setting.value().longValue() / 1000.0) + "\u79d2";
                    }
                }, 0, 0, 0) : (setting == UViewSettings.crosshairSize ? new NumberSettingSlider(new NumberSettingSliderWrapper<Integer>((NumberSetting)setting){
                    private final DecimalFormat df;
                    {
                        this.df = new DecimalFormat("0%");
                    }

                    @Override
                    public void handleSlide(float sliderValue) {
                        this.setting.setValue(Float.valueOf(sliderValue * 3.0f));
                    }

                    @Override
                    public float getSliderValue() {
                        return (float)this.setting.value().intValue() / 3.0f;
                    }

                    @Override
                    public String getButtonText() {
                        return this.df.format(this.setting.value().floatValue());
                    }
                }, 0, 0, 0) : (setting == UViewSettings.crosshairWidth || setting == UViewSettings.crosshairHeight || setting == UViewSettings.crosshairGap || setting == UViewSettings.crosshairThickness || setting == UViewSettings.crosshairDotSize || setting == UViewSettings.crosshairOutlineThickness ? new NumberSettingSlider(new NumberSettingSliderWrapper<Integer>((NumberSetting)setting){

                    @Override
                    public void handleSlide(float sliderValue) {
                        int value = (int)(sliderValue * 100.0f);
                        this.setting.setValue(value);
                    }

                    @Override
                    public float getSliderValue() {
                        return (float)this.setting.value().intValue() / 100.0f;
                    }

                    @Override
                    public String getButtonText() {
                        if (this.setting.value().intValue() <= 0) {
                            return "\u5173\u95ed";
                        }
                        return this.setting.value() + "";
                    }
                }, 0, 0, 0) : (setting == UViewSettings.blurRadius ? new NumberSettingSlider(new NumberSettingSliderWrapper<Integer>((NumberSetting)setting){

                    @Override
                    public void handleSlide(float sliderValue) {
                        int value = (int)(sliderValue * 100.0f);
                        this.setting.setValue(value);
                    }

                    @Override
                    public float getSliderValue() {
                        return (float)this.setting.value().intValue() / 100.0f;
                    }

                    @Override
                    public String getButtonText() {
                        if (this.setting.value().intValue() <= 0) {
                            return "\u5173\u95ed";
                        }
                        return this.setting.value() + "";
                    }
                }, 0, 0, 0) : (setting == UViewSettings.timeChangerTime ? new NumberSettingSlider(new NumberSettingSliderWrapper<Integer>((NumberSetting)setting){

                    @Override
                    public void handleSlide(float sliderValue) {
                        int value = (int)(sliderValue * 24000.0f);
                        this.setting.setValue(value);
                    }

                    @Override
                    public float getSliderValue() {
                        return (float)this.setting.value().intValue() / 24000.0f;
                    }

                    @Override
                    public String getButtonText() {
                        return this.setting.value() + "";
                    }
                }, 0, 0, 0) : (setting == UViewSettings.motionBlurAmount ? new NumberSettingSlider(new NumberSettingSliderWrapper<Integer>((NumberSetting)setting){

                    @Override
                    public void handleSlide(float sliderValue) {
                        int value = (int)(sliderValue * 7.0f);
                        this.setting.setValue(value);
                        if (value > 0) {
                            MotionBlur.INSTANCE.turnOn();
                        }
                    }

                    @Override
                    public float getSliderValue() {
                        return (float)this.setting.value().intValue() / 7.0f;
                    }

                    @Override
                    public String getButtonText() {
                        return this.setting.value() + "";
                    }
                }, 0, 0, 0) : (setting == UViewSettings.blockOverlayLineWidth ? new NumberSettingSlider(new NumberSettingSliderWrapper<Integer>((NumberSetting)setting){
                    private final DecimalFormat df;
                    {
                        this.df = new DecimalFormat("0.0");
                    }

                    @Override
                    public void handleSlide(float sliderValue) {
                        float value = sliderValue * 15.0f;
                        this.setting.setValue(Float.valueOf(value));
                    }

                    @Override
                    public float getSliderValue() {
                        return this.setting.value().floatValue() / 15.0f;
                    }

                    @Override
                    public String getButtonText() {
                        return this.df.format(this.setting.value());
                    }
                }, 0, 0, 0) : (setting == UViewSettings.sharpnessParticlesSetting ? new NumberSettingSlider(new NumberSettingSliderWrapper<Integer>((NumberSetting)setting){

                    @Override
                    public void handleSlide(float sliderValue) {
                        int value = (int)(sliderValue * 10.0f);
                        this.setting.setValue(value);
                    }

                    @Override
                    public float getSliderValue() {
                        return (float)this.setting.value().intValue() / 10.0f;
                    }

                    @Override
                    public String getButtonText() {
                        if (this.setting.value().intValue() <= 0) {
                            return "\u5173\u95ed";
                        }
                        return this.setting.value() + "";
                    }
                }, 0, 0, 0) : (setting == UViewSettings.armorHudSpacing ? new NumberSettingSlider(new NumberSettingSliderWrapper<Integer>((NumberSetting)setting){

                    @Override
                    public void handleSlide(float sliderValue) {
                        int value = (int)(sliderValue * 10.0f);
                        this.setting.setValue(value);
                    }

                    @Override
                    public float getSliderValue() {
                        return (float)this.setting.value().intValue() / 10.0f;
                    }

                    @Override
                    public String getButtonText() {
                        return this.setting.value() + "";
                    }
                }, 0, 0, 0) : (setting == UViewSettings.betterChatClear ? new NumberSettingSlider(new NumberSettingSliderWrapper<Float>((NumberSetting)setting){
                    private final DecimalFormat df;
                    {
                        this.df = new DecimalFormat("0%");
                    }

                    @Override
                    public void handleSlide(float sliderValue) {
                        this.setting.setValue(Float.valueOf(sliderValue));
                    }

                    @Override
                    public float getSliderValue() {
                        return this.setting.value().floatValue();
                    }

                    @Override
                    public String getButtonText() {
                        return this.df.format(this.setting.value());
                    }
                }, 0, 0, 0) : (setting == UViewSettings.itemPhysicsRotateSpeed || setting == UViewSettings.potionEffectHudScale ? new NumberSettingSlider(new NumberSettingSliderWrapper<Float>((NumberSetting)setting){
                    private final DecimalFormat df;
                    {
                        this.df = new DecimalFormat("0%");
                    }

                    @Override
                    public void handleSlide(float sliderValue) {
                        this.setting.setValue(Float.valueOf(sliderValue * 2.5f));
                    }

                    @Override
                    public float getSliderValue() {
                        return this.setting.value().floatValue() / 2.5f;
                    }

                    @Override
                    public String getButtonText() {
                        return this.df.format(this.getSliderValue());
                    }
                }, 0, 0, 0) : new NumberSettingSlider(new NumberSettingSliderWrapper<Integer>((NumberSetting)setting){

                    @Override
                    public void handleSlide(float sliderValue) {
                        int value = (int)(sliderValue * 10.0f);
                        this.setting.setValue(value);
                    }

                    @Override
                    public float getSliderValue() {
                        return (float)this.setting.value().intValue() / 10.0f;
                    }

                    @Override
                    public String getButtonText() {
                        if (this.setting.value().intValue() <= 0) {
                            return "\u5173\u95ed";
                        }
                        return this.setting.value() + "";
                    }
                }, 0, 0, 0))))))))))));
            } else if (this.setting instanceof ColorSetting) {
                this.component = new GuiColorPicker(GuiSettingList.this.parentGui, (ColorSetting)setting, 0, 0, 0);
            } else if (this.setting instanceof ItemCounterSetting) {
                this.component = new GuiButton(0, 0, 0, 150, 20, setting.getDesc()){

                    public boolean mousePressed(Minecraft mc, int p_mouseReleased_1_, int p_mouseReleased_2_) {
                        this.playPressSound(mc.getSoundHandler());
                        new DelayedTask(() -> {
                            Minecraft.getMinecraft().displayGuiScreen(null);
                            Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiItemCounterSetting(GuiSettingList.this.parentGui, (ItemCounterSetting)setting));
                        });
                        return true;
                    }
                };
                ((GuiButton)this.component).displayString = "\u70b9\u51fb\u7f16\u8f91";
            } else {
                this.component = this.setting instanceof EnumSetting ? new EnumSettingButton((EnumSetting)setting, 0, 0, 0, 150, 20) : new GuiButton(0, 0, 0, 150, 20, setting.getDesc());
            }
        }

        public void drawEntry(int p_drawEntry_1_, int p_drawEntry_2_, int p_drawEntry_3_, int p_drawEntry_4_, int p_drawEntry_5_, int p_drawEntry_6_, int p_drawEntry_7_, boolean p_drawEntry_8_) {
            ((GuiSettingList)GuiSettingList.this).mc.fontRendererObj.drawString(this.setting.getDesc(), p_drawEntry_2_ + 90 - GuiSettingList.this.maxListLabelWidth, p_drawEntry_3_ + p_drawEntry_5_ / 2 - ((GuiSettingList)GuiSettingList.this).mc.fontRendererObj.FONT_HEIGHT / 2, 0xFFFFFF);
            if (this.component instanceof GuiColorPicker) {
                GuiColorPicker colorPicker = (GuiColorPicker)this.component;
                colorPicker.xPosition = p_drawEntry_2_ + 107;
                colorPicker.yPosition = p_drawEntry_3_ + 2;
                colorPicker.drawButton(GuiSettingList.this.mc, p_drawEntry_6_, p_drawEntry_7_);
            } else if (this.component instanceof GuiButton) {
                GuiButton button = (GuiButton)this.component;
                button.xPosition = p_drawEntry_2_ + 105;
                button.yPosition = p_drawEntry_3_;
                button.drawButton(GuiSettingList.this.mc, p_drawEntry_6_, p_drawEntry_7_);
            } else if (this.component instanceof GuiTextField) {
                GuiTextField textField = (GuiTextField)this.component;
                textField.xPosition = p_drawEntry_2_ + 107;
                textField.yPosition = p_drawEntry_3_ + 2;
                textField.updateCursorCounter();
                textField.drawTextBox();
            }
        }

        public boolean mousePressed(int p_mousePressed_1_, int p_mousePressed_2_, int p_mousePressed_3_, int p_mousePressed_4_, int p_mousePressed_5_, int p_mousePressed_6_) {
            if (this.component instanceof GuiButton) {
                return ((GuiButton)this.component).mousePressed(GuiSettingList.this.mc, p_mousePressed_2_, p_mousePressed_3_);
            }
            if (this.component instanceof GuiTextField) {
                ((GuiTextField)this.component).mouseClicked(p_mousePressed_2_, p_mousePressed_3_, p_mousePressed_4_);
                return true;
            }
            return false;
        }

        public void mouseReleased(int p_mouseReleased_1_, int p_mouseReleased_2_, int p_mouseReleased_3_, int p_mouseReleased_4_, int p_mouseReleased_5_, int p_mouseReleased_6_) {
            if (this.component instanceof GuiButton) {
                ((GuiButton)this.component).mouseReleased(p_mouseReleased_2_, p_mouseReleased_3_);
            }
        }

        public void setSelected(int p_setSelected_1_, int p_setSelected_2_, int p_setSelected_3_) {
        }

        public Gui getComponent() {
            return this.component;
        }
    }
}

