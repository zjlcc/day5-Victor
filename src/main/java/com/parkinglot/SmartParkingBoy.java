package com.parkinglot;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class SmartParkingBoy extends ParkingBoy{
    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public Ticket park(Car car){
        return parkingLots.stream()
                .min(Comparator.comparingInt(ParkingLot::getUsedSize))
                .get()
                .park(car);
    }
}
