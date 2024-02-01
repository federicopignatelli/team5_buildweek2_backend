package epicenergy_backend_buildweek.team5_buildweek2_backend.controllers;

import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Comune;
import epicenergy_backend_buildweek.team5_buildweek2_backend.services.ComuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// only ADMINs can register
@RestController
@RequestMapping("/comuni")
public class ComuneController {
    @Autowired
    private ComuneService comuneService;

    @GetMapping("")
    public Page<Comune> getComuni(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String orderBy) {
        return comuneService.getComuni(page, size, orderBy);
    }

    @GetMapping("/{comune}")
    public List<Comune> getByNomeComune(@PathVariable String comune) {
        return comuneService.findByName(comune.substring(0, 1).toUpperCase() + comune.substring(1));
    }
}
