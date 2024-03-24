package com.example.kosherja.Model.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "contacts")
@NoArgsConstructor
public class Contact {
    @Id
    private ObjectId id;
    @Setter private String number;
    @Setter private String email;
}
