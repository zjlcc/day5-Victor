package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public Ticket park(Car car) {
        ParkingLot morePositionLot = getMorePositionLot();
        checkParkingLot(morePositionLot);
        return morePositionLot.park(car);
    }

    private ParkingLot getMorePositionLot() {
        return parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFulling())
                .min(Comparator.comparingInt(ParkingLot::getUsedSize))
                .orElse(null);
    }
}
