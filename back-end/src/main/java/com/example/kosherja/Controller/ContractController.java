package com.example.kosherja.Controller;

import com.example.kosherja.Model.Contract;
import com.example.kosherja.Repo.ContractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contract")
public class ContractController {

    @Autowired
    private ContractRepo contractRepo;

    @PostMapping("/create")
    public ResponseEntity createContract(@RequestBody Contract contract){
        return ResponseEntity.status(201).body(
                contractRepo.save(contract)
        );

    }

}
