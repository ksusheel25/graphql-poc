package com.sushilk.graphqlpoc.controllers;

import com.sushilk.graphqlpoc.dtos.UserInput;
import com.sushilk.graphqlpoc.entities.User;
import com.sushilk.graphqlpoc.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<String> helloHandler(){
        return ResponseEntity.ok("Hello Word!");
    }

    @MutationMapping(name = "createUser")
    public User createUser(@Argument("input") UserInput userInput) {
        User user = new User();
        user.setName(userInput.name());
        user.setEmail(userInput.email());
        user.setPhone(userInput.phone());
        user.setCity(userInput.city());
        return userService.createUser(user);
    }

    @QueryMapping(name = "getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @QueryMapping(name = "getUserById")
    public User getUserById(@Argument Long id) {
        return userService.getUserById(id);
    }

    @MutationMapping(name = "updateUser")
    public User updateUser(@Argument Long id, @Argument("input") UserInput userInput) {
        User userDetails = new User();
        userDetails.setName(userInput.name());
        userDetails.setEmail(userInput.email());
        userDetails.setPhone(userInput.phone());
        userDetails.setCity(userInput.city());
        return userService.updateUser(id, userDetails);
    }

    @MutationMapping(name = "deleteUser")
    public boolean deleteUser(@Argument Long id) {
        return userService.deleteUser(id);
    }
}
