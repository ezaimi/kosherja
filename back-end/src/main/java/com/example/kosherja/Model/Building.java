package com.example.kosherja.Model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "building")
public class Building {

    @Id
    private String id;
    private String name;
    private double occupancyStatus;


}
