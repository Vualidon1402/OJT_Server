package com.ojt_server.modules.user.controller;

import com.ojt_server.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    //hiện thị tất cả users
    @GetMapping("/findAll")
    public Object getUsers(){
        return userService.findUsers();
    }



}
