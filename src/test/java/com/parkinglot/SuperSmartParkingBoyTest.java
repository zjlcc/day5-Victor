package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class SuperSmartParkingBoyTest {
    public static final String NO_AVAILABLE_POSITION = "No available position.";
    public static final String UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket.";

    @Test
    void should_in_second_lot_when_park_car_given_second_lot_position_rate_smaller_and_a_super_smart_parking_boy() {
        //Given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(10));
        parkingLots.add(new ParkingLot(10));
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        superSmartParkingBoy.park(new Car());
        //When
        Ticket ticket = superSmartParkingBoy.park(new Car());
        //Then
        ParkingLot parkingLot = superSmartParkingBoy.getParkingLots().get(1);
        assertTrue(parkingLot.getParkingRecords().containsKey(ticket));
    }

    @Test
    void should_in_first_lot_when_park_car_given_two_empty_lots_and_a_smart_parking_boy() {
        //Given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(10));
        parkingLots.add(new ParkingLot(10));
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        //When
        Ticket ticket = superSmartParkingBoy.park(new Car());
        //Then
        ParkingLot parkingLot = superSmartParkingBoy.getParkingLots().get(0);
        assertTrue(parkingLot.getParkingRecords().containsKey(ticket));
    }

    @Test
    void should_return_two_cars_when_fetch_given_two_tickets_and_a_super_smart_parking_boy() {
        //Given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(10));
        parkingLots.add(new ParkingLot(10));
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        Car firstCar = new Car();
        Car secondCar = new Car();
        Ticket firstTicket = superSmartParkingBoy.park(firstCar);
        Ticket secondticket = superSmartParkingBoy.park(secondCar);
        //When
        Car firstFetchedCar = superSmartParkingBoy.fetch(firstTicket);
        Car secondFetchedCar = superSmartParkingBoy.fetch(secondticket);
        //Then
        assertEquals(firstCar, firstFetchedCar);
        assertEquals(secondCar, secondFetchedCar);
    }

    @Test
    void should_print_error_message_when_fetch_car_given_a_wrong_ticket_and_a_super_smart_parking_boy() {
        //Given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(10));
        parkingLots.add(new ParkingLot(10));
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        Ticket ticket = new Ticket();
        //When
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> superSmartParkingBoy.fetch(ticket));
        //Then
        assertEquals(unrecognizedParkingTicketException.getMessage(), UNRECOGNIZED_PARKING_TICKET);
    }

    @Test
    void should_print_error_message_when_fetch_car_given_used_ticket_and_a_super_smart_parking_boy() {
        //Given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot(10));
        parkingLots.add(new ParkingLot(10));
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        Car car = new Car();
        Ticket ticket = superSmartParkingBoy.park(car);
        superSmartParkingBoy.fetch(ticket);
        //When
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> superSmartParkingBoy.fetch(ticket));
        //Then
        assertEquals(unrecognizedParkingTicketException.getMessage(), UNRECOGNIZED_PARKING_TICKET);
    }

    @Test
    void should_print_error_message_when_park_car_given_fulling_parking_lot_and_a_super_parking_boy() {
        //Given
        List<ParkingLot> parkingLots = new ArrayList<>();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        ParkingLot firstLot = new ParkingLot(10);
        parkingLots.add(firstLot);
        ParkingLot secondLot = new ParkingLot(10);
        parkingLots.add(secondLot);
        IntStream.range(0, 20).forEach(i -> {
            Car car = new Car();
            superSmartParkingBoy.park(car);
        });
        //When
        NoAvailablePositionException noAvailablePositionException = assertThrows(NoAvailablePositionException.class, () -> superSmartParkingBoy.park(new Car()));
        //Then
        assertEquals(noAvailablePositionException.getMessage(), NO_AVAILABLE_POSITION);
    }
}
