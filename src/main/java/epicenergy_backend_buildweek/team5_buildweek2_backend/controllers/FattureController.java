package epicenergy_backend_buildweek.team5_buildweek2_backend.controllers;

import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.fatture.FatturaResponseDTO;
import epicenergy_backend_buildweek.team5_buildweek2_backend.services.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fatture")
public class FattureController {
    @Autowired
    private FatturaService fatturaService;
    @GetMapping
    public Page<FatturaResponseDTO> findAll(@RequestParam int pageNumber, @RequestParam int size, @RequestParam String orderBy){
        return this.fatturaService.findAll(pageNumber, size, orderBy);
    }
}
