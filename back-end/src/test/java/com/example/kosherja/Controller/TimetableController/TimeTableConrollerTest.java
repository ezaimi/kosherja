package com.example.kosherja.Controller.TimetableController;

import com.example.kosherja.Model.Timetable.Routes;
import com.example.kosherja.Model.Timetable.TimeTable;
import com.example.kosherja.Repo.TimeTableRepo.MainTimeTable;
import com.example.kosherja.Service.TimeTableService.RouteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TimeTableConrollerTest {

    @InjectMocks
    private TimeTableConroller timeTableController;

    @Mock
    private RouteService routeService;

    @Mock
    private MainTimeTable mainTimeTable;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllRoutes() {
        String id = "123";
        List<Routes> routes = new ArrayList<>();
        routes.add(new Routes());
        routes.add(new Routes());

        when(routeService.getAllRoutes()).thenReturn(routes);

        ResponseEntity<TimeTable> response = timeTableController.getAllRoutes(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(routes, response.getBody().getRoutes());
        verify(mainTimeTable, times(1)).save(any(TimeTable.class));
    }

    @Test
    void testGetTimetable() {
        String id = "456";
        TimeTable timeTable = new TimeTable();
        when(mainTimeTable.findById(id)).thenReturn(Optional.of(timeTable));

        ResponseEntity<TimeTable> response = timeTableController.getTimetable(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(timeTable, response.getBody());
        verify(mainTimeTable, times(1)).findById(id);
    }
}
