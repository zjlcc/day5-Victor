package com.parkinglot;

import java.util.List;
import java.util.Objects;

public class ParkingBoy {
    private List<ParkingLot> parkingLots;
    private static final String UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket.";

    public ParkingBoy(List<ParkingLot> parkingLots){
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
        if(!parkingLots.get(0).isFulling()){
            return parkingLots.get(0).park(car);
        }
        return parkingLots.get(1).park(car);
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
