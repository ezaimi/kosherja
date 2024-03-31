package com.example.kosherja.Controller.FacilitiesController;

import com.example.kosherja.Model.Facilities.Reservation;
import com.example.kosherja.Model.Facilities.Room;
import com.example.kosherja.Repo.FacilitiesRepo.RoomRepo;
import com.example.kosherja.Service.Facilities.RoomService;
import com.example.kosherja.Service.UserService.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private RoomService roomService;

    @PostMapping("/create")
    public ResponseEntity createRoom(@RequestBody Room room){
        return ResponseEntity.status(201).body(
                roomRepo.save(room)
        );
    }


    //get room details in std dashboard
    @GetMapping("details/{studentId}")
    public ResponseEntity<Room> getRoomDetails(@PathVariable("studentId") String studentId){
        Room room = roomService.showRoomInfo(studentId);

        return new ResponseEntity<>(room, HttpStatus.OK);
    }


}
