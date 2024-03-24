package com.example.kosherja.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@Document(collection = "room")
public class Room {
    @Id
    private ObjectId id;
    @Setter private String details;
    @Setter private boolean availability;
    @Setter private double price;

}
