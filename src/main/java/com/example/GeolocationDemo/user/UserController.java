package com.example.GeolocationDemo.user;

import com.example.GeolocationDemo.user.model.User;
import com.example.GeolocationDemo.user.model.UserDTO;
import com.example.GeolocationDemo.user.services.CreateUserService;
import com.example.GeolocationDemo.user.services.DeleteUserService;
import com.example.GeolocationDemo.user.services.GetAllUsersWithLocationService;
import com.example.GeolocationDemo.user.services.GetUsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5500")
@RestController
public class UserController {

    private final CreateUserService createUserService;
    private final GetUsersService getUsersService;
    private final DeleteUserService deleteUserService;
    private final GetAllUsersWithLocationService getAllUsersWithLocationService;

    public UserController(CreateUserService createUserService,
                          GetUsersService getUsersService,
                          DeleteUserService deleteUserService,
                          GetAllUsersWithLocationService getAllUsersWithLocationService) {
        this.createUserService = createUserService;
        this.getUsersService = getUsersService;
        this.deleteUserService = deleteUserService;
        this.getAllUsersWithLocationService = getAllUsersWithLocationService;
    }

    @PostMapping("/user")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        return createUserService.execute(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return getUsersService.execute(null);
    }

    @GetMapping("/users/withLocations")
    public ResponseEntity<List<UserDTO>> getUsersWithLocation(){
        return getAllUsersWithLocationService.execute(null);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        return deleteUserService.execute(id);
    }
}
