package com.example.kosherja.Controller.Meetings;

import com.example.kosherja.Exeptions.AppointmentNotFoundException;
import com.example.kosherja.Exeptions.AppointmentUnavailableException;
import com.example.kosherja.Model.Meetings.AvailabilityRequest;
import com.example.kosherja.Repo.Meetings.AvailableDtRepo;
import com.example.kosherja.Service.Meetings.AvailableDtService;
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

    @Autowired
    AvailableDtService availableDtService;
    @GetMapping()
    public ResponseEntity<Iterable<AvailabilityRequest>> viewDates(){
        Iterable<AvailabilityRequest> requests=availableDtRepo.findAll();
        return new ResponseEntity<>(requests,HttpStatus.OK);
    }
    @PutMapping("/book/{id}")
    public ResponseEntity<AvailabilityRequest> bookAppointment(@PathVariable("id") String id) {
        try {
            // Retrieve the existing availability by ID
            Optional<AvailabilityRequest> existingAvailabilityOptional = availableDtRepo.findById(id);

            if (!existingAvailabilityOptional.isPresent()) {
                // Return 404 Not Found if availability with the specified ID is not found
                return ResponseEntity.notFound().build();
            }

            // Get the existing availability object
            AvailabilityRequest existingAvailability = existingAvailabilityOptional.get();

            // Set the availability to false
            existingAvailability.setAvailable(false);

            // Save the updated availability
            AvailabilityRequest updatedAvailability = availableDtRepo.save(existingAvailability);

            // Return the updated availability as a response
            return ResponseEntity.ok(updatedAvailability);
        } catch (Exception e) {
            // Handle any other exceptions and return an appropriate response
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PutMapping("/cancel/{id}")
    public ResponseEntity<AvailabilityRequest> cancelAppointment(@PathVariable("id") String id) {
        try {
            // Retrieve the existing availability by ID
            Optional<AvailabilityRequest> existingAvailabilityOptional = availableDtRepo.findById(id);

            if (!existingAvailabilityOptional.isPresent()) {
                // Return 404 Not Found if availability with the specified ID is not found
                return ResponseEntity.notFound().build();
            }

            // Get the existing availability object
            AvailabilityRequest existingAvailability = existingAvailabilityOptional.get();

            // Set the availability to false (this should be set to true to cancel)
            existingAvailability.setAvailable(true);

            // Save the updated availability
            AvailabilityRequest updatedAvailability = availableDtRepo.save(existingAvailability);

            // Return the updated availability as a response
            return ResponseEntity.ok(updatedAvailability);
        } catch (Exception e) {
            // Handle any other exceptions and return an appropriate response
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
