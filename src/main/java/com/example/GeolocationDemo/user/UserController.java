package com.example.GeolocationDemo.user;

import com.example.GeolocationDemo.user.model.User;
import com.example.GeolocationDemo.user.model.UserDTO;
import com.example.GeolocationDemo.user.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5500")
@RestController
public class UserController {

    private final UserServiceImpl userService ;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/withLocations")
    public ResponseEntity<List<UserDTO>> getUsersWithLocation(){
        return userService.getAllUsersWithLocation();
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }
}
