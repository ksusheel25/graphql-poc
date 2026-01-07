package com.sushilk.graphqlpoc.services;

import com.sushilk.graphqlpoc.entities.User;
import com.sushilk.graphqlpoc.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //create user methods here
    public User createUser(User user) {
        return userRepository.save(user);
    }
    //find all users method
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    //find user by id method
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
    //update user method
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setPhone(userDetails.getPhone());
        user.setCity(userDetails.getCity());
        return userRepository.save(user);
    }
    //delete user method
    public boolean deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
        return true;
    }
}
