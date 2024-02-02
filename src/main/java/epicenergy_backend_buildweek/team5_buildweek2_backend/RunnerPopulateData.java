package epicenergy_backend_buildweek.team5_buildweek2_backend;

import epicenergy_backend_buildweek.team5_buildweek2_backend.csvData.PopulateData;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Cliente;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Comune;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Provincia;
import epicenergy_backend_buildweek.team5_buildweek2_backend.repositories.IndirizzoRepository;
import epicenergy_backend_buildweek.team5_buildweek2_backend.repositories.ProvinciaRepository;
import epicenergy_backend_buildweek.team5_buildweek2_backend.services.ClienteService;
import epicenergy_backend_buildweek.team5_buildweek2_backend.services.ComuneService;
import epicenergy_backend_buildweek.team5_buildweek2_backend.services.IndirizzoService;
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

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private IndirizzoService indirizzoService;

    @Override
    public void run(String... args) throws Exception {

        Path pathComuni = Paths.get("src/main/java/epicenergy_backend_buildweek/team5_buildweek2_backend/csvData/comuni-italiani.csv");
        Path pathProvince = Paths.get("src/main/java/epicenergy_backend_buildweek/team5_buildweek2_backend/csvData/province-italiane.csv");

        populateData.readAllLines(pathProvince);
        populateData.readAllLines(pathComuni);

//        List<Comune> comuni = comuneService.findByProvincia("Torino");
//        comuni.forEach(System.out::println);

        Cliente cliente0 = new Cliente("Marco Polo",545234234L, "ciaobello@gmail.com", "negozietto", "negoziettogmail.com" );
        Cliente cliente1 = new Cliente("Alice Wonderland", 1234567890L, "alice@gmail.com", "Via Felice", "Città Sognante");
        Cliente cliente2 = new Cliente("Bob Marley", 9876543210L, "bob@yahoo.com", "Via Reggae", "Sunny City");
        Cliente cliente3 = new Cliente("Charlie Chaplin", 5551234567L, "charlie@hotmail.com", "Via Comica", "Laughing Town");
        Cliente cliente4 = new Cliente("Diana Prince", 9998887770L, "diana@example.com", "Paradise Street", "Wonderland");
        Cliente cliente5 = new Cliente("Edward Scissorhands", 4567890123L, "edward@gmail.com", "Blade Avenue", "Cuttingville");
        Cliente cliente6 = new Cliente("Fiona Shrek", 6789012345L, "fiona@yahoo.com", "Swamp Lane", "Far Far Away");
        Cliente cliente7 = new Cliente("George Orwell", 1112223334L, "george@hotmail.com", "Dystopia Street", "Oceania");
        Cliente cliente8 = new Cliente("Hermione Granger", 4445556667L, "hermione@example.com", "Wizardry Lane", "Magic Town");
        Cliente cliente9 = new Cliente("Indiana Jones", 8889990001L, "indy@gmail.com", "Adventure Avenue", "Archaeology City");
        Cliente cliente10 = new Cliente("Jack Sparrow", 2223334445L, "jack@yahoo.com", "Pirate Street", "Tortuga");
        clienteService.savedatarunner(cliente0);
        clienteService.savedatarunner(cliente1);
        clienteService.savedatarunner(cliente2);
        clienteService.savedatarunner(cliente3);
        clienteService.savedatarunner(cliente4);
        clienteService.savedatarunner(cliente5);
        clienteService.savedatarunner(cliente6);
        clienteService.savedatarunner(cliente7);
        clienteService.savedatarunner(cliente8);
        clienteService.savedatarunner(cliente9);
        clienteService.savedatarunner(cliente10);







    }
}
