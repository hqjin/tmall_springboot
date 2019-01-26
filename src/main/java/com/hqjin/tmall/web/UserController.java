package com.hqjin.tmall.web;

import com.hqjin.tmall.dao.UserDAO;
import com.hqjin.tmall.pojo.User;
import com.hqjin.tmall.service.UserService;
import com.hqjin.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/users")
    public Page4Navigator<User> list(
            @RequestParam(value="start",defaultValue = "0")int start,
            @RequestParam(value="size",defaultValue = "5")int size
    ){
        start=start<0?0:start;
        return userService.list(start,size,5);
    }
}
