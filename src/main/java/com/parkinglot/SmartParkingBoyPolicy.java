package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoyPolicy implements FindParkingLotHandler {
    @Override
    public ParkingLot findParingLot(List<ParkingLot> parkingLots) {
        return parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFulling())
                .min(Comparator.comparingInt(ParkingLot::getUsedSize))
                .orElse(null);
    }
}
