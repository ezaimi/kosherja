package com.example.kosherja.Service.Meetings;

import com.example.kosherja.Model.Meetings.AvailabilityRequest;
import com.example.kosherja.Repo.Meetings.AvailableDtRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class AvailableDtService {
    @Autowired
    AvailableDtRepo availableDtRepo;
    private final Lock lock = new ReentrantLock();


    public AvailabilityRequest setAvailibility1(LocalDate date,String id){

        AvailabilityRequest request = new AvailabilityRequest();
        request.setDate(date);
        request.setId(id);
        request.setAvailable(false); // Set availability to true explicitly
        return availableDtRepo.save(request);}


    public AvailabilityRequest setAvailibility3(LocalDate date,String id){

            AvailabilityRequest request = new AvailabilityRequest();
            request.setDate(date);
            request.setId(id);
            request.setAvailable(true); // Set availability to true explicitly
            return availableDtRepo.save(request);}


    public AvailabilityRequest setAvailibility2(LocalDate date,String managerId){

         AvailabilityRequest request = new AvailabilityRequest();
            request.setDate(date);
            request.setManagerId(managerId);
            request.setAvailable(true); // Set availability to true explicitly
            return availableDtRepo.save(request);}





}
