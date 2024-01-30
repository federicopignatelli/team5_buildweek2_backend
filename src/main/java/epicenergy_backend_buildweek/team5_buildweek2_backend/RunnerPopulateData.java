package epicenergy_backend_buildweek.team5_buildweek2_backend;

import epicenergy_backend_buildweek.team5_buildweek2_backend.csvData.PopulateData;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Provincia;
import epicenergy_backend_buildweek.team5_buildweek2_backend.repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class RunnerPopulateData implements CommandLineRunner {

    @Autowired
    private PopulateData populateData;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("hello isuru!");

        Path pathComuni = Paths.get("src/main/java/epicenergy_backend_buildweek/team5_buildweek2_backend/csvData/comuni-italiani.csv");
        Path pathComuniMini = Paths.get("src/main/java/epicenergy_backend_buildweek/team5_buildweek2_backend/csvData/comuni-italiani-mini.csv");
        Path pathProvince = Paths.get("src/main/java/epicenergy_backend_buildweek/team5_buildweek2_backend/csvData/province-italiane.csv");
        Path pathProvinceMini = Paths.get("src/main/java/epicenergy_backend_buildweek/team5_buildweek2_backend/csvData/province-italiane-mini.csv");

//        if(Files.exists(pathProvince)) {
//            System.out.println("file found");
//        } else  {
//            System.out.println("file not found");
//        }

        populateData.readAllLines(pathProvince);
        populateData.readAllLines(pathComuni);



    }
}
