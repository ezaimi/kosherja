package com.example.kosherja.Controller.PerformanceEvaluationController;

import com.example.kosherja.Service.PerformanceEvaluation.BonusAllocationService;
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

@WebMvcTest(BonusController.class)
public class BonusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BonusAllocationService bonusAllocationService;

    @Mock
    private SubmissionService submissionService;

    @InjectMocks
    private BonusController bonusController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShowTotalScorePerManager() throws Exception {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Manager1", 100);
        scores.put("Manager2", 200);

        when(bonusAllocationService.totalScoresForEachManager()).thenReturn(scores);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/bonus")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"Manager1\":100,\"Manager2\":200}"));
    }

    @Test
    public void testCheckAndCalculate_AllSubmissionsReceived() throws Exception {
        Map<String, Integer> remainingSubmissions = new HashMap<>();
        remainingSubmissions.put("Manager1", 0);
        remainingSubmissions.put("Manager2", 0);

        Map<String, Integer> scores = new HashMap<>();
        scores.put("Manager1", 100);
        scores.put("Manager2", 200);

        when(submissionService.checkRemainingSubmissions()).thenReturn(remainingSubmissions);
        when(bonusAllocationService.totalScoresForEachManager()).thenReturn(scores);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/bonus/checkAndCalculate")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"Manager1\":100,\"Manager2\":200}"));
    }

    @Test
    public void testCheckAndCalculate_NotAllSubmissionsReceived() throws Exception {
        Map<String, Integer> remainingSubmissions = new HashMap<>();
        remainingSubmissions.put("Manager1", 1);
        remainingSubmissions.put("Manager2", 0);

        when(submissionService.checkRemainingSubmissions()).thenReturn(remainingSubmissions);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/bonus/checkAndCalculate")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(status().reason("Not all submissions received."));
    }

    @Test
    public void testShowHighestScoreManager() throws Exception {
        Map<String, Object> highestScoreManager = new HashMap<>();
        highestScoreManager.put("Manager", "Manager1");
        highestScoreManager.put("Score", 200);

        when(bonusAllocationService.managerWithHighestScore()).thenReturn(highestScoreManager);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/bonus/highestScore")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"Manager\":\"Manager1\",\"Score\":200}"));
    }
}
