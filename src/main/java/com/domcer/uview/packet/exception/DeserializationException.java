/*
 * Decompiled with CFR 0.152.
 */
package com.domcer.uview.packet.exception;

public class DeserializationException
extends Exception {
    private static final long serialVersionUID = 310446237157256052L;
    private boolean serverSide = false;

    public DeserializationException() {
    }

    public DeserializationException(String message) {
        super(message);
    }

    public DeserializationException(String message, boolean serverSide) {
        this(message);
        this.serverSide = serverSide;
    }

    public DeserializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeserializationException(String message, Throwable cause, boolean serverSide) {
        this(message, cause);
        this.serverSide = serverSide;
    }

    public boolean isServerSide() {
        return this.serverSide;
    }
}

