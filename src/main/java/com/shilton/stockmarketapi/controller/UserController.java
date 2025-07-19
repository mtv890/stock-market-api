package com.shilton.stockmarketapi.controller;

import com.shilton.stockmarketapi.domain.user.User;
import com.shilton.stockmarketapi.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }


    @PutMapping(value = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add/Update a User")
    public void recordTransaction(@RequestBody User u) throws Exception {
        userService.updateUser(u);
    }

}
