package com.example.kosherja.Service.SupportTicket;

import com.example.kosherja.Model.User.Student;
import com.example.kosherja.Model.User.TopSer;
import com.example.kosherja.Repo.SupportTicketRepo.Service1Repo;
import com.example.kosherja.Repo.UserRepo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.SelectionOperators;
import org.springframework.stereotype.Service;

@Service
public class MainService {
   @Autowired
    StudentRepo studentRepo;
    @Autowired
    private Service1Repo service1Repo;

    public void deleteService(String id){
        service1Repo.deleteById(id);
    }
    public TopSer createMainService(TopSer model) {


        TopSer model1 = new TopSer();
        model1.setName(model.getName());
        model1.setSurname(model.getSurname());
        model1.setUsername(model.getUsername());
        model1.setPassword(model.getPassword());
        model1.setEmail(model.getEmail());
        // Save the model1 object to the database or perform any other necessary operations
        return model1;
    }
}
