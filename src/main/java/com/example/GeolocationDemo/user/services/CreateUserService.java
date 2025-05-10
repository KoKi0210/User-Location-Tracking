package com.example.GeolocationDemo.user.services;

import com.example.GeolocationDemo.Command;
import com.example.GeolocationDemo.user.UserRepository;
import com.example.GeolocationDemo.user.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateUserService implements Command<User, String> {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public CreateUserService(UserRepository userRepository,
                             PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<String> execute(User user) {

        Optional<User> optional = userRepository.findByNumber(user.getNumber());

        if (optional.isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Успешно създаден потребител: " + user.getUsername());
        }
        return ResponseEntity.badRequest().body("Грешка!");
    }
}
