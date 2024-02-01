package epicenergy_backend_buildweek.team5_buildweek2_backend.controllers;

import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Indirizzo;
import epicenergy_backend_buildweek.team5_buildweek2_backend.exceptions.BadRequestException;
import epicenergy_backend_buildweek.team5_buildweek2_backend.exceptions.NotFoundException;
import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.indirizzo.NewIndirizzoDTO;
import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.indirizzo.NewIndirizzoResponseDTO;
import epicenergy_backend_buildweek.team5_buildweek2_backend.repositories.IndirizzoRepository;
import epicenergy_backend_buildweek.team5_buildweek2_backend.services.IndirizzoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/indirizzi")
public class IndirizzoController {

    @Autowired
    private IndirizzoService indirizzoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewIndirizzoResponseDTO saveIndirizzo(@RequestBody @Validated NewIndirizzoDTO body, BindingResult validation) throws Exception {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        Indirizzo newIndirizzo = indirizzoService.save(body);
        return new NewIndirizzoResponseDTO(newIndirizzo.getId());
    }
    @GetMapping
    public Page<Indirizzo> getIndirizzi(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return indirizzoService.getIndirizzi(page, size, sortBy);
    }

    @GetMapping("/{indirizzoId}")
    public Indirizzo findById(@PathVariable int indirizzoId) {
        return indirizzoService.findById(indirizzoId);
    }

   @PutMapping("/{indirizzoId}")
    public Indirizzo findAndUpdate(@PathVariable int indirizzoId, @RequestBody Indirizzo body) {
        return indirizzoService.findByIdAndUpdate(indirizzoId, body);
    }

    @DeleteMapping("/{indirizzoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable int indirizzoId) {
        indirizzoService.findByIdAndDelete(indirizzoId);
    }
}
