package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private static final int CAPACITY = 10;
    private final Map<Ticket, Car> parkingRecords = new HashMap<>(CAPACITY);

    public Ticket park(Car car) {
        Ticket ticket = new Ticket();
        parkingRecords.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        Car car = parkingRecords.get(ticket);
        parkingRecords.remove(ticket);
        return car;
    }

    public boolean isFulling() {
        return parkingRecords.size() == CAPACITY;
    }

    public boolean isCarParked(Ticket ticket){
        return parkingRecords.containsKey(ticket);
    }

    public Map<Ticket, Car> getParkingRecords() {
        return parkingRecords;
    }
}
