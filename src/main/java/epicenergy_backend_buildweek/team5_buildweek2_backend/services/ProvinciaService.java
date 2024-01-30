package epicenergy_backend_buildweek.team5_buildweek2_backend.services;

import org.springframework.stereotype.Service;
import java.util.List;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Indirizzo;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Provincia;
import epicenergy_backend_buildweek.team5_buildweek2_backend.exceptions.NotFoundException;
import epicenergy_backend_buildweek.team5_buildweek2_backend.repositories.IndirizzoRepository;
import epicenergy_backend_buildweek.team5_buildweek2_backend.repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class ProvinciaService {
    @Autowired
    private ProvinciaRepository provinciaRepository;
    public Page<Provincia> findAll(int pageNumber, int size, String orderBy){
        if(size>100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber,size, Sort.by(orderBy));
        return this.provinciaRepository.findAll(pageable);
    }
    public Provincia findBySigla(String sigla){
        return this.provinciaRepository.findById(sigla).orElseThrow(()->new NotFoundException(sigla));
    }

    public void findBySiglaAndDelete(String sigla) {
        Provincia found = this.findBySigla(sigla);
        provinciaRepository.delete(found);
    }
}
