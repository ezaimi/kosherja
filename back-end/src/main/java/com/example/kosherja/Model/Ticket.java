package com.example.kosherja.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="ticket")
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    private String ticketId;
    private String status;
    private String topic;
    private List<String> msg;
    private String studentId;

    private String priority;

    @JsonCreator
    public Ticket(@JsonProperty("status")String status,
                  @JsonProperty("studentId")String studentId,
                  @JsonProperty("topic")String topic,
                  @JsonProperty("msg") List<String> msg,
                  @JsonProperty("priority")String priority){
        this.status=status;
        this.studentId=studentId;
        this.topic=topic;
        this.msg=msg;
        this.priority=priority;
    };



    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
