package com.example.GeolocationDemo.user.services;

import com.example.GeolocationDemo.exception.UserNotFoundException;
import com.example.GeolocationDemo.user.UserRepository;
import com.example.GeolocationDemo.user.model.User;
import com.example.GeolocationDemo.user.model.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<String> createUser(User user) {
        Optional<User> optional = userRepository.findByNumber(user.getNumber());

        if (optional.isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Успешно създаден потребител: " + user.getUsername());
        }
        return ResponseEntity.badRequest().body("Грешка!");
    }

    @Override
    public ResponseEntity<Void> deleteUser(Integer id) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        throw new UserNotFoundException(id);

    }

    @Override
    public ResponseEntity<List<UserDTO>> getAllUsersWithLocation() {
        List<User> users = userRepository.findAllUsersWithLocations();
        List<UserDTO> userDTOS = users.stream().map(UserDTO::new).toList();
        return ResponseEntity.ok(userDTOS);
    }

    @Override
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = users.stream().map(UserDTO::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(userDTOS);
    }
}
