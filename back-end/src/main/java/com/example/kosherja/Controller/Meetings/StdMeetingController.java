package com.example.kosherja.Controller.Meetings;

import com.example.kosherja.Exeptions.AppointmentNotFoundException;
import com.example.kosherja.Exeptions.AppointmentUnavailableException;
import com.example.kosherja.Model.Meetings.AvailabilityRequest;
import com.example.kosherja.Repo.Meetings.AvailableDtRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/checkDatess") // Make sure the base path is correct

public class StdMeetingController {

    @Autowired
    AvailableDtRepo availableDtRepo;
    @GetMapping()
    public ResponseEntity<Iterable<AvailabilityRequest>> viewDates(){
        Iterable<AvailabilityRequest> requests=availableDtRepo.findAll();
        return new ResponseEntity<>(requests,HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<AvailabilityRequest> bookAppointment(@PathVariable("id") String id, @RequestBody AvailabilityRequest appointmentRequest) {
        try {
            // Find the specific availability by ID
            Optional<AvailabilityRequest> existingAvailabilityOptional = availableDtRepo.findById(id);

            // Check if the availability exists
            if (!existingAvailabilityOptional.isPresent()) {
                throw new AppointmentNotFoundException("Availability with ID " + id + " not found.");
            }

            // Get the existing availability object
            AvailabilityRequest existingAvailability = existingAvailabilityOptional.get();

            // Check if the availability is already booked
            if (!existingAvailability.isAvailable()) {
                throw new AppointmentUnavailableException("The selected date is not available for booking.");
            }

            // Update the availability status to false (booked)
            existingAvailability.setAvailable(false);
            availableDtRepo.save(existingAvailability);

            return ResponseEntity.ok(existingAvailability);
        } catch (AppointmentNotFoundException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (AppointmentUnavailableException e) {
            System.out.println(e);

            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }


}
