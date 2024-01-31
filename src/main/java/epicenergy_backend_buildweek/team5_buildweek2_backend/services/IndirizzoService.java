package epicenergy_backend_buildweek.team5_buildweek2_backend.services;

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
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class IndirizzoService {
    @Autowired
    private IndirizzoRepository indirizzoRepository;
    @Autowired
    private ComuneService comuneService;

    public Page<Indirizzo> findAll(int pageNumber, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return this.indirizzoRepository.findAll(pageable);
    }

    public Indirizzo findById(long id) {
        return this.indirizzoRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(long id) {
        Indirizzo found = this.findById(id);
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
}

