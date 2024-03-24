package com.example.kosherja.Model.UserDetails;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@Document(collection = "contract")
public class Contract {
    @Id
    private ObjectId id;
    @Setter private String terms;

}
