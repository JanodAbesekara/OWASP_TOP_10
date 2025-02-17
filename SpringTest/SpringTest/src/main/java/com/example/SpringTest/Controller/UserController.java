package com.example.SpringTest.Controller;

import com.example.SpringTest.DTO.LoginDTO;
import com.example.SpringTest.DTO.UpdateDTO;
import com.example.SpringTest.DTO.UserDTO;
import com.example.SpringTest.Services.JWservice;
import com.example.SpringTest.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/v1")
@RestController
public class UserController {

    @Autowired
    private  UserServices userServices;

    @Autowired
    private JWservice jwtService;


    @PostMapping("/Register")
    public UserDTO registerUser(@RequestBody UserDTO userDTO) {

        return userServices.saveuser(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO user) {
        var token = userServices.verify(user);
        return ResponseEntity.status(200).body(token);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(@RequestHeader("Authorization") String token) {
        try {
            String jwtToken = token.substring(7);
            String userRole = jwtService.extractRole(jwtToken);

            if (!"ADMIN".equals(userRole)) {
                return ResponseEntity.status(403).body("Access Denied: Only ADMINs can view users.");
            }

            // Return the list of users if the role is ADMIN
            List<UserDTO> users = userServices.getAllUsers().stream()
                    .map(user -> new UserDTO(user.getId(), user.getEmail(), user.getName(), user.getPassword(), user.getRole()))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid or expired token.");
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteuser(@RequestHeader("Authorization") String token,@PathVariable Long id){
        try {
            String jwtToken = token.substring(7);
            String userRole = jwtService.extractRole(jwtToken);

            if (!"ADMIN".equals(userRole)) {
                return ResponseEntity.status(403).body("Access Denied: Only ADMINs can delete users.");
            }
            userServices.deleteUser(id);
            return ResponseEntity.ok("User Deleted Successfully");
        }
        catch (Exception e){
            return ResponseEntity.status(401).body("Invalid or expired token.");
        }
    }
    @PutMapping("/Update/{email}")
    public ResponseEntity<?> updateUser(@PathVariable String email, @RequestBody UpdateDTO updateDTO) {
        try {
            UserDTO updatedUser = userServices.updateUserByEmail(email, updateDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("User not found");
        }
    }

}




