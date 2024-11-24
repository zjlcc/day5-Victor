package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SuperSmartParkingBoyPolicy implements FindParkingLotHandler {
    @Override
    public ParkingLot findParingLot(List<ParkingLot> parkingLots) {
        return parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFulling())
                .min(Comparator.comparingDouble(ParkingLot::getPositionRate))
                .orElse(null);
    }
}
