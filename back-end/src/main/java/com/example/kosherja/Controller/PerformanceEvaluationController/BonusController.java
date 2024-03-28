package com.example.kosherja.Controller.PerformanceEvaluationController;


import com.example.kosherja.Service.PerformanceEvaluation.BonusAllocationService;
import com.example.kosherja.Service.PerformanceEvaluation.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/bonus")
public class BonusController {

    @Autowired
    private BonusAllocationService bonusAllocationService;

    @Autowired
    private SubmissionService submissionService;

    @GetMapping
    public Map<String, Integer> showTotalScorePerManager(){
        return bonusAllocationService.totalScoresForEachManager();
    }

    @GetMapping("/checkAndCalculate")
    public Map<String,Integer > checkAndCalculate() {
        Map<String, Integer> remainingSubmissions = submissionService.checkRemainingSubmissions();

        // checking if there are remaining submissions for any manager
        boolean allSubmissionsReceived = remainingSubmissions.values().stream().allMatch(count -> count == 0);

        if (allSubmissionsReceived) {
            // all submissions received, proceed to calculate total score
            return bonusAllocationService.totalScoresForEachManager();
        } else {
            // not all submissions received
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not all submissions received.");

//            Map<String, Object> response = new HashMap<>();
//            response.put("message", "Not all submissions received.");
//            return response;
        }
    }


    //manager with the highest score
    @GetMapping("/highestScore")
    public Map<String,Object> showHighestScoreManager(){
        return bonusAllocationService.managerWithHighestScore();
    }

}
