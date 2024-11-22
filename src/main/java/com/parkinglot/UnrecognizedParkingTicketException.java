package com.parkinglot;

public class UnrecognizedParkingTicketException extends RuntimeException{

    public UnrecognizedParkingTicketException(String errorMessage){
        super(errorMessage);
    }
}
