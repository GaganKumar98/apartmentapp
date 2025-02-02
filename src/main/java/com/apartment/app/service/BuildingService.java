package com.apartment.app.service;

import com.apartment.app.entity.Room;
import com.apartment.app.exception.DuplicateRoomException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BuildingService {
    private static final Logger logger = Logger.getLogger(BuildingService.class.getName());

    private final List<Room> rooms = new ArrayList<>();
    private double requestedTemperature = 20.0;

    public void setRequestedTemperature(double requestedTemperature) {
        if (requestedTemperature < 0) {
            throw new IllegalArgumentException("Requested temperature cannot be negative");
        }
        logger.info(() -> String.format("Setting building requested temperature to %.2f", requestedTemperature));
        this.requestedTemperature = requestedTemperature;
    }

    public double getRequestedTemperature() { return requestedTemperature; }

    public void addRoom(Room room) throws DuplicateRoomException {
        if (rooms.stream().anyMatch(r -> r.getId().equals(room.getId()))) {
            String errorMsg = String.format("Duplicate room ID: %s", room.getId());
            logger.severe(errorMsg);
            throw new DuplicateRoomException(errorMsg);
        }
        rooms.add(room);
        logger.info(() -> String.format("Added room %s to the building", room.getId()));
    }

    public List<Room> getRooms() { return new ArrayList<>(rooms); }

    public void updateHeatingCoolingStatus() {
        logger.info("Updating heating/cooling status for all rooms");
        for (Room room : rooms) {
            double roomTemp = room.getCurrentTemperature();
            if (roomTemp < requestedTemperature) {
                room.setHeatingEnabled(true);
                room.setCoolingEnabled(false);
                logger.fine(() -> String.format("Room %s: Heating enabled (%.2f < %.2f)", room.getId(), roomTemp, requestedTemperature));
            } else if (roomTemp > requestedTemperature) {
                room.setCoolingEnabled(true);
                room.setHeatingEnabled(false);
                logger.fine(() -> String.format("Room %s: Cooling enabled (%.2f > %.2f)", room.getId(), roomTemp, requestedTemperature));
            } else {
                room.setHeatingEnabled(false);
                room.setCoolingEnabled(false);
                logger.fine(() -> String.format("Room %s: No heating/cooling needed (%.2f == %.2f)", room.getId(), roomTemp, requestedTemperature));
            }
        }
    }
}
