package com.example.kosherja.Service.TimeTableService;

import com.example.kosherja.Model.Timetable.Routes;
import com.example.kosherja.Model.Timetable.TimeTable;
import com.example.kosherja.Repo.TimeTableRepo.MainTimeTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimeTableService {

    @Autowired
    MainTimeTable mainTimeTable;

    @Autowired
    RouteService routeService;


}
