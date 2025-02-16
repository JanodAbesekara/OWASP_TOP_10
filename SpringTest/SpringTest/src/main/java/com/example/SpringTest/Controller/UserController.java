package com.example.SpringTest.Controller;

import com.example.SpringTest.DTO.UserDTO;
import com.example.SpringTest.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1")
@RestController
@CrossOrigin
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
   @GetMapping("/Getallusers")
    public List<UserDTO> getAllUsers() {
        
   }
}
