package com.apartment.app.entity;

public class Apartment extends Room {
    private String ownerName;

    public Apartment(String id, String ownerName) {
        super(id);
        if (ownerName == null || ownerName.isEmpty()) {
            throw new IllegalArgumentException("Owner name cannot be null or empty");
        }
        this.ownerName = ownerName;
    }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) {
        if (ownerName == null || ownerName.isEmpty()) {
            throw new IllegalArgumentException("Owner name cannot be null or empty");
        }
        this.ownerName = ownerName;
    }
}
