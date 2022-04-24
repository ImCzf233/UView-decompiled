// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.client.audio.SoundCategory
 *  net.minecraft.client.audio.SoundEventAccessorComposite
 *  net.minecraft.client.audio.SoundHandler
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiControls
 *  net.minecraft.client.gui.GuiCustomizeSkin
 *  net.minecraft.client.gui.GuiIngameMenu
 *  net.minecraft.client.gui.GuiLanguage
 *  net.minecraft.client.gui.GuiLockIconButton
 *  net.minecraft.client.gui.GuiMainMenu
 *  net.minecraft.client.gui.GuiMultiplayer
 *  net.minecraft.client.gui.GuiOptionButton
 *  net.minecraft.client.gui.GuiOptionSlider
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiScreenOptionsSounds
 *  net.minecraft.client.gui.GuiScreenResourcePacks
 *  net.minecraft.client.gui.GuiSnooper
 *  net.minecraft.client.gui.GuiVideoSettings
 *  net.minecraft.client.gui.GuiYesNo
 *  net.minecraft.client.gui.GuiYesNoCallback
 *  net.minecraft.client.gui.ScreenChatOptions
 *  net.minecraft.client.gui.stream.GuiStreamOptions
 *  net.minecraft.client.gui.stream.GuiStreamUnavailable
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.client.settings.GameSettings$Options
 *  net.minecraft.client.stream.IStream
 *  net.minecraft.realms.RealmsBridge
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.EnumDifficulty
 */
package com.domcer.uview.custom;

import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.audio.SoundEventAccessorComposite;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.gui.GuiCustomizeSkin;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiLockIconButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiOptionSlider;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenOptionsSounds;
import net.minecraft.client.gui.GuiScreenResourcePacks;
import net.minecraft.client.gui.GuiSnooper;
import net.minecraft.client.gui.GuiVideoSettings;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.gui.ScreenChatOptions;
import net.minecraft.client.gui.stream.GuiStreamOptions;
import net.minecraft.client.gui.stream.GuiStreamUnavailable;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.stream.IStream;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.EnumDifficulty;

