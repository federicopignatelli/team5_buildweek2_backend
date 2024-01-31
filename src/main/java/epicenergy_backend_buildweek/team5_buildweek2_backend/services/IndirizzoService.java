package epicenergy_backend_buildweek.team5_buildweek2_backend.services;

import com.cloudinary.Cloudinary;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Cliente;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Comune;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Indirizzo;
import epicenergy_backend_buildweek.team5_buildweek2_backend.exceptions.NotFoundException;
import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.indirizzo.NewIndirizzoDTO;
import epicenergy_backend_buildweek.team5_buildweek2_backend.repositories.ClienteRepository;
import epicenergy_backend_buildweek.team5_buildweek2_backend.repositories.IndirizzoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;
import java.util.UUID;
@Service
public class IndirizzoService {
    @Autowired
    private static IndirizzoRepository indirizzoRepository;
    @Autowired
    private ComuneService comuneService;

    @Autowired
    private Cloudinary cloudinaryUploader;

    public Page<Indirizzo> findAll(int pageNumber, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return indirizzoRepository.findAll(pageable);
    }

    public Indirizzo findById(long id) {
        return indirizzoRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(long id) {
        Indirizzo found = findById(id);
        indirizzoRepository.delete(found);
    }

    public Indirizzo save(NewIndirizzoDTO body) {

        Comune comune = comuneService.findById(body.idcomune());

        Indirizzo newIndirizzo = new Indirizzo();
        newIndirizzo.setVia(body.via());
        newIndirizzo.setCivico(body.civico());
        newIndirizzo.setLocalità(body.località());
        newIndirizzo.setCAP(body.CAP());
        newIndirizzo.setComune(comune);

        return indirizzoRepository.save(newIndirizzo);
    }

    public Page<Indirizzo> getIndirizzi(int page, int size, String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return indirizzoRepository.findAll(pageable);
    }


   public Indirizzo findByIdAndUpdate(long id, Indirizzo body) {

        Indirizzo found = findById(id);
        found.setVia(body.getVia());
        found.setCivico(body.getCivico());
        found.setLocalità(body.getLocalità());
        found.setComune(body.getComune());
        return indirizzoRepository.save(found);
    }

}