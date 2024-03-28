package com.example.kosherja.Service.UserService;

import com.example.kosherja.Model.User.Manager;
import com.example.kosherja.Model.User.Student;
import com.example.kosherja.Repo.UserRepo.ManagerRepo;
import com.example.kosherja.Repo.UserRepo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private ManagerRepo managerRepo;

    @Autowired
    private StudentRepo studentRepo; // Assuming you have a StudentRepo

    // Method to get manager ID by student ID
    public String getManagerIdByStudentId(String studentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));

        // Get the manager ID from the student object

        // Return the manager ID
        return student.getManagerId();

    }

}

