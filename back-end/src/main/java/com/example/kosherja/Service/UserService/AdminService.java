package com.example.kosherja.Service.UserService;

import com.example.kosherja.Model.Facilities.Building;
import com.example.kosherja.Model.User.Manager;
import com.example.kosherja.Model.User.ManagerDTO;
import com.example.kosherja.Repo.FacilitiesRepo.BuildingRepo;
import com.example.kosherja.Repo.UserRepo.ManagerRepo;
import com.example.kosherja.Repo.UserRepo.StudentRepo;
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
