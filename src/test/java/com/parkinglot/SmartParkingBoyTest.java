package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class SmartParkingBoyTest {
    public static final String NO_AVAILABLE_POSITION = "No available position.";
    public static final String UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket.";
    @Test
    void should_in_first_lot_when_park_car_given_two_empty_lots_and_a_smart_parking_boy() {
        //Given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(10));
        parkingLots.add(new ParkingLot(10));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        //When
        Ticket ticket = smartParkingBoy.park(new Car());
        //Then
        ParkingLot parkingLot = smartParkingBoy.getParkingLots().get(0);
        assertTrue(parkingLot.getParkingRecords().containsKey(ticket));
    }

    @Test
    void should_in_second_lot_when_park_car_given_second_more_empty_lots_and_a_smart_parking_boy() {
        //Given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot firstLot = new ParkingLot(10);
        parkingLots.add(firstLot);
        ParkingLot secondLot = new ParkingLot(10);
        parkingLots.add(secondLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        smartParkingBoy.park(new Car());
        //When
        Ticket ticket = smartParkingBoy.park(new Car());
        //Then
        ParkingLot parkingLot = smartParkingBoy.getParkingLots().get(1);
        assertTrue(parkingLot.getParkingRecords().containsKey(ticket));
    }

    @Test
    void should_return_two_cars_when_fetch_given_two_tickets_and_a_smart_parking_boy() {
        //Given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(10));
        parkingLots.add(new ParkingLot(10));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car firstCar = new Car();
        Car secondCar = new Car();
        Ticket firstTicket = smartParkingBoy.park(firstCar);
        Ticket secondticket = smartParkingBoy.park(secondCar);
        //When
        Car firstFetchedCar = smartParkingBoy.fetch(firstTicket);
        Car secondFetchedCar = smartParkingBoy.fetch(secondticket);
        //Then
        assertEquals(firstCar, firstFetchedCar);
        assertEquals(secondCar, secondFetchedCar);
    }

    @Test
    void should_print_error_message_when_fetch_car_given_a_wrong_ticket_and_a_smart_parking_boy() {
        //Given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(10));
        parkingLots.add(new ParkingLot(10));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Ticket ticket = new Ticket();
        //When
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> smartParkingBoy.fetch(ticket));
        //Then
        assertEquals(unrecognizedParkingTicketException.getMessage(), UNRECOGNIZED_PARKING_TICKET);
    }

    @Test
    void should_print_error_message_when_fetch_car_given_used_ticket_and_a_smart_parking_boy() {
        //Given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(10));
        parkingLots.add(new ParkingLot(10));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();
        Ticket ticket = smartParkingBoy.park(car);
        smartParkingBoy.fetch(ticket);
        //When
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> smartParkingBoy.fetch(ticket));
        //Then
        assertEquals(unrecognizedParkingTicketException.getMessage(), UNRECOGNIZED_PARKING_TICKET);
    }

    @Test
    void should_print_error_message_when_park_car_given_fulling_parking_lot_and_a_parking_boy() {
        //Given
        List<ParkingLot> parkingLots = new ArrayList<>();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        ParkingLot firstLot = new ParkingLot(10);
        parkingLots.add(firstLot);
        ParkingLot secondLot = new ParkingLot(10);
        parkingLots.add(secondLot);
        IntStream.range(0, 20).forEach(i -> {
            Car car = new Car();
            smartParkingBoy.park(car);
        });
        //When
        NoAvailablePositionException noAvailablePositionException = assertThrows(NoAvailablePositionException.class, () -> smartParkingBoy.park(new Car()));
        //Then
        assertEquals(noAvailablePositionException.getMessage(), NO_AVAILABLE_POSITION);
    }
}
