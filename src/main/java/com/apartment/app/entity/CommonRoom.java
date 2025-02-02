package com.apartment.app.entity;

public class CommonRoom extends Room {
    public enum Type { GYM, LIBRARY, LAUNDRY }

    private final Type type;

    public CommonRoom(String id, Type type) {
        super(id);
        if (type == null) {
            throw new IllegalArgumentException("Common room type cannot be null");
        }
        this.type = type;
    }

    public Type getType() { return type; }
}
