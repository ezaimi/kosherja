package com.example.kosherja.Model;


import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

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


    @Getter
    private String buildingID;


}
