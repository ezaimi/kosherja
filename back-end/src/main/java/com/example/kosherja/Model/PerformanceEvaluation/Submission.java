package com.example.kosherja.Model.PerformanceEvaluation;

import com.example.kosherja.Model.User.Manager;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "submissions")
public class Submission {

    @Id
    private String id;
    //private String evaluationFormId; // ID of the evaluation form
    private List<Answer> answers;

    private String managerId;

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }
}
