package com.example.kosherja.Repo.PerformanceEvaluationRepo;

import com.example.kosherja.Model.PerformanceEvaluation.Submission;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SubmissionsRepo extends MongoRepository<Submission, String> {
    long countByManagerId(String managerId);
    List<Submission> findByManagerId(String managerId);

}
