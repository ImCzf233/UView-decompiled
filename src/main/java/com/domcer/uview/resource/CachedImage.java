/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 */
package com.domcer.uview.resource;

import com.google.gson.JsonObject;

public class CachedImage {
    private String id;
    private JsonObject jsonObject;

    public CachedImage(String id, JsonObject jsonObject) {
        this.id = id;
        this.jsonObject = jsonObject;
    }

    public String getId() {
        return this.id;
    }

    public JsonObject getJsonObject() {
        return this.jsonObject;
    }
}

