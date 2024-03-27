package com.example.kosherja.Exeptions;

public class AppointmentNotFoundException extends RuntimeException{
        public  AppointmentNotFoundException(String message){
            super(message);
        }
}
