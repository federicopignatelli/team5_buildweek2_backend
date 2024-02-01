package epicenergy_backend_buildweek.team5_buildweek2_backend.services;


import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.User;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.enums.Role;
import epicenergy_backend_buildweek.team5_buildweek2_backend.exceptions.BadRequestException;
import epicenergy_backend_buildweek.team5_buildweek2_backend.exceptions.UnauthorizedException;
import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.users.NewUserDTO;
import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.users.UserLoginDTO;
import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.users.UserLoginResponseDTO;
import epicenergy_backend_buildweek.team5_buildweek2_backend.repositories.UserDAO;
import epicenergy_backend_buildweek.team5_buildweek2_backend.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserService usersService;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private JWTTools jwtTools;

    public String authenticateUser(UserLoginDTO body) {

        User user = usersService.findByEmail(body.email());

        if (bcrypt.matches(body.password(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Credenziali non valide!");
        }
    }

    public User findByEmail(UserLoginDTO body) {
        return usersService.findByEmail(body.email());
    }

    public User save(NewUserDTO body) {


        userDAO.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
        });

        User newUser = new User();
        newUser.setSurname(body.surname());
        newUser.setName(body.name());
        newUser.setUsername(body.username());
        newUser.setEmail(body.email());
        newUser.setPassword(bcrypt.encode(body.password()));
        if (body.role().equalsIgnoreCase("admin")){
            newUser.setRole(Role.ADMIN);
        }else{
            newUser.setRole(Role.USER);
        }
        return userDAO.save(newUser);
    }


}
