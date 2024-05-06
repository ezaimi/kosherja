package com.example.kosherja.Controller.Meetings;

import com.example.kosherja.Model.Meetings.AvailabilityRequest;
import com.example.kosherja.Repo.Meetings.AvailableDtRepo;
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

import java.util.ArrayList;
import java.util.Optional;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StdMeetingController.class)
public class StdMeetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AvailableDtRepo availableDtRepo;

    @Mock
    private AvailableDtService availableDtService;

    @InjectMocks
    private StdMeetingController stdMeetingController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testViewDates() throws Exception {
        // Mock data
        Iterable<AvailabilityRequest> availabilityRequests = new ArrayList<>();
        // Populate availabilityRequests with some test data

        when(availableDtRepo.findAll()).thenReturn(availabilityRequests);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/checkDatess")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        // Add more assertions as needed
    }

    @Test
    public void testBookAppointment() throws Exception {
        // Mock data
        String availabilityId = "1";
        AvailabilityRequest availabilityRequest = new AvailabilityRequest();
        // Populate availabilityRequest with some test data

        // Mock repository behavior
        when(availableDtRepo.findById(availabilityId)).thenReturn(Optional.of(availabilityRequest));
        when(availableDtRepo.save(availabilityRequest)).thenReturn(availabilityRequest);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/checkDatess/book/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        // Add more assertions as needed
    }

    @Test
    public void testCancelAppointment() throws Exception {
        // Mock data
        String availabilityId = "1";
        AvailabilityRequest availabilityRequest = new AvailabilityRequest();
        // Populate availabilityRequest with some test data

        // Mock repository behavior
        when(availableDtRepo.findById(availabilityId)).thenReturn(Optional.of(availabilityRequest));
        when(availableDtRepo.save(availabilityRequest)).thenReturn(availabilityRequest);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/checkDatess/cancel/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        // Add more assertions as needed
    }
}
