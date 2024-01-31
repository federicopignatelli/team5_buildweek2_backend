package epicenergy_backend_buildweek.team5_buildweek2_backend.controllers;

import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Comune;
import epicenergy_backend_buildweek.team5_buildweek2_backend.services.ComuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// only ADMINs can register
@RestController
@RequestMapping("/comune")
public class ComuneController {
    @Autowired
    private ComuneService comuneService;

    @GetMapping("")
    public Page<Comune> getComuni(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String orderBy) {
        return comuneService.getComuni(page, size, orderBy);
    }
}
