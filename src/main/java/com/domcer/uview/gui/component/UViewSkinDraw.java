// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.domcer.uview.gui.component;

import com.domcer.uview.gui.component.UViewEntityDraw;
import com.domcer.uview.util.FakeEntityUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class UViewSkinDraw
extends UViewEntityDraw {
    public ResourceLocation locationSkin;

    public UViewSkinDraw(int id, int x, int y, ResourceLocation locationSkin) {
        this(id, x, y, 64, 64, locationSkin);
    }

    public UViewSkinDraw(int id, int x, int y, int widthIn, int heightIn, ResourceLocation locationSkin) {
        super(id, x, y, widthIn, heightIn, (EntityLivingBase)FakeEntityUtils.createFakePlayer(Minecraft.getMinecraft(), locationSkin));
        this.locationSkin = locationSkin;
    }
}

