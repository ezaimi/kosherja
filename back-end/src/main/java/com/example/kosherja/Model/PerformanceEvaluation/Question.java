package com.example.kosherja.Model.PerformanceEvaluation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Question {

    private String text;

    private QuestionType type;

    private List<String> options; //  used for multiple choice or dropdown questions

    @JsonCreator
    public Question(@JsonProperty("text") String text,
                    @JsonProperty("type") QuestionType type,
                    @JsonProperty("options") List<String> options) {
        this.text = text;
        this.type = type;
        this.options = options;
    }

}