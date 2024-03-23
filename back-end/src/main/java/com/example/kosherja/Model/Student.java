package com.example.kosherja.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class Student extends User{


    private String roomId;
    private String contractId;
    @JsonCreator
    public Student(@JsonProperty("firstName") String firstName,
                   @JsonProperty("lastName") String lastName,
                   @JsonProperty("contactDetailsId") String contactDetailsId,
                   @JsonProperty("roomId")String roomId,
                   @JsonProperty("contractId")String contractId) {
        super(firstName, lastName, contactDetailsId);
        this.roomId=roomId;
        this.contractId=contractId;
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
//    @JsonCreator
//    public Student(@JsonProperty("name")String name){this.name=name;}

//    JsonCreator=>specifies deseralization
//    @JsonProperty maps the json property name with object parameterseok

}
