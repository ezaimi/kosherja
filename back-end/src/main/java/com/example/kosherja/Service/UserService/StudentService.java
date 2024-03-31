package com.example.kosherja.Service.UserService;

import com.example.kosherja.Model.Facilities.Building;
import com.example.kosherja.Model.Facilities.Room;
import com.example.kosherja.Model.User.Student;
import com.example.kosherja.Model.UserDetails.Contract;
import com.example.kosherja.Repo.FacilitiesRepo.BuildingRepo;
import com.example.kosherja.Repo.FacilitiesRepo.RoomRepo;
import com.example.kosherja.Repo.UserDetailsRepo.ContractRepo;
import com.example.kosherja.Repo.UserRepo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {


    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private BuildingRepo buildingRepo;

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private ContractRepo contractRepo;

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



    //method to edit a student
    public Student editStudent(String studentId, Student editedStudent) {
        // Find the student by ID
        Student existingStudent = studentRepo.findById(studentId).orElse(null);
        if (existingStudent == null) {
            return null; // Student not found
        }

        // Update the attributes of the existing student with the edited student's attributes
        existingStudent.setUsername(editedStudent.getUsername());
        existingStudent.setPassword(editedStudent.getPassword());
        existingStudent.setName(editedStudent.getName());
        existingStudent.setSurname(editedStudent.getSurname());
        existingStudent.setEmail(editedStudent.getEmail());
        existingStudent.setPhone(editedStudent.getPhone());
        existingStudent.setRoomId(editedStudent.getRoomId());
        existingStudent.setContractId(editedStudent.getContractId());
        existingStudent.setManagerId(editedStudent.getManagerId());
        existingStudent.setDocumentList(editedStudent.getDocumentList());
        // Update other attributes as needed

        // Save the updated student
        return studentRepo.save(existingStudent);
    }



}

