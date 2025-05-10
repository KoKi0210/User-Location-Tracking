package com.example.GeolocationDemo.user.services;

import com.example.GeolocationDemo.Query;
import com.example.GeolocationDemo.user.UserRepository;
import com.example.GeolocationDemo.user.model.User;
import com.example.GeolocationDemo.user.model.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllUsersWithLocationService implements Query<Void, List<UserDTO>> {

    private final UserRepository userRepository;

    public GetAllUsersWithLocationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<List<UserDTO>> execute(Void input) {
        List<User> users = userRepository.findAllUsersWithLocations();
        List<UserDTO> userDTOS = users.stream().map(UserDTO::new).toList();
        return ResponseEntity.ok(userDTOS);
    }
}
