package com.example.kosherja.Controller.Meetings;

import com.example.kosherja.Model.Meetings.AvailabilityRequest;
import com.example.kosherja.Service.Meetings.AvailableDtService;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AvailableDtController.class)
public class AvailableDtControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AvailableDtService availableDtService;

    @InjectMocks
    private AvailableDtController availableDtController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSetAvailable() throws Exception {
        AvailabilityRequest request = new AvailabilityRequest("1","managerId", LocalDate.now(),true);

        when(availableDtService.setAvailibility2(request.getDate(), request.getManagerId())).thenReturn(request);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/availableDt/managerId")
                        .content("{\"date\":\"2024-05-06\",\"managerId\":\"managerId\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"date\":\"2024-05-06\",\"managerId\":\"managerId\"}"));
    }

    @Test
    public void testGetAllDates() throws Exception {
        List<AvailabilityRequest> requests = new ArrayList<>();
        requests.add(new AvailabilityRequest("1","managerId1", LocalDate.now(),true));
        requests.add(new AvailabilityRequest("1","managerId2", LocalDate.now(),true));

        when(availableDtService.findAllAvailableDates()).thenReturn(requests);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/availableDt/getAllDt")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"date\":\"2024-05-06\",\"managerId\":\"managerId1\"},{\"date\":\"2024-05-06\",\"managerId\":\"managerId2\"}]"));
    }
}
