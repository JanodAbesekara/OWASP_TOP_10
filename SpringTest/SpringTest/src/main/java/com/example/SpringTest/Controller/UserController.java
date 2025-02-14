package com.example.SpringTest.Controller;

import com.example.SpringTest.DTO.UserDTO;
import com.example.SpringTest.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@RestController
public class UserController {

    @Autowired
    private  UserServices userServices;


    @PostMapping("/Register")
    public UserDTO registerUser(@RequestBody UserDTO userDTO) {
        return userServices.saveuser(userDTO);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO user) {
        return userServices.verify(user);
    }
}
