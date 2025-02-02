package com.apartment.app;

import com.apartment.app.entity.Apartment;
import com.apartment.app.entity.CommonRoom;
import com.apartment.app.entity.Room;
import com.apartment.app.exception.DuplicateRoomException;
import com.apartment.app.service.BuildingService;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class buildingcontrol {
    private static final Logger logger = Logger.getLogger(buildingcontrol.class.getName());

    public static void main(String[] args) {
        try {
            BuildingService building = createInitialBuilding();
            displayStatus(building);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\nOptions:");
                System.out.println("1. Change temperature");
                System.out.println("2. Add a room");
                System.out.println("3. Exit");
                System.out.print("Enter choice: ");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("3") || input.equalsIgnoreCase("exit")) {
                    break;
                }

                switch (input) {
                    case "1":
                        System.out.print("Enter new requested temperature: ");
                        String tempInput = scanner.nextLine();
                        try {
                            double newTemp = Double.parseDouble(tempInput);
                            building.setRequestedTemperature(newTemp);
                            building.updateHeatingCoolingStatus();
                            displayStatus(building);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid temperature input.");
                        }
                        break;

                    case "2":
                        System.out.print("Enter room type (Apartment/Common): ");
                        String roomType = scanner.nextLine();
                        try {
                            System.out.print("Enter room ID: ");
                            String roomId = scanner.nextLine();

                            if (roomType.equalsIgnoreCase("Apartment")) {
                                System.out.print("Enter owner name: ");
                                String ownerName = scanner.nextLine();
                                Apartment apartment = new Apartment(roomId, ownerName);
                                building.addRoom(apartment);
                            } else if (roomType.equalsIgnoreCase("Common")) {
                                System.out.print("Enter common room type (Gym/Library/Laundry): ");
                                String commonType = scanner.nextLine().toUpperCase();
                                CommonRoom.Type type = CommonRoom.Type.valueOf(commonType);
                                CommonRoom commonRoom = new CommonRoom(roomId, type);
                                building.addRoom(commonRoom);
                            } else {
                                System.out.println("Invalid room type.");
                            }

                            building.updateHeatingCoolingStatus();
                            displayStatus(building);
                        } catch (IllegalArgumentException | DuplicateRoomException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }
            }

            scanner.close();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred in the application", e);
        }
    }

    private static BuildingService createInitialBuilding() throws DuplicateRoomException {
        BuildingService building = new BuildingService();
        building.setRequestedTemperature(25.0);

        Apartment apartment101 = new Apartment("101", "John Doe");
        Apartment apartment102 = new Apartment("102", "Jane Smith");
        CommonRoom gym = new CommonRoom("GYM1", CommonRoom.Type.GYM);
        CommonRoom library = new CommonRoom("LIB1", CommonRoom.Type.LIBRARY);

        building.addRoom(apartment101);
        building.addRoom(apartment102);
        building.addRoom(gym);
        building.addRoom(library);

        building.updateHeatingCoolingStatus();
        return building;
    }

    private static void displayStatus(BuildingService building) {
        System.out.println("\nBuilding Status:");
        System.out.printf("Requested Temperature: %.2f%n", building.getRequestedTemperature());
        System.out.println("Rooms:");
        for (Room room : building.getRooms()) {
            String typeInfo = "";
            if (room instanceof Apartment) {
                typeInfo = "Apartment, Owner: " + ((Apartment) room).getOwnerName();
            } else if (room instanceof CommonRoom) {
                typeInfo = "Common Room, Type: " + ((CommonRoom) room).getType();
            }
            System.out.printf("- ID: %s, Type: %s, Temperature: %.2f, Heating: %b, Cooling: %b%n",
                    room.getId(), typeInfo, room.getCurrentTemperature(),
                    room.isHeatingEnabled(), room.isCoolingEnabled());
        }
    }
}
