package com.example.SpringTest.Services;

import com.example.SpringTest.DTO.UserDTO;
import com.example.SpringTest.Models.Users;
import com.example.SpringTest.Repo.UserRepo;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    @Autowired
    private  UserRepo userRepo;
    @Autowired
    private  ModelMapper modelMapper;
    @Autowired
    private JWservice jwtservice;
    @Autowired
    private  AuthenticationManager authenticationManager;


    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);



    public UserDTO saveuser(@NotNull UserDTO userDTO) {
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        Users user = modelMapper.map(userDTO, Users.class);
        userRepo.save(user);
        return userDTO;
    }



    public String verify(@NotNull UserDTO user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtservice.generateToken(user.getEmail());
        }
        return "fail";
    }
}
