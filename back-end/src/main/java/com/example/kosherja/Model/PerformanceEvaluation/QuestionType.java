package com.example.kosherja.Model.PerformanceEvaluation;


import lombok.Getter;

@Getter
public enum QuestionType {
    TEXT("Text"),
    MULTIPLE_CHOICE("Multiple Choice"),
    DROPDOWN("Dropdown"),
    RATING_SCALE("Rating Scale");

    private final String displayName;

    QuestionType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
