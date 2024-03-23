package com.example.kosherja.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="ticket")
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    private String id;
    private String status;
    private String studentId;

    @JsonCreator
    public Ticket(@JsonProperty("status")String status, @JsonProperty("studentId")String studentId){
        this.status=status;
        this.studentId=studentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
