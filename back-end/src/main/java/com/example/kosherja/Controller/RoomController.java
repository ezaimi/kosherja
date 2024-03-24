package com.example.kosherja.Controller;

import com.example.kosherja.Model.Room;
import com.example.kosherja.Repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    private RoomRepo roomRepo;

    @PostMapping("/create")
    public ResponseEntity createRoom(@RequestBody Room room){
        return ResponseEntity.status(201).body(
                roomRepo.save(room)
        );
    }
}
