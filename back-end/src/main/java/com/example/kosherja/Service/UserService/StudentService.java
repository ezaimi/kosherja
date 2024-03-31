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

    public boolean existsByUsernameOrEmail(String username, String email) {
        return studentRepo.existsByUsernameOrEmail(username, email);
    }


    //  to get manager ID by student ID
    public String getManagerIdByStudentId(String studentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));

        // Get the manager ID from the student object

        // Return the manager ID
        return student.getManagerId();

    }


    public Student createStudent(String managerId, Student student, String roomId, String contractId) {

        Room room = roomRepo.findById(roomId).orElse(null);
        if (room == null) {
            return null;
        }

        String buildingId = room.getBuildingId();

        Building building = buildingRepo.findById(buildingId).orElse(null);
        if (building == null) {
            return null; // Building not found
        }


        //  if there are available rooms of the same type
        int availableRooms = building.getNoOfRooms() - (int) building.getOccupancyStatus();
        System.out.println("Available Rooms: " + availableRooms);
        if (availableRooms <= 0) {
            return null;
        }

//        student.setLastPaymentDate(null);
//        student.setNextPaymentDate(null);

        student.setManagerId(managerId);
        student.setRoomId(roomId);
        student.setContractId(contractId);

        studentRepo.save(student);

        // updating the occupancy status of the building
        building.setOccupancyStatus(building.getOccupancyStatus() + 1);
        buildingRepo.save(building);

        return studentRepo.save(student);

    }




}

