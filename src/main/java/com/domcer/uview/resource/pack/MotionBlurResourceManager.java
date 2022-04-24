// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.resources.FallbackResourceManager
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.client.resources.data.IMetadataSerializer
 *  net.minecraft.util.ResourceLocation
 */
package com.domcer.uview.resource.pack;

import com.domcer.uview.resource.pack.MotionBlurResource;
import java.util.List;
import java.util.Set;
import net.minecraft.client.resources.FallbackResourceManager;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;

public class MotionBlurResourceManager
extends FallbackResourceManager
implements IResourceManager {
    public MotionBlurResourceManager(IMetadataSerializer frmMetadataSerializerIn) {
        super(frmMetadataSerializerIn);
    }

    public Set<String> getResourceDomains() {
        return null;
    }

    public IResource getResource(ResourceLocation location) {
        return MotionBlurResource.INSTANCE;
    }

    public List<IResource> getAllResources(ResourceLocation location) {
        return null;
    }
}

