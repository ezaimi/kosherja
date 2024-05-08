package com.example.kosherja.Controller.UserController;

import com.example.kosherja.Model.User.Manager;
import com.example.kosherja.Repo.UserRepo.ManagerRepo;
import com.example.kosherja.Service.UserService.ManagerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ManagerControllerTest {

    @InjectMocks
    private ManagerController managerController;

    @Mock
    private ManagerRepo managerRepo;

    @Mock
    private ManagerService managerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCoordinator_CoordinatorExists() {
        Manager coordinator = new Manager();
        coordinator.setUsername("existingUsername");
        coordinator.setEmail("existingEmail");

        when(managerService.existsByUsernameOrEmail(coordinator.getUsername(), coordinator.getEmail())).thenReturn(true);

        ResponseEntity<?> response = managerController.createCoordinator(coordinator);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Coordinator with the same username or email already exists", response.getBody());
        verify(managerService, times(1)).existsByUsernameOrEmail(coordinator.getUsername(), coordinator.getEmail());
        verify(managerService, never()).createCoordinator(coordinator);
    }

    @Test
    void testCreateCoordinator_CoordinatorDoesNotExist() {
        Manager coordinator = new Manager();
        coordinator.setUsername("newUsername");
        coordinator.setEmail("newEmail");

        when(managerService.existsByUsernameOrEmail(coordinator.getUsername(), coordinator.getEmail())).thenReturn(false);
        when(managerService.createCoordinator(coordinator)).thenReturn(coordinator);

        ResponseEntity<?> response = managerController.createCoordinator(coordinator);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(coordinator, response.getBody());
        verify(managerService, times(1)).existsByUsernameOrEmail(coordinator.getUsername(), coordinator.getEmail());
        verify(managerService, times(1)).createCoordinator(coordinator);
    }

    @Test
    void testDeleteCoordinator_CoordinatorDeleted() {
        String id = "123";

        when(managerService.deleteCoordinatorById(id)).thenReturn(true);

        ResponseEntity<String> response = managerController.deleteCoordinator(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Coordinator deleted successfully", response.getBody());
        verify(managerService, times(1)).deleteCoordinatorById(id);
    }

    @Test
    void testDeleteCoordinator_CoordinatorNotFound() {
        String id = "456";

        when(managerService.deleteCoordinatorById(id)).thenReturn(false);

        ResponseEntity<String> response = managerController.deleteCoordinator(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(managerService, times(1)).deleteCoordinatorById(id);
    }

    @Test
    void testCreateTicket() {
        String id = "789";
        Manager manager = new Manager();
        manager.setBuildingID(id);

        when(managerRepo.save(manager)).thenReturn(manager);

        ResponseEntity<Manager> response = managerController.createTicket(id, manager);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(manager, response.getBody());
        verify(managerRepo, times(1)).save(manager);
    }
}
