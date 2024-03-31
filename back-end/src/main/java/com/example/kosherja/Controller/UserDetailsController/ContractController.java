package com.example.kosherja.Controller.UserDetailsController;

import com.example.kosherja.Model.UserDetails.Contract;
import com.example.kosherja.Repo.UserDetailsRepo.ContractRepo;
import com.example.kosherja.Service.UserDetails.ContractService;
import com.example.kosherja.Service.UserService.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contract")
public class ContractController {

    @Autowired
    private ContractRepo contractRepo;

    @Autowired
    private ContractService contractService;

    @PostMapping("/create")
    public ResponseEntity createContract(@RequestBody Contract contract){
        return ResponseEntity.status(201).body(
                contractRepo.save(contract)
        );

    }


    //get contract details in student dashboard
    @GetMapping("details/{studentId}")
    public ResponseEntity<Contract> getContractDetails(@PathVariable("studentId") String studentId){
        Contract contract = contractService.showContractInfo(studentId);

        return new ResponseEntity<>(contract, HttpStatus.OK);
    }

}
