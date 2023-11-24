package com.thistletechnologies.ilemi.controller;

import com.thistletechnologies.ilemi.dto.request.JoinWaitingListRequest;
import com.thistletechnologies.ilemi.dto.response.JoinWaitingListResponse;
import com.thistletechnologies.ilemi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
   UserService userService;



    @PostMapping("/join-wait-list")
    public ResponseEntity<JoinWaitingListResponse> joinWaitList(@RequestBody JoinWaitingListRequest joinWaitingListRequest){

        return new ResponseEntity<>(userService.joinWaitingList(joinWaitingListRequest), HttpStatus.CREATED);
    }

}
