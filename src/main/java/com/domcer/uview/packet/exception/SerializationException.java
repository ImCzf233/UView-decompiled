/*
 * Decompiled with CFR 0.152.
 */
package com.domcer.uview.packet.exception;

public class SerializationException
extends Exception {
    private static final long serialVersionUID = 5668965722686668067L;
    private boolean serverSide = false;

    public SerializationException() {
    }

    public SerializationException(String message) {
        super(message);
    }

    public SerializationException(String message, boolean serverSide) {
        this(message);
        this.serverSide = serverSide;
    }

    public SerializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SerializationException(String message, Throwable cause, boolean serverSide) {
        this(message, cause);
        this.serverSide = serverSide;
    }

    public boolean isServerSide() {
        return this.serverSide;
    }
}

