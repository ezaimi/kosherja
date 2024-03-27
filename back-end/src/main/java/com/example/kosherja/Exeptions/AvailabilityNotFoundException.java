package com.example.kosherja.Exeptions;

public class AvailabilityNotFoundException extends RuntimeException{
    public AvailabilityNotFoundException(String message){
        super(message);
    }
}
