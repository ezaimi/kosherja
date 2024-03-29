package com.example.kosherja.Controller.TimetableController;

import com.example.kosherja.Model.Timetable.Routes;
import com.example.kosherja.Service.TimeTableService.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/routes")
public class RouteController {
    @Autowired
    RouteService routeService;
    @PostMapping("/create/")
    public ResponseEntity<Routes>create(@RequestBody Routes routes){
       return new ResponseEntity<>(routeService.createRoute(routes), HttpStatus.CREATED) ;

    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Routes>editRoutes(@PathVariable String id,@RequestBody Routes routes){
        return new ResponseEntity<>(routeService.editRoute(routes,id),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void>deleteRoute(@PathVariable String id){
        routeService.deleteRoute(id);

        return  ResponseEntity.noContent().build();
    }

}
