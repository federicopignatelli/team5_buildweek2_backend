package epicenergy_backend_buildweek.team5_buildweek2_backend.controllers;


import epicenergy_backend_buildweek.team5_buildweek2_backend.config.MailgunSender;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.User;
import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.users.NewUserDTO;
import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.users.NewUserResponseDTO;
import epicenergy_backend_buildweek.team5_buildweek2_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailgunSender mailgunSender;

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

    @PostMapping
    public NewUserResponseDTO createUser(@RequestBody @Validated NewUserDTO newUserPayload){
         User newUser = userService.save(newUserPayload);
         mailgunSender.sendRegistrationEmail(newUser.getEmail());
         return new NewUserResponseDTO(newUser.getId());
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getUserByIdAndDelete(@PathVariable UUID userId) {
        userService.findByIdAndDelete(userId);
    }

    //endpoint immagini
    @PostMapping("/{userId}upload")
    public String uploadAvatar(@RequestParam("avatar") MultipartFile file, @PathVariable UUID userId) throws IOException {
        return userService.uploadAvatar(file);
    }

}
