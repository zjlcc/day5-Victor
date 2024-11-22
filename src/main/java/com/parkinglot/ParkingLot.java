package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private static final int CAPACITY = 10;
    private static final String UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket.";
    private final Map<Ticket, Car> parkingRecords = new HashMap<>(CAPACITY);

    public Ticket park(Car car) {
        if (parkingRecords.size() == CAPACITY) {
            return null;
        }
        Ticket ticket = new Ticket();
        parkingRecords.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        if(!parkingRecords.containsKey(ticket)){
            System.out.println(UNRECOGNIZED_PARKING_TICKET);
            return null;
        }
        Car car = parkingRecords.get(ticket);
        parkingRecords.remove(ticket);
        return car;
    }
}
