package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    void should_return_ticket_when_park_given_a_car() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        //When
        Ticket ticket = parkingLot.park(car);
        //Then
        assertNotNull(ticket);
    }

    @Test
    void should_return_the_car_when_fetch_given_a_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        //When
        Car fetchedCar = parkingLot.fetch(ticket);
        //Then
        assertEquals(fetchedCar, car);
    }

    @Test
    void should_return_nothing_when_fetch_car_given_a_wrong_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Ticket ticket = new Ticket();
        //When
        Car fetchedCar = parkingLot.fetch(ticket);
        //Then
        assertNull(fetchedCar);
    }

    @Test
    void should_return_two_parked_cars_when_fetch_car_given_two_tickets() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car firstCar = new Car();
        Car secondCar = new Car();
        Ticket firstTicket = parkingLot.park(firstCar);
        Ticket secondticket = parkingLot.park(secondCar);
        //When
        Car fetchedFirstCar = parkingLot.fetch(firstTicket);
        Car fetchedSecondCar = parkingLot.fetch(secondticket);
        //Then
        assertEquals(fetchedFirstCar, firstCar);
        assertEquals(fetchedSecondCar, secondCar);
    }

    @Test
    void should_return_nothing_when_fetch_car_given_used_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        parkingLot.fetch(ticket);
        //When
        Car fetchedCar = parkingLot.fetch(ticket);
        //Then
        assertNull(fetchedCar);
    }

    @Test
    void should_return_nothing_when_park_car_given_parking_lot_fulling() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        IntStream.range(0, 10).forEach(i -> {
            Car car = new Car();
            Ticket ticket = parkingLot.park(car);
        });
        //When
        Ticket ticket = parkingLot.park(new Car());
        //Then
        assertNull(ticket);
    }

    @Test
    void should_print_error_message_when_fetch_car_given_unrecognized_ticket(){
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Ticket ticket = new Ticket();
        //When
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLot.fetch(ticket));
        //Then
        assertEquals(unrecognizedParkingTicketException.getMessage(), "Unrecognized parking ticket.");
    }

    @Test
    void should_print_error_message_when_fetch_car_given_used_ticket(){
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Ticket ticket = parkingLot.park(new Car());
        parkingLot.fetch(ticket);
        //When
        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLot.fetch(ticket));
        //Then
        assertEquals(unrecognizedParkingTicketException.getMessage(), "Unrecognized parking ticket.");
    }

    @Test
    void should_print_error_message_when_park_car_given_fulling_parking_lot(){
        //Given
        ParkingLot parkingLot = new ParkingLot();
        IntStream.range(0, 10).forEach(i -> {
            Car car = new Car();
            parkingLot.park(car);
        });
        //When
        NoAvailablePositionException noAvailablePositionException = assertThrows(NoAvailablePositionException.class, () -> parkingLot.park(new Car()));
        //Then
        assertEquals(noAvailablePositionException.getMessage(), "No available position.");
    }
}
