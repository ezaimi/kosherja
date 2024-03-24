package com.example.kosherja.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "student")
public class Student{


    @Id
    private String id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String phone;


    private String roomId;
    private String contractId;

    private String managerId;

    @JsonCreator
    public Student(@JsonProperty("username") String username,
                   @JsonProperty("password") String password,
                   @JsonProperty("name") String name,
                   @JsonProperty("surname") String surname,
                   @JsonProperty("email") String email,
                   @JsonProperty("phone") String phone,
                   @JsonProperty("roomId")String roomId,
                   @JsonProperty("contractId")String contractId,
                   @JsonProperty("managerId")String managerId)
    {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.roomId=roomId;
        this.contractId=contractId;
        this.managerId = managerId;
    }

}
