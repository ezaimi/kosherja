package com.example.kosherja.Controller;


import com.example.kosherja.Model.Building;
import com.example.kosherja.Repo.BuildingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/create")
public class BuildingController {

    @Autowired
    private BuildingRepo buildingRepo;

    @PostMapping("/building")
    public ResponseEntity createBuilding(@RequestBody Building building){
        return ResponseEntity.status(201).body(
                buildingRepo.save(building)
        );

    }

}
