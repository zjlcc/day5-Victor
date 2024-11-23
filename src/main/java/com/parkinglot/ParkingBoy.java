package com.parkinglot;

import java.util.List;
import java.util.Objects;

public class ParkingBoy {
    private List<ParkingLot> parkingLots;
    private static final String UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket.";
    private static final String NO_AVAILABLE_POSITION = "No available position.";

    public ParkingBoy(List<ParkingLot> parkingLots){
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
        ParkingLot availableLot = getAvailableLot();
        if(Objects.isNull(availableLot)){
            throw new NoAvailablePositionException(NO_AVAILABLE_POSITION);
        }
        return availableLot.park(car);
    }

    private ParkingLot getAvailableLot() {
        return parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFulling())
                .findFirst()
                .orElse(null);
    }

    public Car fetch(Ticket ticket) {
        ParkingLot carParkedLot = getCarParkedLot(ticket);
        if(Objects.isNull(carParkedLot)) {
            throw new UnrecognizedParkingTicketException(UNRECOGNIZED_PARKING_TICKET);
        }
        return carParkedLot.fetch(ticket);
    }

    private ParkingLot getCarParkedLot(Ticket ticket){
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.isCarParked(ticket))
                .findFirst()
                .orElse(null);
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }
}
