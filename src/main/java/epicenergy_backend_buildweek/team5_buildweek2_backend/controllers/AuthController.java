package epicenergy_backend_buildweek.team5_buildweek2_backend.controllers;

import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.User;
import epicenergy_backend_buildweek.team5_buildweek2_backend.exceptions.BadRequestException;
import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.users.NewUserDTO;
import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.users.NewUserResponseDTO;
import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.users.UserLoginDTO;
import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.users.UserLoginResponseDTO;
import epicenergy_backend_buildweek.team5_buildweek2_backend.services.AuthService;
import epicenergy_backend_buildweek.team5_buildweek2_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body) {
        String accessToken = authService.authenticateUser(body);
        User user = authService.findByEmail(body);

        return new UserLoginResponseDTO(
                accessToken,
                user.getId(),
                user.getName(),
                user.getRole().toString(),
                user.getSurname(),
                user.getUsername(),
                user.getAvatar()
        );
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserResponseDTO createUser(@RequestBody @Validated NewUserDTO newUserPayload, BindingResult validation) {
        System.out.println(validation);
        if (validation.hasErrors()) {
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("Ci sono errori nel payload!");
        } else {
            User newUser = authService.save(newUserPayload);

            return new NewUserResponseDTO(newUser.getId());
        }
    }
}
