package com.example.SpringTest.Services;

import com.example.SpringTest.DTO.LoginDTO;
import com.example.SpringTest.DTO.UpdateDTO;
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

import java.util.List;

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



    public String verify(@NotNull LoginDTO user) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            Users user1 = userRepo.findByEmail(user.getEmail());
            return jwtservice.generateToken(user.getEmail(),user1.getRole());
        }
        return "fail";
    }

    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    public UserDTO updateUserByEmail(String email, UpdateDTO updateDTO) {
        Users user = userRepo.findByEmail(email);

        if(userRepo.findByEmail(email) == null){
            return null;
        }

        user.setName(updateDTO.getName());
        user.setRole(updateDTO.getRole());

        Users updatedUser = userRepo.save(user);

        return modelMapper.map(updatedUser, UserDTO.class);
    }


}
