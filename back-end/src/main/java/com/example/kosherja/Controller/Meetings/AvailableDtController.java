package com.example.kosherja.Controller.Meetings;

import com.example.kosherja.Model.Meetings.AvailabilityRequest;
import com.example.kosherja.Service.Meetings.AvailableDtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("api/availableDt")
public class AvailableDtController {
    @Autowired
    AvailableDtService availableDtService;

    @PostMapping()
    public ResponseEntity<AvailabilityRequest> setAvailable(@RequestBody AvailabilityRequest requestdate){
        AvailabilityRequest request=availableDtService.setAvailibility(requestdate.getDate());
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }
}
