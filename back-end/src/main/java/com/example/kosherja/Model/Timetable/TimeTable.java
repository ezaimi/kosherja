package com.example.kosherja.Model.Timetable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "timetable")
@NoArgsConstructor
@AllArgsConstructor
public class TimeTable {
    @Id
    private String id;
    private List<Routes> routes;
    private String magersId;



    public String getMagersId() {
        return magersId;
    }

    public void setMagersId(String magersId) {
        this.magersId = magersId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Routes> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Routes> routes) {
        this.routes = routes;
    }

    public Routes getFistRoute(){
        return  routes.get(0);
    }
}