public class CustomIngameMenu
extends GuiIngameMenu
implements GuiYesNoCallback {
    private static final GameSettings.Options[] field_146440_f = new GameSettings.Options[]{GameSettings.Options.FOV};
    private final GuiScreen field_146441_g;
    private final GameSettings game_settings_1;
    private GuiButton field_175357_i;
    private GuiLockIconButton field_175356_r;
    protected String field_146442_a = "Options";

    public CustomIngameMenu(GuiScreen p_i1046_1_, GameSettings p_i1046_2_) {
        this.field_146441_g = p_i1046_1_;
        this.game_settings_1 = p_i1046_2_;
    }

    public void initGui() {
        int lvt_1_1_ = 0;
        this.field_146442_a = "DoMcer \u4e2d\u56fd\u7248";
        for (GameSettings.Options lvt_5_1_ : field_146440_f) {
            if (lvt_5_1_.getEnumFloat()) {
                this.buttonList.add(new GuiOptionSlider(lvt_5_1_.returnEnumOrdinal(), this.width / 2 - 155 + lvt_1_1_ % 2 * 160, this.height / 6 - 12 + 24 * (lvt_1_1_ >> 1), lvt_5_1_));
            } else {
                GuiOptionButton lvt_6_1_ = new GuiOptionButton(lvt_5_1_.returnEnumOrdinal(), this.width / 2 - 155 + lvt_1_1_ % 2 * 160, this.height / 6 - 12 + 24 * (lvt_1_1_ >> 1), lvt_5_1_, this.game_settings_1.getKeyBinding(lvt_5_1_));
                this.buttonList.add(lvt_6_1_);
            }
            ++lvt_1_1_;
        }
        if (this.mc.theWorld != null) {
            EnumDifficulty lvt_2_2_ = this.mc.theWorld.getDifficulty();
            this.field_175357_i = new GuiButton(108, this.width / 2 - 155 + lvt_1_1_ % 2 * 160, this.height / 6 - 12 + 24 * (lvt_1_1_ >> 1), 150, 20, this.func_175355_a(lvt_2_2_));
            this.buttonList.add(this.field_175357_i);
            if (this.mc.isSingleplayer() && !this.mc.theWorld.getWorldInfo().isHardcoreModeEnabled()) {
                this.field_175357_i.setWidth(this.field_175357_i.getButtonWidth() - 20);
                this.field_175356_r = new GuiLockIconButton(109, this.field_175357_i.xPosition + this.field_175357_i.getButtonWidth(), this.field_175357_i.yPosition);
                this.buttonList.add(this.field_175356_r);
                this.field_175356_r.func_175229_b(this.mc.theWorld.getWorldInfo().isDifficultyLocked());
                this.field_175356_r.enabled = !this.field_175356_r.func_175230_c();
                this.field_175357_i.enabled = !this.field_175356_r.func_175230_c();
            } else {
                this.field_175357_i.enabled = false;
            }
        } else {
            GuiOptionButton lvt_2_3_ = new GuiOptionButton(GameSettings.Options.REALMS_NOTIFICATIONS.returnEnumOrdinal(), this.width / 2 - 155 + lvt_1_1_ % 2 * 160, this.height / 6 - 12 + 24 * (lvt_1_1_ >> 1), GameSettings.Options.REALMS_NOTIFICATIONS, this.game_settings_1.getKeyBinding(GameSettings.Options.REALMS_NOTIFICATIONS));
            this.buttonList.add(lvt_2_3_);
        }
        this.buttonList.add(new GuiButton(105, this.width / 2 - 155, this.height / 6 + 48 - 6, 150, 20, I18n.format((String)"options.resourcepack", (Object[])new Object[0])));
        this.buttonList.add(new GuiButton(8675309, this.width / 2 + 5, this.height / 6 + 48 - 6, 150, 20, "Super Secret Settings..."){

            public void playPressSound(SoundHandler p_playPressSound_1_) {
                SoundEventAccessorComposite lvt_2_1_ = p_playPressSound_1_.getRandomSoundFromCategories(new SoundCategory[]{SoundCategory.ANIMALS, SoundCategory.BLOCKS, SoundCategory.MOBS, SoundCategory.PLAYERS, SoundCategory.WEATHER});
                if (lvt_2_1_ != null) {
                    p_playPressSound_1_.playSound((ISound)PositionedSoundRecord.create((ResourceLocation)lvt_2_1_.getSoundEventLocation(), (float)0.5f));
                }
            }
        });
        this.buttonList.add(new GuiButton(106, this.width / 2 - 155, this.height / 6 + 72 - 6, 150, 20, I18n.format((String)"options.sounds", (Object[])new Object[0])));
        this.buttonList.add(new GuiButton(103, this.width / 2 + 5, this.height / 6 + 72 - 6, 150, 20, I18n.format((String)"options.chat.title", (Object[])new Object[0])));
        this.buttonList.add(new GuiButton(101, this.width / 2 - 155, this.height / 6 + 96 - 6, 150, 20, I18n.format((String)"options.video", (Object[])new Object[0])));
        this.buttonList.add(new GuiButton(100, this.width / 2 + 5, this.height / 6 + 96 - 6, 150, 20, I18n.format((String)"options.controls", (Object[])new Object[0])));
        this.buttonList.add(new GuiButton(2001, this.width / 2 - 100, this.height / 6 + 168, "\u8fd4\u56de\u6e38\u620f"));
        this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 240, "\u65ad\u5f00\u8fde\u63a5"));
    }

    public String func_175355_a(EnumDifficulty p_175355_1_) {
        ChatComponentText lvt_2_1_ = new ChatComponentText("");
        lvt_2_1_.appendSibling((IChatComponent)new ChatComponentTranslation("options.difficulty", new Object[0]));
        lvt_2_1_.appendText(": ");
        lvt_2_1_.appendSibling((IChatComponent)new ChatComponentTranslation(p_175355_1_.getDifficultyResourceKey(), new Object[0]));
        return lvt_2_1_.getFormattedText();
    }

    public void confirmClicked(boolean p_confirmClicked_1_, int p_confirmClicked_2_) {
        this.mc.displayGuiScreen((GuiScreen)this);
        if (p_confirmClicked_2_ == 109 && p_confirmClicked_1_ && this.mc.theWorld != null) {
            this.mc.theWorld.getWorldInfo().setDifficultyLocked(true);
            this.field_175356_r.func_175229_b(true);
            this.field_175356_r.enabled = false;
            this.field_175357_i.enabled = false;
        }
    }

    protected void actionPerformed(GuiButton p_actionPerformed_1_) {
        if (p_actionPerformed_1_.enabled) {
            if (p_actionPerformed_1_.id < 100 && p_actionPerformed_1_ instanceof GuiOptionButton) {
                GameSettings.Options lvt_2_1_ = ((GuiOptionButton)p_actionPerformed_1_).returnEnumOptions();
                this.game_settings_1.setOptionValue(lvt_2_1_, 1);
                p_actionPerformed_1_.displayString = this.game_settings_1.getKeyBinding(GameSettings.Options.getEnumOptions((int)p_actionPerformed_1_.id));
            }
            if (p_actionPerformed_1_.id == 108) {
                this.mc.theWorld.getWorldInfo().setDifficulty(EnumDifficulty.getDifficultyEnum((int)(this.mc.theWorld.getDifficulty().getDifficultyId() + 1)));
                this.field_175357_i.displayString = this.func_175355_a(this.mc.theWorld.getDifficulty());
            }
            if (p_actionPerformed_1_.id == 109) {
                this.mc.displayGuiScreen((GuiScreen)new GuiYesNo((GuiYesNoCallback)this, new ChatComponentTranslation("difficulty.lock.title", new Object[0]).getFormattedText(), new ChatComponentTranslation("difficulty.lock.question", new Object[]{new ChatComponentTranslation(this.mc.theWorld.getWorldInfo().getDifficulty().getDifficultyResourceKey(), new Object[0])}).getFormattedText(), 109));
            }
            if (p_actionPerformed_1_.id == 110) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen((GuiScreen)new GuiCustomizeSkin((GuiScreen)this));
            }
            if (p_actionPerformed_1_.id == 8675309) {
                this.mc.entityRenderer.activateNextShader();
            }
            if (p_actionPerformed_1_.id == 101) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen((GuiScreen)new GuiVideoSettings((GuiScreen)this, this.game_settings_1));
            }
            if (p_actionPerformed_1_.id == 100) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen((GuiScreen)new GuiControls((GuiScreen)this, this.game_settings_1));
            }
            if (p_actionPerformed_1_.id == 102) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen((GuiScreen)new GuiLanguage((GuiScreen)this, this.game_settings_1, this.mc.getLanguageManager()));
            }
            if (p_actionPerformed_1_.id == 103) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen((GuiScreen)new ScreenChatOptions((GuiScreen)this, this.game_settings_1));
            }
            if (p_actionPerformed_1_.id == 104) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen((GuiScreen)new GuiSnooper((GuiScreen)this, this.game_settings_1));
            }
            if (p_actionPerformed_1_.id == 2001) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(this.field_146441_g);
            }
            if (p_actionPerformed_1_.id == 200) {
                this.mc.gameSettings.saveOptions();
                boolean flag = this.mc.isIntegratedServerRunning();
                boolean flag1 = false;
                p_actionPerformed_1_.enabled = false;
                this.mc.theWorld.sendQuittingDisconnectingPacket();
                this.mc.loadWorld(null);
                if (flag) {
                    this.mc.displayGuiScreen((GuiScreen)new GuiMainMenu());
                } else if (flag1) {
                    RealmsBridge realmsbridge = new RealmsBridge();
                    realmsbridge.switchToRealms((GuiScreen)new GuiMainMenu());
                } else {
                    this.mc.displayGuiScreen((GuiScreen)new GuiMultiplayer((GuiScreen)new GuiMainMenu()));
                }
            }
            if (p_actionPerformed_1_.id == 105) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen((GuiScreen)new GuiScreenResourcePacks((GuiScreen)this));
            }
            if (p_actionPerformed_1_.id == 106) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen((GuiScreen)new GuiScreenOptionsSounds((GuiScreen)this, this.game_settings_1));
            }
            if (p_actionPerformed_1_.id == 107) {
                this.mc.gameSettings.saveOptions();
                IStream lvt_2_2_ = this.mc.getTwitchStream();
                if (lvt_2_2_.func_152936_l() && lvt_2_2_.func_152928_D()) {
                    this.mc.displayGuiScreen((GuiScreen)new GuiStreamOptions((GuiScreen)this, this.game_settings_1));
                } else {
                    GuiStreamUnavailable.func_152321_a((GuiScreen)this);
                }
            }
        }
    }

    public void drawScreen(int p_drawScreen_1_, int p_drawScreen_2_, float p_drawScreen_3_) {
        this.drawCenteredString(this.fontRendererObj, this.field_146442_a, this.width / 2, 15, 0xFFFFFF);
        super.drawScreen(p_drawScreen_1_, p_drawScreen_2_, p_drawScreen_3_);
    }
}

