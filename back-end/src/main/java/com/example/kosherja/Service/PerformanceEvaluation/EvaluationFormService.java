package com.example.kosherja.Service.PerformanceEvaluation;

import com.example.kosherja.Model.PerformanceEvaluation.EvaluationForm;
import com.example.kosherja.Model.PerformanceEvaluation.Question;
import com.example.kosherja.Repo.PerformanceEvaluationRepo.EvaluationFormRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluationFormService {

    @Autowired
    private EvaluationFormRepo evaluationFormRepo;


    //to edit a question in the evaluation form
    public void editQuestion(String formId, int questionIndex, Question updatedQuestion) {
        // retrieve the evaluation form by ID
        Optional<EvaluationForm> optionalForm = evaluationFormRepo.findById(formId);
        if (optionalForm.isPresent()) {
            EvaluationForm evaluationForm = optionalForm.get();
            // Check if the questionIndex is within the bounds of the questions list
            if (questionIndex >= 0 && questionIndex < evaluationForm.getQuestions().size()) {
                // Update the question at the specified index with the updatedQuestion
                evaluationForm.getQuestions().set(questionIndex, updatedQuestion);
                // Save the updated evaluation form
                evaluationFormRepo.save(evaluationForm);
            } else {
                throw new IllegalArgumentException("Invalid question index");
            }
        } else {
            throw new IllegalArgumentException("Evaluation form not found");
        }
    }

    public EvaluationForm addQuestion(String evaluationFormId, Question newQuestion) {
        EvaluationForm evaluationForm = evaluationFormRepo.findById(evaluationFormId)
                .orElseThrow(() -> new RuntimeException("Evaluation form not found with id: " + evaluationFormId));

        List<Question> questions = evaluationForm.getQuestions();
        questions.add(newQuestion);
        evaluationForm.setQuestions(questions);

        return evaluationFormRepo.save(evaluationForm);
    }


    public void deleteQuestionByIndex(String formId, int index) {
        Optional<EvaluationForm> optionalForm = evaluationFormRepo.findById(formId);
        if (optionalForm.isPresent()) {
            EvaluationForm evaluationForm = optionalForm.get();
            if (index >= 0 && index < evaluationForm.getQuestions().size()) {

                evaluationForm.getQuestions().remove(index);
                // saving the updated evaluation form
                evaluationFormRepo.save(evaluationForm);
            } else {
                throw new IllegalArgumentException("Invalid question index");
            }
        } else {
            throw new IllegalArgumentException("Evaluation form not found");
        }
    }

}
