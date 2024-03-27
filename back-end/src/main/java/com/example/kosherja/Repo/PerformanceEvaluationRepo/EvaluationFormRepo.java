package com.example.kosherja.Repo.PerformanceEvaluationRepo;

import com.example.kosherja.Model.PerformanceEvaluation.EvaluationForm;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EvaluationFormRepo extends MongoRepository<EvaluationForm,String> {
}
