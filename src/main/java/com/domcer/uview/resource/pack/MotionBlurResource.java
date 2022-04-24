// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋
// 王航我爱你，关注王航顿顿解馋

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.client.resources.data.IMetadataSection
 *  net.minecraft.util.ResourceLocation
 *  org.apache.commons.io.IOUtils
 */
package com.domcer.uview.resource.pack;

import com.domcer.uview.settings.UViewSettings;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Locale;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;

public class MotionBlurResource
implements IResource {
    public static final MotionBlurResource INSTANCE = new MotionBlurResource();
    private static final String JSON = "{\"targets\":[\"swap\",\"previous\"],\"passes\":[{\"name\":\"phosphor\",\"intarget\":\"minecraft:main\",\"outtarget\":\"swap\",\"auxtargets\":[{\"name\":\"PrevSampler\",\"id\":\"previous\"}],\"uniforms\":[{\"name\":\"Phosphor\",\"values\":[%.2f, %.2f, %.2f]}]},{\"name\":\"blit\",\"intarget\":\"swap\",\"outtarget\":\"previous\"},{\"name\":\"blit\",\"intarget\":\"swap\",\"outtarget\":\"minecraft:main\"}]}";

    public ResourceLocation getResourceLocation() {
        return null;
    }

    public InputStream getInputStream() {
        double amount = 0.7 + (double)UViewSettings.motionBlurAmount.value().intValue() / 100.0 * 3.0 - 0.01;
        return IOUtils.toInputStream((String)String.format(Locale.ENGLISH, JSON, amount, amount, amount), (Charset)Charset.defaultCharset());
    }

    public boolean hasMetadata() {
        return false;
    }

    public <T extends IMetadataSection> T getMetadata(String in) {
        return null;
    }

    public String getResourcePackName() {
        return null;
    }
}

