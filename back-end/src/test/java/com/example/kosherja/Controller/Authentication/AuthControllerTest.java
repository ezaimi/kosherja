package com.example.kosherja.Controller.Authentication;

import com.example.kosherja.Model.User.LoginRequest;
import com.example.kosherja.Service.Authenticate.UserAuthService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserAuthService userAuthService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLogin_Admin() throws Exception {
        String username = "admin";
        String password = "admin123";

        // Mocking authentication as admin
        when(userAuthService.authenticateAdmin(username, password)).thenReturn("Admin authenticated successfully");

        // Sending login request
        mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
                        .content("{\"username\":\"admin\",\"password\":\"admin123\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Admin authenticated successfully"));
    }

    // Similarly, you can add tests for other user roles (student, manager) here
}
