package com.example.kosherja.Model;

import lombok.Data;

@Data
public class ManagerDTO {
    private Manager manager;
    private Building building;
    private long numberOfStudents;

    public ManagerDTO(Manager manager, Building building, long numberOfStudents) {
        this.manager = manager;
        this.building = building;
        this.numberOfStudents = numberOfStudents;
    }
}
