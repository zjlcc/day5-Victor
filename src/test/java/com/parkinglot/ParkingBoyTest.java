package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingBoyTest {
    @Test
    void should_return_ticket_when_park_given_a_car_and_a_parking_boy(){
        //Given
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        Car car = new Car();
        //When
        Ticket ticket = parkingBoy.park(car);
        //Then
        assertNotNull(ticket);
    }

}
