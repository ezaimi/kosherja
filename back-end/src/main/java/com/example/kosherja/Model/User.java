package com.example.kosherja.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {


    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String contactDetailsId;

    @JsonCreator
    public User(
            @JsonProperty("firstName")String firstName,
            @JsonProperty("lastName")String lastName,
            @JsonProperty("contactDetailsId")String contactDetailsId
    )
    {
        this.firstName=firstName;
        this.lastName=lastName;
        this.contactDetailsId=contactDetailsId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactDetailsId() {
        return contactDetailsId;
    }

    public void setContactDetailsId(String contactDetailsId) {
        this.contactDetailsId = contactDetailsId;
    }
}
