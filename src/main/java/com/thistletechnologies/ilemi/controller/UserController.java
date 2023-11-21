package com.thistletechnologies.ilemi.controller;

import com.thistletechnologies.ilemi.dto.request.JoinWaitingListRequest;
import com.thistletechnologies.ilemi.dto.response.JoinWaitingListResponse;
import com.thistletechnologies.ilemi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/join-wait-list")
    public ResponseEntity<JoinWaitingListResponse> joinWaitList(@RequestBody JoinWaitingListRequest joinWaitingListRequest){

        return new ResponseEntity<>(userService.joinWaitingList(joinWaitingListRequest), HttpStatus.CREATED);
    }

}
