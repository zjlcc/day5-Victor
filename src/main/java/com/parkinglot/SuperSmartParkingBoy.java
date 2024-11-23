package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public Ticket park(Car car) {
        ParkingLot minPositionRateLot = getMinPositionRateLot();
        checkParkingLot(minPositionRateLot);
        return minPositionRateLot.park(car);
    }

    private ParkingLot getMinPositionRateLot() {
        return parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFulling())
                .min(Comparator.comparingDouble(ParkingLot::getPositionRate))
                .orElse(null);
    }
}
