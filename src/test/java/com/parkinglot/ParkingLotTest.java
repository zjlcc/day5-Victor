package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    void should_return_ticket_when_park_given_a_car(){
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        //When
        Ticket ticket = parkingLot.park(car);
        //Then
        assertNotNull(ticket);
    }

    @Test
    void should_return_the_car_when_fetch_given_a_ticket(){
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
    void should_return_nothing_when_fetch_car_given_a_wrong_ticket(){
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Ticket ticket = new Ticket();
        //When
        Car fetchedCar = parkingLot.fetch(ticket);
        //Then
        assertNull(fetchedCar);
    }


}
