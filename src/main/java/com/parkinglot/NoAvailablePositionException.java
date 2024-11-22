package com.parkinglot;

public class NoAvailablePositionException extends RuntimeException{
    public NoAvailablePositionException(String errorMessage){
        super(errorMessage);
    }
}
