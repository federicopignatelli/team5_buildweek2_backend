package epicenergy_backend_buildweek.team5_buildweek2_backend.controllers;

import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Cliente;
import epicenergy_backend_buildweek.team5_buildweek2_backend.exceptions.BadRequestException;
import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.clienti.NewClienteDTOIdIndirizzo;
import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.clienti.NewClienteDTOIdIndirizzoResponse;
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
        //inserire nella response anche l'id dell'indirizzo
        return new NewClienteDTOIdIndirizzoResponse(newCliente.getRagioneSociale(), newCliente.getEmailAziendale());
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

    @PutMapping("/{id}")
    public Cliente findByPartitaIvaAndUpdate(@PathVariable UUID id, @RequestBody UpdateClienteDTO body) {
        return clienteService.findByPartitaIvaAndUpdate(body, id);
    }

    @PutMapping("/UpdateIndirizzoSedeLegale/{id}")
    public Cliente findByPartitaIvaAndUpdateIndirizzoSedeLegale(@PathVariable UUID id, @RequestBody NewIndirizzoDTO body) {
        return clienteService.findByPartitaIvaAndUpdateIndirizzoSedeLegale(body, id);
    }

    @PutMapping("/UpdateIndirizzoSedeLegale/{id}")
    public Cliente findByPartitaIvaAndUpdateIndirizzoSedeOperativa(@PathVariable UUID id, @RequestBody NewIndirizzoDTO body) {
        return clienteService.findByPartitaIvaAndUpdateIndirizzoSedeOperativa(body, id);
    }


}
