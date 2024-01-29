package epicenergy_backend_buildweek.team5_buildweek2_backend.controllers;


import epicenergy_backend_buildweek.team5_buildweek2_backend.entites.User;
import epicenergy_backend_buildweek.team5_buildweek2_backend.services.UserService;
import kong.unirest.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.UserCredentialsDataSourceAdapter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable UUID userId) {
        return userService.findById(userId);
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User getUserByIdAndUpdate(@PathVariable UUID userId, @RequestBody User modifiedUserPayload) {
        return userService.findByIdAndUpdate(userId, modifiedUserPayload);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getUserByIdAndDelete(@PathVariable UUID userId) {
        userService.findByIdAndDelete(userId);
    }

}
