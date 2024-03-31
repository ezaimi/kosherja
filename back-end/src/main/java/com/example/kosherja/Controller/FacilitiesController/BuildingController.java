package com.example.kosherja.Controller.FacilitiesController;


import com.example.kosherja.Model.Facilities.Building;
import com.example.kosherja.Model.Facilities.BuildingRoomDTO;
import com.example.kosherja.Repo.FacilitiesRepo.BuildingRepo;
import com.example.kosherja.Service.Facilities.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/building")
public class BuildingController {

    @Autowired
    private BuildingRepo buildingRepo;

    @Autowired
    private BuildingService buildingService;

    @PostMapping("/create")
    public ResponseEntity createBuilding(@RequestBody Building building){
        return ResponseEntity.status(201).body(
                buildingRepo.save(building)
        );

    }

    @GetMapping("/checkRoomAvailability")
    public ResponseEntity<List<BuildingRoomDTO>> getRoomInfoPerBuilding(){
        List<BuildingRoomDTO> buildingRoomDTOS = buildingService.getBuildingRooms();
        return new ResponseEntity<>(buildingRoomDTOS, HttpStatus.OK);
    }

}
