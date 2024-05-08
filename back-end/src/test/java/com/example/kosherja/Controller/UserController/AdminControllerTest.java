package com.example.kosherja.Controller.UserController;

import com.example.kosherja.Model.User.Admin;
import com.example.kosherja.Model.User.ManagerDTO;
import com.example.kosherja.Repo.UserRepo.AdminRepo;
import com.example.kosherja.Service.UserService.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AdminControllerTest {

    @InjectMocks
    private AdminController adminController;

    @Mock
    private AdminService adminService;

    @Mock
    private AdminRepo adminRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAdmin() {
        Admin admin = new Admin();
        when(adminRepo.save(admin)).thenReturn(admin);

        ResponseEntity<Admin> response = adminController.createStudent(admin);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(admin, response.getBody());
        verify(adminRepo, times(1)).save(admin);
    }
}
