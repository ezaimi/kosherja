package com.example.kosherja.Controller.PerformanceEvaluationController;


import com.example.kosherja.Model.PerformanceEvaluation.Submission;
import com.example.kosherja.Model.User.ManagerDTO;
import com.example.kosherja.Repo.PerformanceEvaluationRepo.SubmissionsRepo;
import com.example.kosherja.Service.PerformanceEvaluation.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/performance/submission")
public class SubmissionController {


    @Autowired
    private SubmissionService submissionService;

    @PostMapping("/submitAnswers/{studentId}")
    public ResponseEntity<String> submitEvaluation(@PathVariable String studentId, @RequestBody Submission submission){
        try{
            submissionService.submitEvaluation(studentId,submission);
            return ResponseEntity.ok("Evaluation submitted successfully");

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error submitting evaluation" + e.getMessage());
        }
    }


    //shows the remaining students that have not submitted their answers yet
    @GetMapping("/remaining-submissions")
    public Map<String, Integer> getRemainingSubmissionsForManagers() {
        return submissionService.checkRemainingSubmissions();
    }
}
