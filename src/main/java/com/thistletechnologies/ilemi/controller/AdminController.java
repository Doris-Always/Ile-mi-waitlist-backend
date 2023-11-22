package com.thistletechnologies.ilemi.controller;

import com.thistletechnologies.ilemi.model.User;
import com.thistletechnologies.ilemi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {
        @Autowired
         UserService userService;


    @GetMapping("/get-user-by-id/{userId}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable ("userId") String userId) {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);

    }
    @GetMapping("/get-all-users")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }
    @GetMapping("/get-user-by-email/{email}")
    public ResponseEntity<Optional<User>> getUserByEmail(@PathVariable ("email") String email){
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }

    }
