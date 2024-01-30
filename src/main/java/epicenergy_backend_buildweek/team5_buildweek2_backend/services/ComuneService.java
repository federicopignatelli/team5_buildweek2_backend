package epicenergy_backend_buildweek.team5_buildweek2_backend.services;

import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Cliente;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Comune;
import epicenergy_backend_buildweek.team5_buildweek2_backend.exceptions.NotFoundException;
import epicenergy_backend_buildweek.team5_buildweek2_backend.repositories.ClienteRepository;
import epicenergy_backend_buildweek.team5_buildweek2_backend.repositories.ComuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.UUID;

public class ComuneService {
    @Autowired
    private ComuneRepository comuneRepository;
    public Page<Comune> findAll(int pageNumber, int size, String orderBy){
        if(size>100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber,size, Sort.by(orderBy));
        return this.comuneRepository.findAll(pageable);
    }
    public Comune findById(long id){
        return this.comuneRepository.findById(id).orElseThrow(()->new NotFoundException(id));
    }

    public void findByIdAndDelete(long id) {
        Comune found = this.findById(id);
        comuneRepository.delete(found);
    }
}
