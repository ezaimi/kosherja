package com.example.kosherja.Model.SupportTicket;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Document(collection="service")
@NoArgsConstructor

public class Service1{
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String phoneNr;
    private List<Ticket> ticketList;
    @JsonCreator
    public Service1(@JsonProperty("ursername") String username,
                    @JsonProperty("password") String password,
                    @JsonProperty("name") String name,
                    @JsonProperty("surname") String surname,
                    @JsonProperty("email") String email,
                    @JsonProperty("phoneNr")String phoneNr,
                    @JsonProperty("ticketList")List<Ticket>ticketList) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNr = phoneNr;
        this.ticketList = ticketList != null ? ticketList : new ArrayList<>();
    }


    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }
}