package com.example.kosherja.Model.PerformanceEvaluation;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "evaluation")
public class EvaluationForm {

    @Id
    private String id;

    private String title;

    private String description;

    private List<Question> questions;

}
