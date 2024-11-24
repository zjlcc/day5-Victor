package com.parkinglot;

import java.util.List;

public class ParkingBoyPolicy implements FindParkingLotHandler {
    @Override
    public ParkingLot findParingLot(List<ParkingLot> parkingLots) {
        return parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFulling())
                .findFirst()
                .orElse(null);
    }
}
