package epicenergy_backend_buildweek.team5_buildweek2_backend.services;

import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Provincia;
import epicenergy_backend_buildweek.team5_buildweek2_backend.repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinciaService {

    @Autowired
    private ProvinciaRepository provinciaRepository;

//    public boolean checkMultipleProvince(String provincia) {
//        List<Provincia> provinciaList = provinciaRepository.findByFirstFourLetters(provincia);
////        return provinciaList.size() == 1;
////        return true;
//    }

}
