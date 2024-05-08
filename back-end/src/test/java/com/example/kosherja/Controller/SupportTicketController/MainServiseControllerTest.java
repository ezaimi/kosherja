package com.example.kosherja.Controller.SupportTicketController;

import com.example.kosherja.Model.SupportTicket.Priority;
import com.example.kosherja.Model.SupportTicket.Services;
import com.example.kosherja.Model.SupportTicket.Ticket;
import com.example.kosherja.Model.SupportTicket.TicketStatus;
import com.example.kosherja.Repo.SupportTicketRepo.MainServiceTicketRepo;
import com.example.kosherja.Repo.SupportTicketRepo.Service1Repo;
import com.example.kosherja.Repo.SupportTicketRepo.TicketRepo;
import com.example.kosherja.Service.SupportTicket.MainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MainServiseController.class)
public class MainServiseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TicketRepo ticketRepo;

    @Mock
    private MainServiceTicketRepo mainServiceTicketRepo;

    @Mock
    private Service1Repo service1Repo;

    @Mock
    private MainService mainService;

    @InjectMocks
    private MainServiseController mainServiseController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFetchAllTickets() throws Exception {
        List<Ticket> tickets = new ArrayList<>();
        List<String> msg = new ArrayList<>();
        msg.add("A");
        msg.add("B");
        msg.add("C");
        Ticket ticket1 = new Ticket("1",TicketStatus.PENDING,"Fantasy",msg,"student1", Priority.EMERGENT, LocalDateTime.now(),"OK");
        Ticket ticket2 = new Ticket("2",TicketStatus.PENDING,"Idk",msg,"student2", Priority.NOTEMERGENT, LocalDateTime.now(),"OK");
        tickets.add(ticket1);
        tickets.add(ticket2);

        when(ticketRepo.findAll()).thenReturn(tickets);

        mockMvc.perform(MockMvcRequestBuilders.get("/mainService/fetchTickets")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].ticketid").value(ticket1.getTicketId()))
                .andExpect(jsonPath("$[0].topic").value(ticket1.getMsg()))
                .andExpect(jsonPath("$[0].description").value(ticket1.getTopic()))
                .andExpect(jsonPath("$[1].ticketid").value(ticket1.getTicketId()))
                .andExpect(jsonPath("$[1].topic").value(ticket1.getMsg()))
                .andExpect(jsonPath("$[1].description").value(ticket1.getTopic()));
    }

    @Test
    public void testCreateServices() throws Exception {
        Services services = new Services();
        Services savedServices = new Services();
        when(service1Repo.save(services)).thenReturn(savedServices);

        mockMvc.perform(MockMvcRequestBuilders.post("/mainService/createServices")
                        .content("{\"name\":\"John\", \"username\":\"Doe\", \"email\":\"john.doe@example.com\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(savedServices.getName()))
                .andExpect(jsonPath("$.username").value(savedServices.getUsername()))
                .andExpect(jsonPath("$.email").value(savedServices.getEmail()));
    }

    @Test
    public void testSendTicketTo() throws Exception {
        Services service = new Services();
        List<String> msg = new ArrayList<>();
        msg.add("A");
        msg.add("B");
        msg.add("C");
        Ticket ticket = new Ticket("1",TicketStatus.PENDING,"Fantasy",msg,"student1", Priority.EMERGENT, LocalDateTime.now(),"OK");

        when(service1Repo.findById("servId")).thenReturn(java.util.Optional.of(service));
        when(mainServiceTicketRepo.findAllByTicketId("tickId")).thenReturn(ticket);

        mockMvc.perform(MockMvcRequestBuilders.post("/mainService/sentTickTo/servId/tickId")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(service.getName()))
                .andExpect(jsonPath("$.username").value(service.getUsername()))
                .andExpect(jsonPath("$.email").value(service.getEmail()))
                .andExpect(jsonPath("$.tickets[0].ticketid").value(ticket.getTicketId()))
                .andExpect(jsonPath("$.tickets[0].topic").value(ticket.getMsg()))
                .andExpect(jsonPath("$.tickets[0].description").value(ticket.getTopic()));
    }
}
