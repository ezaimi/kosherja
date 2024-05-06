package com.example.kosherja.Controller.TimetableController;

import com.example.kosherja.Model.Timetable.Routes;
import com.example.kosherja.Service.TimeTableService.RouteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RouteControllerTest {

    @InjectMocks
    private RouteController routeController;

    @Mock
    private RouteService routeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateRoute() {
        Routes route = new Routes();
        when(routeService.createRoute(route)).thenReturn(route);

        ResponseEntity<Routes> response = routeController.create(route);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(route, response.getBody());
        verify(routeService, times(1)).createRoute(route);
    }

    @Test
    void testEditRoute() {
        String id = "123";
        Routes route = new Routes();
        when(routeService.editRoute(route, id)).thenReturn(route);

        ResponseEntity<Routes> response = routeController.editRoutes(id, route);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(route, response.getBody());
        verify(routeService, times(1)).editRoute(route, id);
    }

    @Test
    void testDeleteRoute() {
        String id = "456";

        ResponseEntity<Void> response = routeController.deleteRoute(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(routeService, times(1)).deleteRoute(id);
    }
}
