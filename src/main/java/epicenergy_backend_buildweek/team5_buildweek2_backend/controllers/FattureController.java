package epicenergy_backend_buildweek.team5_buildweek2_backend.controllers;

import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.fatture.FatturaResponseDTO;
import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.fatture.NewFatturaDTO;
import epicenergy_backend_buildweek.team5_buildweek2_backend.services.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fatture")
public class FattureController {
    @Autowired
    private FatturaService fatturaService;
    @GetMapping
    public Page<FatturaResponseDTO> findAll(@RequestParam int pageNumber,
                                            @RequestParam int size,
                                            @RequestParam String orderBy){
        return this.fatturaService.findAll(pageNumber, size, orderBy);
    }
    @GetMapping("/{numeroFattura}")
    public FatturaResponseDTO findByBillNumber(@PathVariable long numeroFattura){
        return this.fatturaService.findByNumero(numeroFattura);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FatturaResponseDTO createNewBill(@RequestBody NewFatturaDTO body){
        return this.fatturaService.save(body);
    }
    @DeleteMapping("/{numeroFattura}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByNumber(@PathVariable long numeroFattura){
        this.fatturaService.findByNumeroAndDelete(numeroFattura);
    }
}
