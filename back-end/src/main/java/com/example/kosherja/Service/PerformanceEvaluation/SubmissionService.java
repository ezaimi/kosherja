package com.example.kosherja.Service.PerformanceEvaluation;

import com.example.kosherja.Model.PerformanceEvaluation.Answer;
import com.example.kosherja.Model.PerformanceEvaluation.Submission;
import com.example.kosherja.Model.User.Manager;
import com.example.kosherja.Model.User.Student;
import com.example.kosherja.Repo.PerformanceEvaluationRepo.EvaluationFormRepo;
import com.example.kosherja.Repo.PerformanceEvaluationRepo.SubmissionsRepo;
import com.example.kosherja.Repo.UserRepo.ManagerRepo;
import com.example.kosherja.Repo.UserRepo.StudentRepo;
import com.example.kosherja.Service.UserService.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.HashMap;
import java.util.Map;


@Service
public class SubmissionService {



    @Autowired
    private SubmissionsRepo submissionsRepo;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ManagerRepo managerRepo;

    @Autowired
    private StudentRepo studentRepo;

    public void submitEvaluation(String studentId, Submission submission){
        //generating a unique identifier for the submission bc it is anonymous
       // String submissionId = UUID.randomUUID().toString();

        String managerId = studentService.getManagerIdByStudentId(studentId);

        // set the manager ID in the submission object
        submission.setManagerId(managerId);

        // saving the submission in the database
        submissionsRepo.save(submission);

    }



    //check how many students haven't submitted their answers yet
    public Map<String, Integer> checkRemainingSubmissions() {
        Map<String, Integer> remainingSubmissions = new HashMap<>();

        // Iterate over all managers
        List<Manager> allManagers = managerRepo.findAll();
        for (Manager manager : allManagers) {
            int totalStudents = (int) studentRepo.countByManagerId(manager.getId()); // get the total number of students managed by this manager

            // Retrieve the number of submissions for this manager
            long submittedSubmissions = submissionsRepo.countByManagerId(manager.getId());

            // Calculate the remaining submissions
            int remaining = (int) (totalStudents - submittedSubmissions);

            remainingSubmissions.put(manager.getId(), remaining);
        }

        return remainingSubmissions;
    }


    public List<Submission> getSubmissionsByManagerId(String managerId) {
        return submissionsRepo.findByManagerId(managerId);
    }

}
