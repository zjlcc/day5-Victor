package com.parkinglot;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class SmartParkingBoy extends ParkingBoy{
    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public Ticket park(Car car){
        ParkingLot parkingLot = getMorePositionLot();
        if(Objects.isNull(parkingLot)){
            throw new NoAvailablePositionException(NO_AVAILABLE_POSITION);
        }
        return parkingLot.park(car);
    }

    private ParkingLot getMorePositionLot() {
        return parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFulling())
                .min(Comparator.comparingInt(ParkingLot::getUsedSize))
                .orElse(null);
    }
}
