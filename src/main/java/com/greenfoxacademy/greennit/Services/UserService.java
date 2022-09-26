package com.greenfoxacademy.greennit.Services;

import com.greenfoxacademy.greennit.Models.User;
import com.greenfoxacademy.greennit.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(String username, String password) {
        User user = new User(username,password);
        userRepository.save(user);
    }

    public Boolean loginVerification(String username, String password) {
        return userRepository.existsUserByUsername(username) && userRepository.findFirstByUsername(username).getPassword().equals(password);
    }

    public Long findIdByUsername(String username) {
        return userRepository.findFirstByUsername(username).getId();
    }

    public User findFirstById(Long id) {
        return userRepository.findFirstById(id);
    }

    public Boolean existsUserByUsername(String username) {
        return userRepository.existsUserByUsername(username);
    }
}
