package epicenergy_backend_buildweek.team5_buildweek2_backend.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.User;
import epicenergy_backend_buildweek.team5_buildweek2_backend.exceptions.NotFoundException;
import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.users.NewUserDTO;
import epicenergy_backend_buildweek.team5_buildweek2_backend.repositories.UserDAO;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private Cloudinary cloudinary;

    public List<User> getAllUsers(){
        return userDAO.findAll();
    }

    public User save(NewUserDTO body){
        userDAO.findByEmail(body.email()).ifPresent(user -> {
            try {
                throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
            } catch (BadRequestException e) {
                throw new RuntimeException(e);
            }
        });

        User newUser = new User();
        newUser.setName(body.name());
        newUser.setSurname(body.surname());
        newUser.setUsername(body.username());
        newUser.setEmail(body.email());
        newUser.setPassword(body.password());
        return userDAO.save(newUser);
    }

    public User findById(UUID id) {
        return userDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(UUID id) {
        User found = this.findById(id);
        userDAO.delete(found);
    }

    public User findByIdAndUpdate(UUID id, User body) {
        User found = this.findById(id);
        found.setSurname(body.getSurname());
        found.setName(body.getName());
        found.setEmail(body.getEmail());
        found.setPassword(body.getPassword());
        found.setAvatar(body.getAvatar());
        found.setName(body.getName());
        found.setRole(body.getRole());
        return userDAO.save(found);
    }


    public User findByEmail(String email) throws NotFoundException {
        return userDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovata!"));
    }

    public String uploadAvatar(MultipartFile file) throws IOException {
        String url = (String) cloudinary.uploader()
                .upload(file.getBytes(), ObjectUtils.emptyMap())
                .get("url");
        return url;
    }

}
