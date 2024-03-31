package com.example.kosherja.Controller.Meetings;

import com.example.kosherja.Model.Meetings.AvailabilityRequest;
import com.example.kosherja.Service.Meetings.AvailableDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/availableDt")
public class AvailableDtController {
    @Autowired
    AvailableDtService availableDtService;

    @PostMapping("/{managerId}")
    public ResponseEntity<AvailabilityRequest> setAvailable(@PathVariable ("managerId") String managerId, @RequestBody AvailabilityRequest requestdate){
//        System.out.println("ardddddddd"+managerId);
        AvailabilityRequest request=availableDtService.setAvailibility2(requestdate.getDate(),managerId);
        System.out.println(requestdate);
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }
//    Krijo nje metod qe merr vetem te gjith dates ne nje list na nje manager i caktuar--

    @GetMapping("/getAllDt")
    public ResponseEntity<List<AvailabilityRequest>> getAllDates(){
        return new ResponseEntity<>(availableDtService.findAllAvailableDates(),HttpStatus.OK);
    }
}
