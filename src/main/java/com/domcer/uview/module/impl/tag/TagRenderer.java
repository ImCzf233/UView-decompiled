// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.network.NetworkPlayerInfo
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.WorldRenderer
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.scoreboard.ScoreObjective
 *  net.minecraft.scoreboard.ScorePlayerTeam
 *  net.minecraft.scoreboard.Scoreboard
 *  net.minecraft.scoreboard.Team
 *  net.minecraftforge.client.event.RenderPlayerEvent$Pre
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  org.lwjgl.opengl.GL11
 */
package com.domcer.uview.module.impl.tag;

import com.domcer.uview.module.AbstractModule;
import com.domcer.uview.settings.UViewSettings;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class TagRenderer
extends AbstractModule {
    public static final TagRenderer INSTANCE = new TagRenderer();
    private final Map<UUID, List<String>> tagMap = new ConcurrentHashMap<UUID, List<String>>();
    private final RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();

    @Override
    public void onLoad() {
        MinecraftForge.EVENT_BUS.register((Object)INSTANCE);
    }

    @SubscribeEvent
    public void render(RenderPlayerEvent.Pre e) {
        try {
            if (UViewSettings.tagSetting.value().booleanValue() && e.entity == Minecraft.getMinecraft().getRenderManager().livingPlayer && !Minecraft.getMinecraft().getRenderManager().livingPlayer.isSneaking()) {
                this.renderTagSelf(e.entity, this.getPlayerName(Minecraft.getMinecraft().thePlayer.sendQueue.getPlayerInfo(e.entityPlayer.getUniqueID())), e.x, e.y, e.z);
            }
            this.renderTag(e.entity, e.x, e.y, e.z);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private void renderTagSelf(Entity entity, String p_renderLivingLabel_2_, double x, double y, double z) {
        FontRenderer lvt_12_1_ = Minecraft.getMinecraft().fontRendererObj;
        float lvt_13_1_ = 1.6f;
        float lvt_14_1_ = 0.016666668f * lvt_13_1_;
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)((float)x + 0.0f), (float)((float)y + entity.height + 0.5f), (float)((float)z));
        GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.rotate((float)(-Minecraft.getMinecraft().getRenderManager().playerViewY), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.rotate((float)Minecraft.getMinecraft().getRenderManager().playerViewX, (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.scale((float)(-lvt_14_1_), (float)(-lvt_14_1_), (float)lvt_14_1_);
        GlStateManager.disableLighting();
        GlStateManager.depthMask((boolean)false);
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        Tessellator lvt_15_1_ = Tessellator.getInstance();
        WorldRenderer lvt_16_1_ = lvt_15_1_.getWorldRenderer();
        int lvt_17_1_ = 0;
        int lvt_18_1_ = lvt_12_1_.getStringWidth(p_renderLivingLabel_2_) / 2;
        GlStateManager.disableTexture2D();
        lvt_16_1_.begin(7, DefaultVertexFormats.POSITION_COLOR);
        lvt_16_1_.pos((double)(-lvt_18_1_ - 1), (double)(-1 + lvt_17_1_), 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();
        lvt_16_1_.pos((double)(-lvt_18_1_ - 1), (double)(8 + lvt_17_1_), 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();
        lvt_16_1_.pos((double)(lvt_18_1_ + 1), (double)(8 + lvt_17_1_), 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();
        lvt_16_1_.pos((double)(lvt_18_1_ + 1), (double)(-1 + lvt_17_1_), 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();
        lvt_15_1_.draw();
        GlStateManager.enableTexture2D();
        lvt_12_1_.drawString(p_renderLivingLabel_2_, -lvt_12_1_.getStringWidth(p_renderLivingLabel_2_) / 2, lvt_17_1_, 0x20FFFFFF);
        GlStateManager.enableDepth();
        GlStateManager.depthMask((boolean)true);
        lvt_12_1_.drawString(p_renderLivingLabel_2_, -lvt_12_1_.getStringWidth(p_renderLivingLabel_2_) / 2, lvt_17_1_, -1);
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.popMatrix();
    }

    private String getPlayerName(NetworkPlayerInfo p_getPlayerName_1_) {
        return p_getPlayerName_1_.getDisplayName() != null ? p_getPlayerName_1_.getDisplayName().getFormattedText() : ScorePlayerTeam.formatPlayerName((Team)p_getPlayerName_1_.getPlayerTeam(), (String)p_getPlayerName_1_.getGameProfile().getName());
    }

    public List<String> getTags(UUID uuid) {
        return this.tagMap.getOrDefault(uuid, null);
    }

    public void updateTags(UUID uuid, List<String> lines) {
        this.tagMap.put(uuid, lines);
    }

    public void clear() {
        this.tagMap.clear();
    }

    public void renderTag(Entity entity, double positionX, double positionY, double positionZ) {
        List<String> tags;
        if (!(entity instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer)entity;
        double distance = player.getDistanceToEntity((Entity)Minecraft.getMinecraft().thePlayer);
        if (Minecraft.getMinecraft().gameSettings.hideGUI || player.isInvisible()) {
            return;
        }
        if (!player.isSneaking() && distance <= 40.0 && (tags = this.getTags(player.getUniqueID())) != null) {
            double height = 0.1 + 0.25 * (double)tags.size();
            for (String tag : tags) {
                this.renderTag(player, tag, positionX, positionY + height, positionZ);
                height -= 0.25;
            }
        }
    }

    private void renderTag(EntityPlayer player, String p_renderLivingLabel_2_, double x, double y, double z) {
        Scoreboard scoreboard = player.getWorldScoreboard();
        ScoreObjective scoreObjective = scoreboard.getObjectiveInDisplaySlot(2);
        FontRenderer lvt_12_1_ = Minecraft.getMinecraft().fontRendererObj;
        if (scoreObjective != null) {
            y += (double)lvt_12_1_.FONT_HEIGHT * 1.15 * 0.026666667;
        }
        float lvt_13_1_ = 1.6f;
        float lvt_14_1_ = 0.016666668f * lvt_13_1_;
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)((float)x + 0.0f), (float)((float)y + player.height + 0.5f), (float)((float)z));
        GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.rotate((float)(-this.renderManager.playerViewY), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.rotate((float)this.renderManager.playerViewX, (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.scale((float)(-lvt_14_1_), (float)(-lvt_14_1_), (float)lvt_14_1_);
        GlStateManager.disableLighting();
        GlStateManager.depthMask((boolean)false);
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        Tessellator lvt_15_1_ = Tessellator.getInstance();
        WorldRenderer lvt_16_1_ = lvt_15_1_.getWorldRenderer();
        int lvt_17_1_ = 0;
        int lvt_18_1_ = lvt_12_1_.getStringWidth(p_renderLivingLabel_2_) / 2;
        GlStateManager.disableTexture2D();
        lvt_16_1_.begin(7, DefaultVertexFormats.POSITION_COLOR);
        lvt_16_1_.pos((double)(-lvt_18_1_ - 1), (double)(-1 + lvt_17_1_), 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();
        lvt_16_1_.pos((double)(-lvt_18_1_ - 1), (double)(8 + lvt_17_1_), 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();
        lvt_16_1_.pos((double)(lvt_18_1_ + 1), (double)(8 + lvt_17_1_), 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();
        lvt_16_1_.pos((double)(lvt_18_1_ + 1), (double)(-1 + lvt_17_1_), 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();
        lvt_15_1_.draw();
        GlStateManager.enableTexture2D();
        lvt_12_1_.drawString(p_renderLivingLabel_2_, -lvt_12_1_.getStringWidth(p_renderLivingLabel_2_) / 2, lvt_17_1_, 0x20FFFFFF);
        GlStateManager.enableDepth();
        GlStateManager.depthMask((boolean)true);
        lvt_12_1_.drawString(p_renderLivingLabel_2_, -lvt_12_1_.getStringWidth(p_renderLivingLabel_2_) / 2, lvt_17_1_, -1);
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.popMatrix();
    }
}

