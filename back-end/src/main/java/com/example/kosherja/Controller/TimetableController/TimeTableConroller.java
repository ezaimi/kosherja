package com.example.kosherja.Controller.TimetableController;

import com.example.kosherja.Model.Timetable.Routes;
import com.example.kosherja.Model.Timetable.TimeTable;
import com.example.kosherja.Repo.TimeTableRepo.MainTimeTable;
import com.example.kosherja.Service.TimeTableService.RouteService;
import com.example.kosherja.Service.TimeTableService.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/timetable")
public class TimeTableConroller {

    @Autowired
    RouteService routeService;

    @Autowired
    MainTimeTable mainTimeTable;
    @GetMapping("{id}/all")
    public ResponseEntity<TimeTable> getAllRoutes(@PathVariable String id) {
        List<Routes> allRoutes = routeService.getAllRoutes();
        TimeTable timeTable=new TimeTable();
        timeTable.setRoutes(allRoutes);
        timeTable.setMagersId(id);
        mainTimeTable.save(timeTable);
        return new ResponseEntity<>(timeTable, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeTable> getTimetable(@PathVariable String id){
        return new ResponseEntity<>(mainTimeTable.findById(id).orElse(null),HttpStatus.OK);
    }

}
