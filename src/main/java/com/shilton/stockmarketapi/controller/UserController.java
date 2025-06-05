package com.shilton.stockmarketapi.controller;

import com.shilton.stockmarketapi.aspect.AppSecured;
import com.shilton.stockmarketapi.domain.User;
import com.shilton.stockmarketapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @PutMapping(value="/save")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add/Update a User")
    @AppSecured
    public void recordTransaction(@RequestBody User u) throws Exception {
        userService.updateUser(u);
    }

}
