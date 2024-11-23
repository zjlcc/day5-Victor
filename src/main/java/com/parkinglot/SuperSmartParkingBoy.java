package com.parkinglot;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public Ticket park(Car car) {
        ParkingLot minPositionRateLot = getMinPositionRateLot();
        if(Objects.isNull(minPositionRateLot)){
            throw new NoAvailablePositionException(NO_AVAILABLE_POSITION);
        }
        return minPositionRateLot.park(car);
    }

    private ParkingLot getMinPositionRateLot() {
        return parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFulling())
                .min(Comparator.comparingDouble(ParkingLot::getPositionRate))
                .orElse(null);
    }
}
