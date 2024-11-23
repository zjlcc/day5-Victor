package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SuperSmartParkingBoyTest {
    @Test
    void should_in_second_lot_when_park_car_given_second_lot_position_rate_smaller_and_a_super_smart_parking_boy(){
        //Given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot());
        parkingLots.add(new ParkingLot());
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        superSmartParkingBoy.park(new Car());
        //When
        Ticket ticket = superSmartParkingBoy.park(new Car());
        //Then
        ParkingLot parkingLot = superSmartParkingBoy.getParkingLots().get(1);
        assertTrue(parkingLot.getParkingRecords().containsKey(ticket));
    }

    @Test
    void should_in_first_lot_when_park_car_given_two_empty_lots_and_a_smart_parking_boy(){
        //Given
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot());
        parkingLots.add(new ParkingLot());
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        //When
        Ticket ticket = superSmartParkingBoy.park(new Car());
        //Then
        ParkingLot parkingLot = superSmartParkingBoy.getParkingLots().get(0);
        assertTrue(parkingLot.getParkingRecords().containsKey(ticket));
    }
}
