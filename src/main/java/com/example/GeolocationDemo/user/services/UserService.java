package com.example.GeolocationDemo.user.services;

import com.example.GeolocationDemo.user.model.User;
import com.example.GeolocationDemo.user.model.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<String> createUser(User user);

    ResponseEntity<Void> deleteUser(Integer id);

    ResponseEntity<List<UserDTO>> getAllUsersWithLocation();

    ResponseEntity<List<UserDTO>> getUsers();
}
