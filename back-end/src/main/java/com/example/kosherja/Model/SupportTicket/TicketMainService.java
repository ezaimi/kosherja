package com.example.kosherja.Model.SupportTicket;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="mainService")

public class TicketMainService {

    private String name;
    private String surname;

    @JsonCreator
    public TicketMainService(@JsonProperty("name") String name,
                             @JsonProperty("surname") String surname) {
    }
}