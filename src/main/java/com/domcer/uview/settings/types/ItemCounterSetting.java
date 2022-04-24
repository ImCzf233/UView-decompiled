/*
 * Decompiled with CFR 0.152.
 */
package com.domcer.uview.settings.types;

import com.domcer.uview.settings.ISetting;
import java.util.List;

public class ItemCounterSetting implements ISetting<List<ItemCounterSetting.DisplayItem>> {
    private final String path;
    private final String desc;
    private List<DisplayItem> value;
    private final List<DisplayItem> defValue;

    public ItemCounterSetting(String path, String desc, List<DisplayItem> defValue) {
        this.path = path;
        this.desc = desc;
        this.defValue = defValue;
        this.value = this.defValue;
    }

    @Override
    public List<DisplayItem> defaultValue() {
        return this.defValue;
    }

    @Override
    public List<DisplayItem> value() {
        return this.value;
    }

    @Override
    public void setValue(List<DisplayItem> value) {
        this.value = value;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }

    public static class DisplayItem {
        public int ordinal;
        public boolean hideNone;
        public String itemName;

        public DisplayItem(int ordinal, boolean hideNone, String itemName) {
            this.ordinal = ordinal;
            this.hideNone = hideNone;
            this.itemName = itemName;
        }
    }
}

