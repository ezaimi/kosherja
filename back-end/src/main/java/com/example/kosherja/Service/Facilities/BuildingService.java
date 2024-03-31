package com.example.kosherja.Service.Facilities;

import com.example.kosherja.Model.Facilities.Building;
import com.example.kosherja.Model.Facilities.BuildingRoomDTO;
import com.example.kosherja.Model.Facilities.Room;
import com.example.kosherja.Repo.FacilitiesRepo.BuildingRepo;
import com.example.kosherja.Repo.FacilitiesRepo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingService {

    @Autowired
    private BuildingRepo buildingRepo;

    @Autowired
    private RoomRepo roomRepo;

    public List<BuildingRoomDTO> getBuildingRooms(){
        List<BuildingRoomDTO> buildingRooms = new ArrayList<>();

        List<Building> buildings = buildingRepo.findAll();

        for(Building building : buildings){

            List<Room> rooms = roomRepo.findByBuildingId(building.getId());
            BuildingRoomDTO buildingRoomDTO = new BuildingRoomDTO(building,rooms);

            buildingRooms.add(buildingRoomDTO);
        }

        return buildingRooms;
    }
}
