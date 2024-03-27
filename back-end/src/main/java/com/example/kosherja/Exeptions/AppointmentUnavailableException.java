package com.example.kosherja.Exeptions;

public class AppointmentUnavailableException extends RuntimeException{
    public  AppointmentUnavailableException(String message){
        super(message);
    }
}
