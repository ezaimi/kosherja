package com.example.kosherja.Service.PerformanceEvaluation;

import com.example.kosherja.Model.PerformanceEvaluation.Answer;
import com.example.kosherja.Model.PerformanceEvaluation.Submission;
import com.example.kosherja.Model.User.Manager;
import com.example.kosherja.Repo.UserRepo.ManagerRepo;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Service
public class BonusAllocationService {

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private ManagerRepo managerRepo;

    public Map<String, Integer> totalScoresForEachManager() {
        Map<String, Integer> totalScores = new HashMap<>();

        // checking remaining submissions for each manager
        Map<String, Integer> remainingSubmissions = submissionService.checkRemainingSubmissions();


        for (Map.Entry<String, Integer> entry : remainingSubmissions.entrySet()) {
            String managerId = entry.getKey();
            int remaining = entry.getValue();

            // if all submissions have been made
            if (remaining == 0) {
                // get submissions for this manager
                List<Submission> submissions = submissionService.getSubmissionsByManagerId(managerId);

                int totalScore = calculateTotalScore(submissions);
                totalScores.put(managerId, totalScore);
            }
        }

        return totalScores;
    }

    private int calculateTotalScore(List<Submission> submissions) {
        int totalScore = 0;
        for (Submission submission : submissions) {
            for (Answer answer : submission.getAnswers()) {
                if (answer.getAnswer() instanceof Integer) {
                    totalScore += (int) answer.getAnswer();
                }
            }
        }
        return totalScore;
    }


    public Map<String, Object> managerWithHighestScore() {
        Map<String, Integer> totalScores = totalScoresForEachManager();

        if (totalScores.isEmpty()) {
            // No scores available
            return Collections.singletonMap("message", "No scores available.");
        }

        // Find manager with the highest score
        String managerWithHighestScore = Collections.max(totalScores.entrySet(), Map.Entry.comparingByValue()).getKey();
        int highestScore = totalScores.get(managerWithHighestScore);

        // Find manager name by ID
        Manager manager = managerRepo.findById(managerWithHighestScore).orElse(null);
        String managerName = (manager != null) ? manager.getName() : "Unknown";
        String managerSurname = (manager != null) ? manager.getSurname() : "Unknown";

        // Prepare response
        Map<String, Object> response = new HashMap<>();
        response.put("Manager", managerName + " " + managerSurname);
        response.put("Total Score", highestScore);

        return response;
    }

}
