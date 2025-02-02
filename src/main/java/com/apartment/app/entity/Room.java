package com.apartment.app.entity;

import java.util.logging.Logger;

public class Room {
    private static final Logger logger = Logger.getLogger(Room.class.getName());

    private final String id;
    private final double currentTemperature;
    private boolean heatingEnabled;
    private boolean coolingEnabled;

    public Room(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Room ID cannot be null or empty");
        }
        this.id = id;
        this.currentTemperature = 10.0 + (Math.random() * 30.0);
        this.heatingEnabled = false;
        this.coolingEnabled = false;
        logger.info(() -> String.format("Created room %s with initial temperature %.2f", id, currentTemperature));
    }

    public String getId() {
        return id;
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public boolean isHeatingEnabled() {
        return heatingEnabled;
    }

    public void setHeatingEnabled(boolean heatingEnabled) {
        this.heatingEnabled = heatingEnabled;
    }

    public boolean isCoolingEnabled() {
        return coolingEnabled;
    }

    public void setCoolingEnabled(boolean coolingEnabled) {
        this.coolingEnabled = coolingEnabled;
    }

}
