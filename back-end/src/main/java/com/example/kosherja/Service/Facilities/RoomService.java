package com.example.kosherja.Service.Facilities;

import com.example.kosherja.Model.Facilities.Room;
import com.example.kosherja.Model.User.Student;
import com.example.kosherja.Repo.FacilitiesRepo.RoomRepo;
import com.example.kosherja.Repo.UserRepo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private RoomRepo roomRepo;

    //room details for a student
    public Room showRoomInfo(String studentId){
        Student student = studentRepo.findById(studentId)  .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));

        String roomId = student.getRoomId();


        return roomRepo.findById(roomId).orElse(null);
    }

}
