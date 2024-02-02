package epicenergy_backend_buildweek.team5_buildweek2_backend.services;

import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Cliente;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.StatoFattura;
import epicenergy_backend_buildweek.team5_buildweek2_backend.exceptions.NotFoundException;
import epicenergy_backend_buildweek.team5_buildweek2_backend.repositories.ClienteRepository;
import epicenergy_backend_buildweek.team5_buildweek2_backend.repositories.StatoFatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StatoFatturaService {
    @Autowired
    private StatoFatturaRepository statoFatturaRepository;
    public Page<StatoFattura> findAll(int pageNumber, int size, String orderBy){
        if(size>100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber,size, Sort.by(orderBy));
        return this.statoFatturaRepository.findAll(pageable);
    }
    public StatoFattura findByStato(String stato){
        return this.statoFatturaRepository.findById(stato).orElseThrow(()->new NotFoundException(stato));
    }
    public StatoFattura save(String stato){
        return this.statoFatturaRepository.save(new StatoFattura(stato));
    }

    public void findByStatoAndDelete(String stato) {
        StatoFattura found = this.findByStato(stato);
        statoFatturaRepository.delete(found);
    }
}
