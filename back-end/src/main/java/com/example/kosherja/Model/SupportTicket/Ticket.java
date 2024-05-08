package com.example.kosherja.Model.SupportTicket;

import com.example.kosherja.Model.User.Student;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Document(collection="ticket")
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    private String ticketId;
    private String status; // Changed to enum TicketStatus
    private String topic;
    private List<String> msg;
    private String studentId;
    private String priority; // Changed to enum Priority
    private  String stdName;

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }




    // Default constructor setting default values
//    public Ticket() {
//        this.status = TicketStatus.PENDING;
//        this.priority = Priority.NOTEMERGENT;
//    }

    // Getter methods for all properties


    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String  status) {
        this.status = status;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<String> getMsg() {
        return msg;
    }

    public void setMsg(List<String> msg) {
        this.msg = msg;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    private LocalDateTime createdAt;
    private String formattedCreatedAt;

    // Getter and setter for createdAt field
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        this.formattedCreatedAt = createdAt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); // Format the date
    }

    // Getter for formattedCreatedAt field
    public String getFormattedCreatedAt() {
        return formattedCreatedAt;
    }


    // JsonCreator for constructor
//    @JsonCreator
//    public Ticket(@JsonProperty("status") TicketStatus status,
//                  @JsonProperty("studentId") String studentId,
//                  @JsonProperty("topic") String topic,
//                  @JsonProperty("msg") List<String> msg,
//                  @JsonProperty("priority") Priority priority) {
//        this.status = status;
//        this.studentId = studentId;
//        this.topic = topic;
//        this.msg = msg;
//        this.priority = priority;
//    }
}
