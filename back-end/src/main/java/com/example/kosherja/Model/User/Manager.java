package com.example.kosherja.Model.User;


import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;
import java.util.List;

@Data
@Document(collection = "manager")
public class Manager {
    @Id
    private String id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String phone;

//    Movieinfo
    private String selectedMovieId;
    private String selectedShowTime;

    public String getSelectedMovieId() {
        return selectedMovieId;
    }

    public void setSelectedMovieId(String selectedMovieId) {
        this.selectedMovieId = selectedMovieId;
    }

    public String getSelectedShowTime() {
        return selectedShowTime;
    }

    public void setSelectedShowTime(String selectedShowTime) {
        this.selectedShowTime = selectedShowTime;
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

    @Getter
    private String buildingID;

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

    public String getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(String buildingID) {
        this.buildingID = buildingID;
    }
}
