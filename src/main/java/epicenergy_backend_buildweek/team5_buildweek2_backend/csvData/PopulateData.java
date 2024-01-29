package epicenergy_backend_buildweek.team5_buildweek2_backend.csvData;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class PopulateData {
    private String filePath = "./comuni-italiani.csv";

    public static void readAllLines(Path filePath) throws Exception {

        try (Reader reader = Files.newBufferedReader(filePath)) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                String[] line;
                while ((line = csvReader.readNext()) != null) {
                    System.out.println(line.toString());
                }
            }
        }
    }
}
