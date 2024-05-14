package com.example.kosherja.Controller.SupportTicketController;

import com.example.kosherja.Model.SupportTicket.Priority;
import com.example.kosherja.Model.SupportTicket.Ticket;
import com.example.kosherja.Model.SupportTicket.TicketStatus;
import com.example.kosherja.Repo.SupportTicketRepo.TicketRepo;
import com.example.kosherja.Repo.UserRepo.StudentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TicketControllerTest {

    @InjectMocks
    private TicketController ticketController;

    @Mock
    private TicketRepo ticketRepo;

    @Mock
    private StudentRepo studentRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTicket() {
        List<String> msg = new ArrayList<>();
        msg.add("A");
        msg.add("B");
        msg.add("C");
        String studentId = "123";
        Ticket ticket = new Ticket();
        ticket.setTopic("Test Topic");
        ticket.setPriority("Priority.EMERGENT");
        ticket.setMsg(msg);

        when(ticketRepo.save(any(Ticket.class))).thenReturn(ticket);

        ResponseEntity<Ticket> response = ticketController.createTicket(studentId, ticket);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(ticket, response.getBody());
        verify(ticketRepo, times(1)).save(any(Ticket.class));
    }

    @Test
    void testGetAllTickets() {
        String studentId = "123";
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket());
        tickets.add(new Ticket());

        when(ticketRepo.findTicketBystudentId(studentId)).thenReturn(tickets);

        ResponseEntity<List<Ticket>> response = ticketController.getAllTickets(studentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tickets, response.getBody());
        verify(ticketRepo, times(1)).findTicketBystudentId(studentId);
    }

    @Test
    void testDeleteTicket() {
        String ticketId = "456";

        ResponseEntity<Void> response = ticketController.deleteTicket(ticketId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(ticketRepo, times(1)).deleteByticketId(ticketId);
    }
}
