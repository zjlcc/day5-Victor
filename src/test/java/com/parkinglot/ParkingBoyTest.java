package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {
    private static final String NO_AVAILABLE_POSITION = "No available position.";
    private static final String UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket.";
    private static final FindParkingLotHandler PARKING_BOY_POLICY = new ParkingBoyPolicy();
    @Test
    void should_parking_first_lot_when_park_given_two_free_lot_and_a_parking_boy() {
        //Given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(10));
        parkingLots.add(new ParkingLot(10));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, PARKING_BOY_POLICY);
        Car car = new Car();
        //When
        Ticket ticket = parkingBoy.park(car);
        //Then
        ParkingLot carParkedLot = parkingBoy.getParkingLots().get(0);
        assertTrue(carParkedLot.getParkingRecords().containsKey(ticket));
    }

    @Test
    void should_parking_second_lot_when_park_given_first_full_second_available_and_a_parking_boy() {
        //Given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, PARKING_BOY_POLICY);
        ParkingLot firstLot = new ParkingLot(10);
        parkingLots.add(firstLot);
        ParkingLot secondLot = new ParkingLot(10);
        parkingLots.add(secondLot);
        IntStream.range(0, 10).forEach(i -> {
            Car car = new Car();
            parkingBoy.park(car);
        });
        //When
        Ticket ticket = parkingBoy.park(new Car());
        //Then
        ParkingLot carParkedLot = parkingBoy.getParkingLots().get(1);
        assertTrue(carParkedLot.getParkingRecords().containsKey(ticket));
    }

    @Test
    void should_return_two_cars_when_fetch_given_two_tickets_and_a_parking_boy() {
        //Given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(10));
        parkingLots.add(new ParkingLot(10));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, PARKING_BOY_POLICY);
        Car firstCar = new Car();
        Car secondCar = new Car();
        Ticket firstTicket = parkingBoy.park(firstCar);
        Ticket secondticket = parkingBoy.park(secondCar);
        //When
        Car firstFetchedCar = parkingBoy.fetch(firstTicket);
        Car secondFetchedCar = parkingBoy.fetch(secondticket);
        //Then
        assertEquals(firstCar, firstFetchedCar);
        assertEquals(secondCar, secondFetchedCar);
    }

    @Test
    void should_print_error_message_when_fetch_car_given_a_wrong_ticket_and_a_parking_boy() {
        //Given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(10));
        parkingLots.add(new ParkingLot(10));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, PARKING_BOY_POLICY);
        Ticket ticket = new Ticket();
        //When
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(ticket));
        //Then
        assertEquals(unrecognizedParkingTicketException.getMessage(), UNRECOGNIZED_PARKING_TICKET);
    }

    @Test
    void should_print_error_message_when_fetch_car_given_used_ticket_and_a_parking_boy() {
        //Given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(10));
        parkingLots.add(new ParkingLot(10));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, PARKING_BOY_POLICY);
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);
        parkingBoy.fetch(ticket);
        //When
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(ticket));
        //Then
        assertEquals(unrecognizedParkingTicketException.getMessage(), UNRECOGNIZED_PARKING_TICKET);
    }

    @Test
    void should_print_error_message_when_park_car_given_fulling_parking_lot_and_a_parking_boy() {
        //Given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, PARKING_BOY_POLICY);
        ParkingLot firstLot = new ParkingLot(10);
        parkingLots.add(firstLot);
        ParkingLot secondLot = new ParkingLot(10);
        parkingLots.add(secondLot);
        IntStream.range(0, 20).forEach(i -> {
            Car car = new Car();
            parkingBoy.park(car);
        });
        //When
        NoAvailablePositionException noAvailablePositionException = assertThrows(NoAvailablePositionException.class, () -> parkingBoy.park(new Car()));
        //Then
        assertEquals(noAvailablePositionException.getMessage(), NO_AVAILABLE_POSITION);
    }
}
