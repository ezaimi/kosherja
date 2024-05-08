package com.example.kosherja.Controller.FacilitiesController;

import com.example.kosherja.Model.Facilities.Building;
import com.example.kosherja.Model.Facilities.BuildingRoomDTO;
import com.example.kosherja.Repo.FacilitiesRepo.BuildingRepo;
import com.example.kosherja.Service.Facilities.BuildingService;
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
import java.util.Collections;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BuildingController.class)
public class BuildingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BuildingRepo buildingRepo;

    @Mock
    private BuildingService buildingService;

    @InjectMocks
    private BuildingController buildingController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetRoomInfoPerBuilding() throws Exception {
        List<BuildingRoomDTO> buildingRoomDTOList = Collections.emptyList();

        when(buildingService.getBuildingRooms()).thenReturn(buildingRoomDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/building/checkRoomAvailability")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
}
