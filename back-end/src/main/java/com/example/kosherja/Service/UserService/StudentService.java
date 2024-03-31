package com.example.kosherja.Service.UserService;

import com.example.kosherja.Model.Facilities.Building;
import com.example.kosherja.Model.Facilities.Room;
import com.example.kosherja.Model.User.Student;
import com.example.kosherja.Repo.FacilitiesRepo.BuildingRepo;
import com.example.kosherja.Repo.FacilitiesRepo.RoomRepo;
import com.example.kosherja.Repo.UserRepo.StudentRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StudentService {


    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private BuildingRepo buildingRepo;

    @Autowired
    private RoomRepo roomRepo;


    // Method to get manager ID by student ID
    public String getManagerIdByStudentId(String studentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));

        // Get the manager ID from the student object

        // Return the manager ID
        return student.getManagerId();

    }


    public void createStudent(String managerId, Student student, String roomId, String contractId) {
        // Check if the selected room is available in the building
        Building building = buildingRepo.findById(student.getBuildingId()).orElse(null);
        if (building == null) {
            return;
        }

        // Find the room in the building
        Room room = (Room) roomRepo.findByIdAndBuildingId(roomId, building.getId()).orElse(null);
        if (room == null) {
            return;
        }


        // Check if there are available rooms of the same type
        int availableRooms = building.getNoOfRooms() - (int) building.getOccupancyStatus();
        if (availableRooms <= 0) {
            return;
        }

        student.setLastPaymentDate(null);
        student.setNextPaymentDate(null);

        // Set the manager ID and save the student
        student.setManagerId(managerId);
        student.setRoomId(roomId);
        student.setContractId(contractId);

        studentRepo.save(student);

        // Update the occupancy status of the building
        building.setOccupancyStatus(building.getOccupancyStatus() + 1);
        buildingRepo.save(building);

    }




}

