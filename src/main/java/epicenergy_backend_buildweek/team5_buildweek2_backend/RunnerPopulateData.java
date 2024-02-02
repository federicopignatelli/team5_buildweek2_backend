package epicenergy_backend_buildweek.team5_buildweek2_backend;

import epicenergy_backend_buildweek.team5_buildweek2_backend.csvData.PopulateData;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Comune;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Provincia;
import epicenergy_backend_buildweek.team5_buildweek2_backend.repositories.ProvinciaRepository;
import epicenergy_backend_buildweek.team5_buildweek2_backend.services.ComuneService;
import epicenergy_backend_buildweek.team5_buildweek2_backend.services.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class RunnerPopulateData implements CommandLineRunner {

    @Autowired
    private PopulateData populateData;

    @Autowired
    private ProvinciaService provinciaService;

    @Autowired
    private ComuneService comuneService;

    @Override
    public void run(String... args) throws Exception {

        Path pathComuni = Paths.get("src/main/java/epicenergy_backend_buildweek/team5_buildweek2_backend/csvData/comuni-italiani.csv");
        Path pathProvince = Paths.get("src/main/java/epicenergy_backend_buildweek/team5_buildweek2_backend/csvData/province-italiane.csv");

    //    populateData.readAllLines(pathProvince);
     //   populateData.readAllLines(pathComuni);

//        List<Comune> comuni = comuneService.findByProvincia("Torino");
//        comuni.forEach(System.out::println);


    }
}
