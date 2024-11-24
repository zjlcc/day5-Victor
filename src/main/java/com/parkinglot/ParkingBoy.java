package com.parkinglot;

import java.util.List;
import java.util.Objects;

public class ParkingBoy {
    protected List<ParkingLot> parkingLots;
    private static final String UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket.";
    protected static final String NO_AVAILABLE_POSITION = "No available position.";
    private FindParkingLotHandler findParkingLotHandler;

    public ParkingBoy(List<ParkingLot> parkingLots, FindParkingLotHandler findParkingLotHandler) {
        this.parkingLots = parkingLots;
        this.findParkingLotHandler = findParkingLotHandler;
    }

    public Ticket park(Car car) {
        ParkingLot availableLot = findParkingLotHandler.findParingLot(parkingLots);
        checkParkingLot(availableLot);
        return availableLot.park(car);
    }

    protected static void checkParkingLot(ParkingLot parkingLot) {
        if (Objects.isNull(parkingLot)) {
            throw new NoAvailablePositionException(NO_AVAILABLE_POSITION);
        }
    }

    private ParkingLot getAvailableLot() {
        return parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFulling())
                .findFirst()
                .orElse(null);
    }

    public Car fetch(Ticket ticket) {
        ParkingLot carParkedLot = getCarParkedLot(ticket);
        if (Objects.isNull(carParkedLot)) {
            throw new UnrecognizedParkingTicketException(UNRECOGNIZED_PARKING_TICKET);
        }
        return carParkedLot.fetch(ticket);
    }

    private ParkingLot getCarParkedLot(Ticket ticket) {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.isCarParked(ticket))
                .findFirst()
                .orElse(null);
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }
}
