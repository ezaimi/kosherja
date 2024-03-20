package com.example.kosherja.Model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Data
@Document(collection="student")
@NoArgsConstructor
public class Student {
    @Id
    private ObjectId id;
    @Setter private String firstName;
    @Setter private String lastName;
    @Setter private ObjectId contactDetails;
    @Setter private ObjectId room;
    @Setter private ObjectId contract;

}