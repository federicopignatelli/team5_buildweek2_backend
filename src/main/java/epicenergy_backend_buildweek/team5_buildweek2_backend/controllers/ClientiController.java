package epicenergy_backend_buildweek.team5_buildweek2_backend.controllers;

import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Cliente;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Fattura;
import epicenergy_backend_buildweek.team5_buildweek2_backend.exceptions.BadRequestException;
import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.clienti.NewClienteDTOIdIndirizzo;
import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.clienti.NewClienteDTOIdIndirizzoResponse;
import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.clienti.NewClienteDTOIndirizzoCompleto;
import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.clienti.UpdateClienteDTO;
import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.indirizzo.NewIndirizzoDTO;
import epicenergy_backend_buildweek.team5_buildweek2_backend.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clienti")
public class ClientiController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public NewClienteDTOIdIndirizzoResponse save(@RequestBody @Validated NewClienteDTOIdIndirizzo body, BindingResult validation) throws Exception {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors().toString());
        }
        Cliente newCliente = clienteService.save(body);
        return new NewClienteDTOIdIndirizzoResponse(
                newCliente.getRagioneSociale(),
                newCliente.getEmailAziendale(),
                newCliente.getIndirizzoSedeOperativa().getId()
        );
    }
    @PostMapping("/createWithAddress")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public NewClienteDTOIdIndirizzoResponse saveWithAddress(@RequestBody @Validated NewClienteDTOIndirizzoCompleto body, BindingResult validation) throws Exception {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors().toString());
        }
        Cliente newCliente = clienteService.saveWithAddress(body);
        return new NewClienteDTOIdIndirizzoResponse(
                newCliente.getRagioneSociale(),
                newCliente.getEmailAziendale(),
                newCliente.getIndirizzoSedeOperativa().getId()
        );
    }

    @GetMapping("")
    public Page<Cliente> findAll(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String sortBy) {
        return clienteService.findAll(page, size, sortBy);
    }

    @GetMapping("/{id}")
    public Cliente findByPartitaIva(@PathVariable UUID id){
        return clienteService.findByPartitaIva(id);
    }

    @GetMapping("/byfatturato")
    public List<Cliente> getClienteByFatturatoAnnuale(@RequestParam int fatturato){
        return this.clienteService.getClienteByFatturatoAnnuale(fatturato);
    }

    @GetMapping("/byragionesociale")
    public Cliente getClienteByRagioneSociale(@RequestParam String ragioneSociale){
        return this.clienteService.findByRagioneSociale(ragioneSociale);
    }

    @GetMapping("/bydatainserimento")
    public List<Cliente> getClienteByDataInserimento(@RequestParam LocalDate date){
        return this.clienteService.getClienteByDataInserimento(date);
    }

    @GetMapping("/bydataultimocontatto")
    public List<Cliente> getClienteByDataUltimoContatto(@RequestParam LocalDate date){
        return this.clienteService.getClienteByUtimoContatto(date);
    }

    @GetMapping("/bypartragionesociale")
    public List<Cliente> getClienteByPartRagioneSociale(@RequestParam String ragioneSociale){
        return this.clienteService.getClienteByPartRagioneSociale(ragioneSociale);
    }

    @PutMapping("/{id}")
    public Cliente findByPartitaIvaAndUpdate(@PathVariable UUID id, @RequestBody UpdateClienteDTO body) {
        return clienteService.findByPartitaIvaAndUpdate(body, id);
    }
    @PutMapping("/{id}")
    public Cliente findByPartitaIvaAndUpdateDataUltimoContatto(@PathVariable UUID id, @RequestBody LocalDate date){
        return this.clienteService.updateDataUltimoContatto(id, date);
    }


    @PutMapping("/{id}/UpdateIndirizzoSedeLegale")
    public Cliente findByPartitaIvaAndUpdateIndirizzoSedeLegale(@PathVariable UUID id, @RequestBody NewIndirizzoDTO body) {
        return clienteService.findByPartitaIvaAndUpdateIndirizzoSedeLegale(body, id);
    }

    @PutMapping("/{id}/UpdateIndirizzoSedeOperativa")
    public Cliente findByPartitaIvaAndUpdateIndirizzoSedeOperativa(@PathVariable UUID id, @RequestBody NewIndirizzoDTO body) {
        return clienteService.findByPartitaIvaAndUpdateIndirizzoSedeOperativa(body, id);
    }

    @PostMapping("/{id}/upload")
    public String uploadLogoAziendale(@RequestParam("logoAziendale") MultipartFile file, @PathVariable UUID id) throws IOException, IOException {
        return clienteService.uploadLogoAziendale(file);
    }

}
