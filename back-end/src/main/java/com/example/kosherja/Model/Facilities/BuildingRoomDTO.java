package com.example.kosherja.Model.Facilities;


import lombok.Data;

import java.util.List;

@Data
public class BuildingRoomDTO {

    private Building building;
    private List<Room> rooms;

    public BuildingRoomDTO(Building building, List<Room> rooms){
        this.building = building;
        this.rooms = rooms;
    }
}
