package com.example.kosherja.Model.UserDetails;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "documents")
public class Documents {

    @Id
    private String id;
    private String type;
    private String number;

}

