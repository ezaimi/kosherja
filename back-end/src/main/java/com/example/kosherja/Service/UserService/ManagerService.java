package com.example.kosherja.Service.UserService;

import com.example.kosherja.Model.User.Manager;
import com.example.kosherja.Repo.UserRepo.ManagerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManagerService {

    @Autowired
    private ManagerRepo managerRepo;


    public Manager createCoordinator(Manager manager) {
        // check if the username or email is already in use
        return managerRepo.save(manager);
    }

    public boolean existsByUsernameOrEmail(String username, String email) {
        return managerRepo.existsByUsernameOrEmail(username, email);
    }

    public boolean deleteCoordinatorById(String id) {
        Optional<Manager> optionalManager = managerRepo.findById(id);

        if (optionalManager.isPresent()) {
            Manager deletedManager = optionalManager.get();
            managerRepo.delete(deletedManager);

            return true;
        } else {
            // manager not found
            return false;
        }
    }




}
