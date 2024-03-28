package com.example.kosherja.Service.Meetings;

import com.example.kosherja.Model.Meetings.AvailabilityRequest;
import com.example.kosherja.Repo.Meetings.AvailableDtRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class AvailableDtService {
    @Autowired
    AvailableDtRepo availableDtRepo;
    private final Lock lock = new ReentrantLock();


//    public AvailabilityRequest setAvailibility(LocalDate date){
//        AvailabilityRequest request = new AvailabilityRequest();
//        request.setDate(date);
//        request.setAvailable(true); // Set availability to true explicitly
//        return availableDtRepo.save(request);
//    }

    public AvailabilityRequest setAvailibility(LocalDate date) {
        lock.lock(); // Acquire the lock
        try {
            AvailabilityRequest request = new AvailabilityRequest();
            request.setDate(date);
            request.setAvailable(true); // Set availability to true explicitly
            return availableDtRepo.save(request);
        } finally {
            lock.unlock(); // Release the lock in the finally block to ensure it's always released
        }
    }
}
