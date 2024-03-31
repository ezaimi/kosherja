package com.example.kosherja.Model.User;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Document(collection = "student")
public class Student {


    @Id
    private String id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String phone;

    private String buildingId;

    private String roomId;
    private String contractId;

    private String managerId;



    private List<Document> documentList;

    private LocalDate lastPaymentDate;
    private LocalDate nextPaymentDate;

    @JsonCreator
    public Student(@JsonProperty("username") String username,
                   @JsonProperty("password") String password,
                   @JsonProperty("name") String name,
                   @JsonProperty("surname") String surname,
                   @JsonProperty("email") String email,
                   @JsonProperty("phone") String phone,
                   @JsonProperty("buildingId") String buildingId,
                   @JsonProperty("roomId")String roomId,
                   @JsonProperty("contractId")String contractId,
                   @JsonProperty("managerId")String managerId,
                   @JsonProperty("documentList")List<Document> documentList)
//                   @JsonProperty("lastPaymentDate")LocalDate lastPaymentDate,
//                   @JsonProperty("nextPaymentDate")LocalDate nextPaymentDate)


    {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.buildingId = buildingId;
        this.roomId=roomId;
        this.contractId=contractId;
        this.managerId = managerId;
        this.documentList = documentList;
//        this.lastPaymentDate = lastPaymentDate;
//        this.nextPaymentDate = nextPaymentDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }
}
