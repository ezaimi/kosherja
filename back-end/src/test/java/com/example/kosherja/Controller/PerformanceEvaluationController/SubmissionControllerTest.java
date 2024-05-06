package com.example.kosherja.Controller.PerformanceEvaluationController;

import com.example.kosherja.Model.PerformanceEvaluation.Submission;
import com.example.kosherja.Service.PerformanceEvaluation.SubmissionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SubmissionController.class)
public class SubmissionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private SubmissionService submissionService;

    @InjectMocks
    private SubmissionController submissionController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSubmitEvaluation() throws Exception {
        Submission submission = new Submission();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/performance/submission/submitAnswers/studentId")
                        .content("{\"submission\":\"\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Evaluation submitted successfully"));
    }

    @Test
    public void testGetRemainingSubmissionsForManagers() throws Exception {
        Map<String, Integer> remainingSubmissions = new HashMap<>();
        remainingSubmissions.put("Manager1", 3);
        remainingSubmissions.put("Manager2", 2);

        when(submissionService.checkRemainingSubmissions()).thenReturn(remainingSubmissions);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/performance/submission/remaining-submissions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"Manager1\":3,\"Manager2\":2}"));
    }
}
