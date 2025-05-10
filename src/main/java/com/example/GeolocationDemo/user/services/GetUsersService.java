package com.example.GeolocationDemo.user.services;

import com.example.GeolocationDemo.Query;
import com.example.GeolocationDemo.user.UserRepository;
import com.example.GeolocationDemo.user.model.User;
import com.example.GeolocationDemo.user.model.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUsersService implements Query<Void, List<User>> {


    private final UserRepository userRepository;

    public GetUsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<List<User>> execute(Void input) {

        List<User> users = userRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
}
