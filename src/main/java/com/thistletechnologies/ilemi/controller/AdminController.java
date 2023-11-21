package com.thistletechnologies.ilemi.controller;

import com.thistletechnologies.ilemi.model.User;
import com.thistletechnologies.ilemi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

        private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }


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
