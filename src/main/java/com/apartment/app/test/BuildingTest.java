package com.apartment.app.test;

import com.apartment.app.entity.Apartment;
import com.apartment.app.service.BuildingService;

import com.apartment.app.entity.Room;
import com.apartment.app.exception.DuplicateRoomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class BuildingTest {
    private BuildingService building;

    @BeforeEach
    void setUp() {
        building = new BuildingService();
    }

    @Test
    void addRoom_duplicateId_throwsException() throws DuplicateRoomException {
        Room room1 = new Apartment("101", "Owner");
        Room room2 = new Apartment("101", "Another Owner");
        building.addRoom(room1);
        assertThrows(DuplicateRoomException.class, () -> building.addRoom(room2));
    }

    @Test
    void updateHeatingCoolingStatus_roomBelowRequestedTemp_enablesHeating() throws DuplicateRoomException {
        Room room = new TestRoom("test", 18.0);
        building.setRequestedTemperature(20.0);
        building.addRoom(room);
        building.updateHeatingCoolingStatus();
        assertTrue(room.isHeatingEnabled());
        assertFalse(room.isCoolingEnabled());
    }

    @Test
    void updateHeatingCoolingStatus_roomAboveRequestedTemp_enablesCooling() throws DuplicateRoomException {
        Room room = new TestRoom("test", 25.0);
        building.setRequestedTemperature(20.0);
        building.addRoom(room);
        building.updateHeatingCoolingStatus();
        assertTrue(room.isCoolingEnabled());
        assertFalse(room.isHeatingEnabled());
    }

    @Test
    void updateHeatingCoolingStatus_roomTempEqualsRequested_disablesBoth() throws DuplicateRoomException {
        Room room = new TestRoom("test", 20.0);
        building.setRequestedTemperature(20.0);
        building.addRoom(room);
        building.updateHeatingCoolingStatus();
        assertFalse(room.isHeatingEnabled());
        assertFalse(room.isCoolingEnabled());
    }

    private static class TestRoom extends Room {
        private final double currentTemperature;

        public TestRoom(String id, double temp) {
            super(id);
            this.currentTemperature = temp;
        }

        @Override
        public double getCurrentTemperature() {
            return currentTemperature;
        }
    }
}
