package epicenergy_backend_buildweek.team5_buildweek2_backend.controllers;

import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Fattura;
import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.fatture.FatturaResponseDTO;
import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.fatture.NewFatturaDTO;
import epicenergy_backend_buildweek.team5_buildweek2_backend.services.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PreAuthorize("hasAuthority('ADMIN')")
    public FatturaResponseDTO createNewBill(@RequestBody NewFatturaDTO body){
        return this.fatturaService.save(body);
    }
    @DeleteMapping("/{numeroFattura}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteByNumber(@PathVariable long numeroFattura){
        this.fatturaService.findByNumeroAndDelete(numeroFattura);
    }

    @GetMapping("/fattureRangeImporti")
    public List<Fattura> getFattureByRangeImporti(
            @RequestParam("minImporto") double minImporto,
            @RequestParam("maxImporto") double maxImporto) {

        List<Fattura> fattureFiltratePerImporto = fatturaService.getFattureByRangeImporti(minImporto, maxImporto);
        return fattureFiltratePerImporto;
    }

    @GetMapping("/fattureClienteId")
    public List<Fattura> getFattureByCliente(@RequestParam("clienteId") Long clienteId) {
        List<Fattura> fattureFiltratePerCliente = fatturaService.getFattureByCliente(clienteId);
        return fattureFiltratePerCliente;
    }
}
