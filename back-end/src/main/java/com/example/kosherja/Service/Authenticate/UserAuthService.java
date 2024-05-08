package com.example.kosherja.Service.Authenticate;


import com.example.kosherja.Model.User.Admin;
import com.example.kosherja.Model.User.Manager;
import com.example.kosherja.Model.User.Student;
import com.example.kosherja.Model.User.TopSer;
import com.example.kosherja.Repo.SupportTicketRepo.TopSerRepo;
import com.example.kosherja.Repo.UserRepo.AdminRepo;
import com.example.kosherja.Repo.UserRepo.ManagerRepo;
import com.example.kosherja.Repo.UserRepo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService {
    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private TopSerRepo topSerRepo;
    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private ManagerRepo managerRepo;

    public Object authenticateAdmin(String username, String password) {
        // Authenticate against admin table
        Admin admin = adminRepo.findByUsername(username);
        if( admin != null && admin.getPassword().equals(password)){
            return admin;
        }
        return  null;
    }

    public Object authenticateStudent(String username, String password) {
        // Authenticate against student table
        Student student = studentRepo.findByUsername(username);
        if( student != null && student.getPassword().equals(password)){
            return student;
        }
        return  null;

    }

    public Object authenticateManager(String username, String password) {
        // Authenticate against manager table
        Manager manager = managerRepo.findByUsername(username);
        if( manager != null && manager.getPassword().equals(password)){
            return manager;
        }
        return null;
    }
    public Object authenticateMainServ(String username, String password) {
        // Authenticate against manager table
        TopSer topSer=topSerRepo.findByUsername(username);
        if( topSer != null && topSer.getPassword().equals(password)){
            return topSer;
        }
        return null;
    }
}