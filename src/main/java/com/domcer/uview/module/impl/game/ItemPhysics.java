// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.WorldRenderer
 *  net.minecraft.client.renderer.block.model.ItemCameraTransforms$TransformType
 *  net.minecraft.client.renderer.entity.RenderEntityItem
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.client.resources.model.IBakedModel
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.ForgeHooksClient
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.common.gameevent.TickEvent$RenderTickEvent
 *  org.lwjgl.opengl.GL11
 */
package com.domcer.uview.module.impl.game;

import com.domcer.uview.module.AbstractModule;
import com.domcer.uview.settings.UViewSettings;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.opengl.GL11;

public class ItemPhysics
extends AbstractModule {
    public static final ItemPhysics INSTANCE = new ItemPhysics();
    private static long tick;
    private static float rotation;
    private static final Random random;

    @Override
    public void onLoad() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void renderTick(TickEvent.RenderTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            tick = System.nanoTime();
        }
    }

    public static ResourceLocation getEntityTexture() {
        return TextureMap.locationBlocksTexture;
    }

    public static void setPositionAndRotation2(EntityItem item, double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean p_180426_10_) {
        item.setPosition(x, y, z);
    }

    private static int func_177077_a(RenderEntityItem renderer, EntityItem itemIn, double p_177077_2_, double p_177077_4_, double p_177077_6_, float p_177077_8_, IBakedModel p_177077_9_) {
        float f6;
        ItemStack itemstack = itemIn.getEntityItem();
        Item item = itemstack.getItem();
        if (item == null) {
            return 0;
        }
        boolean flag = p_177077_9_.isGui3d();
        int i = ItemPhysics.getModelCount(itemstack);
        float f = 0.25f;
        float f1 = renderer.shouldBob() ? MathHelper.sin((float)(((float)itemIn.getAge() + p_177077_8_) / 10.0f + itemIn.hoverStart)) * 0.1f + 0.1f : 0.0f;
        float f2 = p_177077_9_.getItemCameraTransforms().getTransform((ItemCameraTransforms.TransformType)ItemCameraTransforms.TransformType.GROUND).scale.y;
        GlStateManager.translate((float)((float)p_177077_2_), (float)((float)p_177077_4_ + f1 + 0.25f * f2), (float)((float)p_177077_6_));
        if (flag || renderer.getRenderManager().options != null) {
            f6 = (((float)itemIn.getAge() + p_177077_8_) / 20.0f + itemIn.hoverStart) * 57.295776f;
            GlStateManager.rotate((float)f6, (float)0.0f, (float)1.0f, (float)0.0f);
        }
        if (!flag) {
            f6 = -0.0f * (float)(i - 1) * 0.5f;
            float f4 = -0.0f * (float)(i - 1) * 0.5f;
            float f5 = -0.046875f * (float)(i - 1) * 0.5f;
            GlStateManager.translate((float)f6, (float)f4, (float)f5);
        }
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        return i;
    }

    public static void doRender(RenderEntityItem renderer, Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        EntityItem item;
        ItemStack itemstack;
        if (!UViewSettings.itemPhysicsEnabled.value().booleanValue()) {
            EntityItem item2 = (EntityItem)entity;
            ItemStack itemstack2 = item2.getEntityItem();
            random.setSeed(187L);
            renderer.bindTexture(ItemPhysics.getEntityTexture());
            renderer.getRenderManager().renderEngine.getTexture(ItemPhysics.getEntityTexture()).setBlurMipmap(false, false);
            GlStateManager.pushMatrix();
            GlStateManager.enableRescaleNormal();
            GlStateManager.alphaFunc((int)516, (float)0.1f);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
            Minecraft mc = Minecraft.getMinecraft();
            IBakedModel ibakedmodel = mc.getRenderItem().getItemModelMesher().getItemModel(itemstack2);
            int i = ItemPhysics.func_177077_a(renderer, (EntityItem)entity, x, y, z, partialTicks, ibakedmodel);
            for (int j = 0; j < i; ++j) {
                float f2;
                float f1;
                float f;
                if (ibakedmodel.isGui3d()) {
                    GlStateManager.pushMatrix();
                    if (j > 0) {
                        f = (random.nextFloat() * 2.0f - 1.0f) * 0.15f;
                        f1 = (random.nextFloat() * 2.0f - 1.0f) * 0.15f;
                        f2 = (random.nextFloat() * 2.0f - 1.0f) * 0.15f;
                        GlStateManager.translate((float)f, (float)f1, (float)f2);
                    }
                    GlStateManager.scale((float)0.5f, (float)0.5f, (float)0.5f);
                    ibakedmodel = ForgeHooksClient.handleCameraTransforms((IBakedModel)ibakedmodel, (ItemCameraTransforms.TransformType)ItemCameraTransforms.TransformType.GROUND);
                    mc.getRenderItem().renderItem(itemstack2, ibakedmodel);
                    GlStateManager.popMatrix();
                    continue;
                }
                GlStateManager.pushMatrix();
                ibakedmodel = ForgeHooksClient.handleCameraTransforms((IBakedModel)ibakedmodel, (ItemCameraTransforms.TransformType)ItemCameraTransforms.TransformType.GROUND);
                mc.getRenderItem().renderItem(itemstack2, ibakedmodel);
                GlStateManager.popMatrix();
                f = ibakedmodel.getItemCameraTransforms().ground.scale.x;
                f1 = ibakedmodel.getItemCameraTransforms().ground.scale.y;
                f2 = ibakedmodel.getItemCameraTransforms().ground.scale.z;
                GlStateManager.translate((float)(0.0f * f), (float)(0.0f * f1), (float)(0.046875f * f2));
            }
            GlStateManager.popMatrix();
            GlStateManager.disableRescaleNormal();
            GlStateManager.disableBlend();
            renderer.bindTexture(ItemPhysics.getEntityTexture());
            renderer.getRenderManager().renderEngine.getTexture(ItemPhysics.getEntityTexture()).restoreLastBlurMipmap();
            if (entity.hasCustomName() && entity.getAlwaysRenderNameTagForRender()) {
                ItemPhysics.renderLivingLabel(entity, entity.getDisplayName().getFormattedText(), x, y, z);
            }
            return;
        }
        rotation = (float)(System.nanoTime() - tick) / 2500000.0f * UViewSettings.itemPhysicsRotateSpeed.value().floatValue();
        Minecraft mc = Minecraft.getMinecraft();
        if (!mc.inGameHasFocus) {
            rotation = 0.0f;
        }
        int i = (itemstack = (item = (EntityItem)entity).getEntityItem()) != null && itemstack.getItem() != null ? Item.getIdFromItem((Item)itemstack.getItem()) + itemstack.getMetadata() : 187;
        if (itemstack == null) {
            return;
        }
        random.setSeed(i);
        boolean flag = true;
        renderer.bindTexture(ItemPhysics.getEntityTexture());
        renderer.getRenderManager().renderEngine.getTexture(ItemPhysics.getEntityTexture()).setBlurMipmap(false, false);
        GlStateManager.enableRescaleNormal();
        GlStateManager.alphaFunc((int)516, (float)0.1f);
        GlStateManager.enableBlend();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        GlStateManager.pushMatrix();
        IBakedModel ibakedmodel = mc.getRenderItem().getItemModelMesher().getItemModel(itemstack);
        boolean flag1 = ibakedmodel.isGui3d();
        boolean is3D = ibakedmodel.isGui3d();
        int j = ItemPhysics.getModelCount(itemstack);
        GlStateManager.translate((float)((float)x), (float)((float)y), (float)((float)z));
        if (ibakedmodel.isGui3d()) {
            GlStateManager.scale((float)0.5f, (float)0.5f, (float)0.5f);
        }
        GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)item.rotationYaw, (float)0.0f, (float)0.0f, (float)1.0f);
        if (is3D) {
            GlStateManager.translate((double)0.0, (double)0.0, (double)-0.08);
        } else {
            GlStateManager.translate((double)0.0, (double)0.0, (double)-0.04);
        }
        if (is3D || mc.getRenderManager().options != null) {
            double rotation;
            if (is3D) {
                if (!item.onGround) {
                    rotation = (double)ItemPhysics.rotation * 2.0;
                    item.rotationPitch = (float)((double)item.rotationPitch + rotation);
                }
            } else if (!(Double.isNaN(item.posX) || Double.isNaN(item.posY) || Double.isNaN(item.posZ) || item.worldObj == null)) {
                if (item.onGround) {
                    item.rotationPitch = 0.0f;
                } else {
                    rotation = (double)ItemPhysics.rotation * 2.0;
                    item.rotationPitch = (float)((double)item.rotationPitch + rotation);
                }
            }
            GlStateManager.rotate((float)item.rotationPitch, (float)1.0f, (float)0.0f, (float)0.0f);
        }
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        for (int k = 0; k < j; ++k) {
            if (flag1) {
                GlStateManager.pushMatrix();
                if (k > 0) {
                    float f7 = (random.nextFloat() * 2.0f - 1.0f) * 0.15f;
                    float f9 = (random.nextFloat() * 2.0f - 1.0f) * 0.15f;
                    float f6 = (random.nextFloat() * 2.0f - 1.0f) * 0.15f;
                    GlStateManager.translate((float)(renderer.shouldSpreadItems() ? f7 : 0.0f), (float)(renderer.shouldSpreadItems() ? f9 : 0.0f), (float)f6);
                }
                ibakedmodel = ForgeHooksClient.handleCameraTransforms((IBakedModel)ibakedmodel, (ItemCameraTransforms.TransformType)ItemCameraTransforms.TransformType.GROUND);
                mc.getRenderItem().renderItem(itemstack, ibakedmodel);
                GlStateManager.popMatrix();
                continue;
            }
            GlStateManager.pushMatrix();
            ibakedmodel = ForgeHooksClient.handleCameraTransforms((IBakedModel)ibakedmodel, (ItemCameraTransforms.TransformType)ItemCameraTransforms.TransformType.GROUND);
            mc.getRenderItem().renderItem(itemstack, ibakedmodel);
            GlStateManager.popMatrix();
            GlStateManager.translate((float)0.0f, (float)0.0f, (float)0.05375f);
        }
        GlStateManager.popMatrix();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableBlend();
        renderer.bindTexture(ItemPhysics.getEntityTexture());
        renderer.getRenderManager().renderEngine.getTexture(ItemPhysics.getEntityTexture()).restoreLastBlurMipmap();
        if (entity.hasCustomName() && entity.getAlwaysRenderNameTagForRender()) {
            ItemPhysics.renderLivingLabel(entity, entity.getDisplayName().getFormattedText(), x, y, z);
        }
    }

    public static void renderLivingLabel(Entity entity, String text, double x, double y, double z) {
        double lvt_10_1_ = entity.getDistanceSqToEntity(Minecraft.getMinecraft().getRenderManager().livingPlayer);
        if (lvt_10_1_ < 200.0) {
            FontRenderer lvt_12_1_ = Minecraft.getMinecraft().fontRendererObj;
            float lvt_13_1_ = 1.6f;
            float lvt_14_1_ = 0.016666668f * lvt_13_1_;
            GlStateManager.pushMatrix();
            GlStateManager.translate((double)(x + 0.0), (double)(y + (double)entity.height + 0.5), (double)z);
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
            int lvt_18_1_ = lvt_12_1_.getStringWidth(text) / 2;
            GlStateManager.disableTexture2D();
            lvt_16_1_.begin(7, DefaultVertexFormats.POSITION_COLOR);
            lvt_16_1_.pos((double)(-lvt_18_1_ - 1), (double)(-1 + lvt_17_1_), 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();
            lvt_16_1_.pos((double)(-lvt_18_1_ - 1), (double)(8 + lvt_17_1_), 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();
            lvt_16_1_.pos((double)(lvt_18_1_ + 1), (double)(8 + lvt_17_1_), 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();
            lvt_16_1_.pos((double)(lvt_18_1_ + 1), (double)(-1 + lvt_17_1_), 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();
            lvt_15_1_.draw();
            GlStateManager.enableTexture2D();
            lvt_12_1_.drawString(text, -lvt_12_1_.getStringWidth(text) / 2, lvt_17_1_, 0x20FFFFFF);
            GlStateManager.enableDepth();
            GlStateManager.depthMask((boolean)true);
            lvt_12_1_.drawString(text, -lvt_12_1_.getStringWidth(text) / 2, lvt_17_1_, -1);
            GlStateManager.enableLighting();
            GlStateManager.disableBlend();
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GlStateManager.popMatrix();
        }
    }

    public static int getModelCount(ItemStack stack) {
        int i = 1;
        if (stack.stackSize > 48) {
            i = 5;
        } else if (stack.stackSize > 32) {
            i = 4;
        } else if (stack.stackSize > 16) {
            i = 3;
        } else if (stack.stackSize > 1) {
            i = 2;
        }
        return i;
    }

    static {
        random = new Random();
    }
}

