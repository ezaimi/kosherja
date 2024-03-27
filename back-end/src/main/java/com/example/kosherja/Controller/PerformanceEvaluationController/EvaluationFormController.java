package com.example.kosherja.Controller.PerformanceEvaluationController;

import com.example.kosherja.Model.PerformanceEvaluation.EvaluationForm;
import com.example.kosherja.Model.PerformanceEvaluation.Question;
import com.example.kosherja.Repo.PerformanceEvaluationRepo.EvaluationFormRepo;
import com.example.kosherja.Service.PerformanceEvaluation.EvaluationFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/performance")
public class EvaluationFormController {

    @Autowired
    private EvaluationFormRepo evaluationFormRepo;

    @Autowired
    private EvaluationFormService evaluationFormService;

    @PostMapping("/postForm")
    public ResponseEntity createPerformanceForm(@RequestBody EvaluationForm evaluationForm){
        return ResponseEntity.status(201).body(
                evaluationFormRepo.save(evaluationForm)
        );
    }

    //edit a question
    @PutMapping("/question/{formId}/{questionIndex}")
    public ResponseEntity<String> editQuestion(
            @PathVariable String formId,
            @PathVariable int questionIndex,
            @RequestBody Question updatedQuestion
    ) {
        try {
            // call the service method to update the question
            evaluationFormService.editQuestion(formId, questionIndex, updatedQuestion);
            return ResponseEntity.ok("Question updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating question: " + e.getMessage());
        }
    }

    //add a new question
    @PostMapping("/{evaluationFormId}/question")
    public ResponseEntity<EvaluationForm> addQuestionToEvaluationForm(
            @PathVariable String evaluationFormId,
            @RequestBody Question newQuestion) {

        EvaluationForm updatedForm = evaluationFormService.addQuestion(evaluationFormId, newQuestion);
        return new ResponseEntity<>(updatedForm, HttpStatus.CREATED);
    }


    //delete a question by index
    @DeleteMapping("/{formId}/delete/{index}")
    public ResponseEntity<String> deleteQuestion(@PathVariable("formId") String formId, @PathVariable("index") int index) {
        try {
            evaluationFormService.deleteQuestionByIndex(formId, index);
            return ResponseEntity.ok("Question at index " + index + " deleted successfully");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting question: " + e.getMessage());
        }
    }



}
