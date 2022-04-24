/*
 * Decompiled with CFR 0.152.
 */
package com.domcer.uview.module.impl.game.blockoverlay;

import com.domcer.uview.util.SwitchableEnum;

public enum BlockOverlayMode implements SwitchableEnum<BlockOverlayMode>
{
    NONE("\u65e0"),
    DEFAULT("\u9ed8\u8ba4"),
    OUTLINE("\u8f6e\u5ed3"),
    FULL("\u5168\u90e8");

    private final String name;

    private BlockOverlayMode(String name) {
        this.name = name;
    }

    @Override
    public String displayName() {
        return this.name;
    }

    public BlockOverlayMode next() {
        BlockOverlayMode[] modes = BlockOverlayMode.values();
        return modes[(this.ordinal() + 1) % modes.length];
    }
}

