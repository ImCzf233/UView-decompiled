/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.IFMLLoadingPlugin
 *  net.minecraftforge.fml.relauncher.IFMLLoadingPlugin$SortingIndex
 */
package com.domcer.uview.transformer;

import com.domcer.uview.transformer.LaunchWrapper;
import java.util.Map;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.SortingIndex(value=0x7FFFFFFF)
public class ForgePlugin
implements IFMLLoadingPlugin {
    public String[] getASMTransformerClass() {
        return new String[]{LaunchWrapper.class.getName()};
    }

    public String getModContainerClass() {
        return null;
    }

    public String getSetupClass() {
        return null;
    }

    public void injectData(Map<String, Object> data) {
    }

    public String getAccessTransformerClass() {
        return null;
    }
}

