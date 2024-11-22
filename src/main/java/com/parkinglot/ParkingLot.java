package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final Map<Ticket,Car> parkingRecords = new HashMap<>();

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
}
