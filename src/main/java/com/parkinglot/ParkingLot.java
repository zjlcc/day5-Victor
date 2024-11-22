package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private static final int CAPACITY = 10;
    private static final String UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket.";
    private static final String NO_AVAILABLE_POSITION = "No available position.";
    private final Map<Ticket, Car> parkingRecords = new HashMap<>(CAPACITY);

    public Ticket park(Car car) {
        if (parkingRecords.size() == CAPACITY) {
            throw new RuntimeException(NO_AVAILABLE_POSITION);
        }
        Ticket ticket = new Ticket();
        parkingRecords.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        if(!parkingRecords.containsKey(ticket)){
            throw new RuntimeException(UNRECOGNIZED_PARKING_TICKET);
        }
        Car car = parkingRecords.get(ticket);
        parkingRecords.remove(ticket);
        return car;
    }
}
