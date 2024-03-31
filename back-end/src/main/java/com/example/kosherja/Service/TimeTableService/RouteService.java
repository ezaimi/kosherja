package com.example.kosherja.Service.TimeTableService;

import com.example.kosherja.Model.Timetable.Routes;
import com.example.kosherja.Repo.TimeTableRepo.RouteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {
    @Autowired
    RouteRepo routeRepo;

    public Routes createRoute(Routes routes){
        Routes routes1=new Routes();
        routes1.setDestination(routes.getDestination());
        routes1.setDepartureTime(routes.getDepartureTime());
        routes1.setStops(routes.getStops());

      return   routeRepo.save(routes1);

    }
    public Routes editRoute(Routes routes,String id){
        Routes routes1=new Routes();
        routes1.setId(id);
        routes1.setDestination(routes.getDestination());
        routes1.setDepartureTime(routes.getDepartureTime());
        routes1.setStops(routes.getStops());

        return   routeRepo.save(routes1);

    }

    public void deleteRoute(String id){
        routeRepo.deleteById(id);
    }
    public List<Routes>getAllRoutes(){
        return  routeRepo.findAll();
    }
}
