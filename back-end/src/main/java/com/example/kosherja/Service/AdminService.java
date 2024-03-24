package com.example.kosherja.Service;

import com.example.kosherja.Model.Building;
import com.example.kosherja.Model.Manager;
import com.example.kosherja.Model.ManagerDTO;
import com.example.kosherja.Repo.BuildingRepo;
import com.example.kosherja.Repo.ManagerRepo;
import com.example.kosherja.Repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private ManagerRepo managerRepo;

    @Autowired
    private BuildingRepo buildingRepo;

    @Autowired
    private StudentRepo studentRepo;

    public List<ManagerDTO> getAllCoordinatorsDetails(){
        List<ManagerDTO> coordinatorDataList = new ArrayList<>();
        List<Manager> managers = managerRepo.findAll();

        for(Manager manager : managers){
            String buildingId = manager.getBuildingID();
            Building building = buildingRepo.findById(buildingId).orElse(null);

            long numberOfStudents = studentRepo.countByManagerId(manager.getId());
            ManagerDTO coordinatorData = new ManagerDTO(manager, building, numberOfStudents);

            coordinatorDataList.add(coordinatorData);

        }
        return coordinatorDataList;
    }


}
