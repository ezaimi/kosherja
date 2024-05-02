package com.example.kosherja.Model.SupportTicket;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Document(collection="service")
@NoArgsConstructor

public class Services{
    @Id
    private  String id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String phoneNr;
    private ServiseLevels serviseLevels;
    private List<Ticket> ticketList;


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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ServiseLevels getServiseLevels() {
        return serviseLevels;
    }

    public void setServiseLevels(ServiseLevels serviseLevels) {
        this.serviseLevels = serviseLevels;
    }
}