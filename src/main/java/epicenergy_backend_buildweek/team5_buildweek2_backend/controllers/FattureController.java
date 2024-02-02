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

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/fatture")
public class FattureController {
    @Autowired
    private FatturaService fatturaService;
    @GetMapping
    public Page<FatturaResponseDTO> findAll(@RequestParam(defaultValue = "0") int pageNumber,
                                            @RequestParam(defaultValue = "10") int size,
                                            @RequestParam(defaultValue = "numero") String orderBy){
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
    @PutMapping("/{numeroFattura}/setPaid")
    @PreAuthorize("hasAuthority('ADMIN')")
    public FatturaResponseDTO setPaid(@PathVariable long numeroFattura){
        return this.fatturaService.setPaid(numeroFattura);
    }
    @PutMapping("/{numeroFattura}/setCustomState")
    @PreAuthorize("hasAuthority('ADMIN')")
    public FatturaResponseDTO setCustomState(@PathVariable long numeroFattura,@RequestParam String customState){
        return this.fatturaService.setCustomState(numeroFattura,customState);
    }
    @DeleteMapping("/{numeroFattura}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteByNumber(@PathVariable long numeroFattura){
        this.fatturaService.findByNumeroAndDelete(numeroFattura);
    }

    @GetMapping("/byDate")
    public List<Fattura> getFattureByDate(@RequestParam LocalDate date){
            return this.fatturaService.getFattureByDate(date);
    }
    @GetMapping("/byRangeOfDates")
    public List<Fattura> getFattureByRangeOfDates(@RequestParam(required = false) LocalDate startDate,
                                                  @RequestParam(required = false) LocalDate endDate){
        return this.fatturaService.getFattureByRangeOfDates(startDate, endDate);
    }
    @GetMapping("/byYear")
    public List<Fattura> getFattureByYear(@RequestParam int year){
        return this.fatturaService.getFattureByYear(year);
    }

    @GetMapping("/fattureRangeImporti")
    public List<FatturaResponseDTO> getFattureByRangeImporti(
            @RequestParam(required = false) Double minImporto,
            @RequestParam(required = false) Double maxImporto) {
        List<Fattura> listaDiFatture = this.fatturaService.getFattureByRangeImporti(minImporto,maxImporto);
        List<FatturaResponseDTO> returnList = listaDiFatture.stream().map(fattura -> new FatturaResponseDTO(
                fattura.getNumero(),
                fattura.getDataEmissione(),
                fattura.getImporto(),
                fattura.getCliente().getPartitaIva(),
                fattura.getCliente().getRagioneSociale(),
                fattura.getStato().toString()
        )).toList();
        return returnList;
    }

    @GetMapping("/fatturePIcliente")
    public List<Fattura> getFattureByPICliente(@RequestParam UUID piCliente) {
        return fatturaService.getFattureByPartitaIvaCliente(piCliente);
    }
    @GetMapping("/byRagioneSociale")
    public List<Fattura> getFattureByRSCliente(@RequestParam String ragioneSocialeCliente){
        return fatturaService.getFattureByRagioneSocialCliente(ragioneSocialeCliente);
    }
    @GetMapping("/byStato")
    public List<Fattura> getFattureByStato(@RequestParam String stato){
        return this.fatturaService.getFattureByStato(stato);
    }
}
