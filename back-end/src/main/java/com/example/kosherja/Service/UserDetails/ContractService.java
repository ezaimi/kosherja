package com.example.kosherja.Service.UserDetails;

import com.example.kosherja.Model.User.Student;
import com.example.kosherja.Model.UserDetails.Contract;
import com.example.kosherja.Repo.UserDetailsRepo.ContractRepo;
import com.example.kosherja.Repo.UserRepo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractService {


    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private ContractRepo contractRepo;

    public Contract showContractInfo(String studentId){
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));

        String contractId = student.getContractId();

        return contractRepo.findById(contractId).orElse(null);
    }

}
