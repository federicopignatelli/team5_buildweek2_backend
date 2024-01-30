package epicenergy_backend_buildweek.team5_buildweek2_backend.services;

import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Cliente;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Fattura;
import epicenergy_backend_buildweek.team5_buildweek2_backend.exceptions.NotFoundException;
import epicenergy_backend_buildweek.team5_buildweek2_backend.repositories.ClienteRepository;
import epicenergy_backend_buildweek.team5_buildweek2_backend.repositories.FatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.UUID;

public class FatturaService {
    @Autowired
    private FatturaRepository fatturaRepository;
    public Page<Fattura> findAll(int pageNumber, int size, String orderBy){
        if(size>100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber,size, Sort.by(orderBy));
        return this.fatturaRepository.findAll(pageable);
    }
    public Fattura findByNumero(long numero){
        return this.fatturaRepository.findById(numero).orElseThrow(()->new NotFoundException(numero));
    }

    public void findByNumeroAndDelete(long numero) {
        Fattura found = this.findByNumero(numero);
        fatturaRepository.delete(found);
    }
}
