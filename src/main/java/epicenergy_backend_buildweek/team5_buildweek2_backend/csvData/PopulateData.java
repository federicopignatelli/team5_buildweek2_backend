package epicenergy_backend_buildweek.team5_buildweek2_backend.csvData;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Provincia;
import epicenergy_backend_buildweek.team5_buildweek2_backend.repositories.ComuneRepository;
import epicenergy_backend_buildweek.team5_buildweek2_backend.repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class PopulateData {
    private String filePath = "./comuni-italiani.csv";

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Autowired
    private ComuneRepository comuneRepository;

    public void readAllLines(Path filePath) throws Exception {


        try (Reader reader = Files.newBufferedReader(filePath)) {

            // convert to an array structure, so we can get data by position
            // parser separates each value with ';'
            CSVParser parser = new CSVParserBuilder()
                    .withSeparator(';')
                    .build();
            try (CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withCSVParser(parser)
                    .build()) {

                // loop entire csv file
                String[] line;
                while ((line = csvReader.readNext()) != null) {
                    // save province
                    if(line.length == 3) {
                        Provincia provincia = new Provincia();
                        provincia.setSigla(line[0]);
                        provincia.setProvincia(line[1]);
                        provincia.setRegine(line[2]);
                        provinciaRepository.save(provincia);
                    }
                    // save comini
                    else if (line.length == 4) {
                        System.out.println("codice provincia: " + line[0] + " progressivo del comune: " + line[1] + " denominazione in Italia " + line[2] + " provincia " + line[3]);
                    } else {
                        // throw exception
                        System.out.println("out of request");
                    }
                }
            }
        }
    }

}
