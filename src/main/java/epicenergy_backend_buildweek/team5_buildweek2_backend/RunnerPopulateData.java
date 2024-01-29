package epicenergy_backend_buildweek.team5_buildweek2_backend;

import epicenergy_backend_buildweek.team5_buildweek2_backend.csvData.PopulateData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class RunnerPopulateData implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("hello isuru!");

//        PopulateData data = new PopulateData();
//        data.readAllLines(Paths.get("comuni-italiani.csv"));

        Path path = Paths.get("src/main/java/epicenergy_backend_buildweek/team5_buildweek2_backend/csvData/comuni-italiani.csv");

        if(Files.exists(path)) {
            System.out.println("file found");
        } else  {
            System.out.println("file not found");
        }

        PopulateData.readAllLines(path);

    }
}
