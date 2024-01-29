package epicenergy_backend_buildweek.team5_buildweek2_backend;

import epicenergy_backend_buildweek.team5_buildweek2_backend.csvData.PopulateData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RunnerPopulateData implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("hello isuru!");

        PopulateData.readAllLines();
    }
}
