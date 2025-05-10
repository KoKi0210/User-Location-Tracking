package com.example.GeolocationDemo.user;

import com.example.GeolocationDemo.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByNumber(String number);

    @Query("SELECT DISTINCT g.user FROM Geolocation g WHERE g.user IS NOT NULL")
    List<User> findAllUsersWithLocations();
}
