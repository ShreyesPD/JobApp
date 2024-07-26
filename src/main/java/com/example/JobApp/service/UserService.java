package com.example.JobApp.service;


import com.example.JobApp.model.User;
import com.example.JobApp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public User getUser(Integer userId) {
        return userRepo.findById(userId).get();
    }

}
