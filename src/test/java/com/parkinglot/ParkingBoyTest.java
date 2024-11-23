package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {
//    @Test
//    void should_return_ticket_when_park_given_a_car_and_a_parking_boy(){
//        //Given
//        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
//        Car car = new Car();
//        //When
//        Ticket ticket = parkingBoy.park(car);
//        //Then
//        assertNotNull(ticket);
//    }
//
//    @Test
//    void should_return_the_car_when_fetch_given_a_ticket_and_a_parking_boy() {
//        //Given
//        ParkingLot parkingLot = new ParkingLot();
//        Car car = new Car();
//        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
//        Ticket ticket = parkingBoy.park(car);
//        //When
//        Car fetchedCar = parkingBoy.fetch(ticket);
//        //Then
//        assertEquals(fetchedCar, car);
//    }
//
//    @Test
//    void should_return_nothing_when_fetch_car_given_a_wrong_ticket_and_a_parking_boy() {
//        //Given
//        ParkingLot parkingLot = new ParkingLot();
//        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
//        Ticket ticket = new Ticket();
//        //When
//        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(ticket));
//        //Then
//        assertEquals(unrecognizedParkingTicketException.getMessage(), "Unrecognized parking ticket.");
//    }
//
//    @Test
//    void should_return_two_parked_cars_when_fetch_car_given_two_tickets_and_a_parking_boy() {
//        //Given
//        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
//        Car firstCar = new Car();
//        Car secondCar = new Car();
//        Ticket firstTicket = parkingBoy.park(firstCar);
//        Ticket secondticket = parkingBoy.park(secondCar);
//        //When
//        Car fetchedFirstCar = parkingBoy.fetch(firstTicket);
//        Car fetchedSecondCar = parkingBoy.fetch(secondticket);
//        //Then
//        assertEquals(fetchedFirstCar, firstCar);
//        assertEquals(fetchedSecondCar, secondCar);
//    }
//
//    @Test
//    void should_print_error_message_when_fetch_car_given_used_ticket_and_a_parking_boy(){
//        //Given
//        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
//        Ticket ticket = parkingBoy.park(new Car());
//        parkingBoy.fetch(ticket);
//        //When
//        UnrecognizedParkingTicketException unrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(ticket));
//        //Then
//        assertEquals(unrecognizedParkingTicketException.getMessage(), "Unrecognized parking ticket.");
//    }
//
//    @Test
//    void should_print_error_message_when_park_car_given_fulling_parking_lot_and_a_parking_boy(){
//        //Given
//        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
//        IntStream.range(0, 10).forEach(i -> {
//            Car car = new Car();
//            parkingBoy.park(car);
//        });
//        //When
//        NoAvailablePositionException noAvailablePositionException = assertThrows(NoAvailablePositionException.class, () -> parkingBoy.park(new Car()));
//        //Then
//        assertEquals(noAvailablePositionException.getMessage(), "No available position.");
//    }

    @Test
    void should_parking_first_lot_when_park_given_two_free_lot_and_a_parking_boy(){
        //Given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot());
        parkingLots.add(new ParkingLot());
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();
        //When
        Ticket ticket = parkingBoy.park(car);
        //Then
        ParkingLot carParkedLot = parkingBoy.getParkingLots().get(0);
        assertTrue(carParkedLot.getParkingRecords().containsKey(ticket));
    }

    @Test
    void should_parking_second_lot_when_park_given_first_full_second_available_and_a_parking_boy(){
        //Given
        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        ParkingLot firstLot = new ParkingLot();
        parkingLots.add(firstLot);
        ParkingLot secondLot = new ParkingLot();
        parkingLots.add(secondLot);
        IntStream.range(0, 10).forEach(i -> {
            Car car = new Car();
            parkingBoy.park(car);
        });
        Car car = new Car();
        //When
        Ticket ticket = parkingBoy.park(car);
        //Then
        ParkingLot carParkedLot = parkingBoy.getParkingLots().get(1);
        assertTrue(carParkedLot.getParkingRecords().containsKey(ticket));
    }

    @Test
    void should_return_two_cars_when_fetch_given_two_tickets_and_a_parking_boy(){
        //Given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot());
        parkingLots.add(new ParkingLot());
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
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
}
