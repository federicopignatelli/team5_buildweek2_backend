package epicenergy_backend_buildweek.team5_buildweek2_backend.csvData;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Comune;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Provincia;
import epicenergy_backend_buildweek.team5_buildweek2_backend.repositories.ComuneRepository;
import epicenergy_backend_buildweek.team5_buildweek2_backend.repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class PopulateData {

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Autowired
    private ComuneRepository comuneRepository;

    public void readAllLines(Path filePath) throws Exception {
    // we have to check if the database is empty before running the code
    // handle accurate exceptions

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
                    // save comuni
                    else if (line.length == 4) {
                        // checks whether if there are multiple provinces starts with the same name
                        // Because CSV relations are not well-defined
                        List<Provincia> provinciaList = provinciaRepository.findByFirstFourLetters(line[3]);
                        System.out.println("this is list: " + provinciaList + " check this: " + line[3]);
                        if(!provinciaList.isEmpty()) {
                            Comune comune = new Comune();
                            comune.setCodiceProvincia(Integer.parseInt(line[0]));
                            comune.setProgressiviDelComune(Integer.parseInt(line[1]));
                            comune.setDenominazioneInItaliano(line[2]);
                            comune.setProvincia(provinciaList.get(0));
                            comuneRepository.save(comune);
                        } else {
                            System.out.println("found out of rule " + line[3] + " lenght: " + line.length);
                        }
//                        System.out.println("codice provincia: " + line[0] + " progressivo del comune: " + line[1] + " denominazione in Italia " + line[2] + " provincia " + line[3]);
                    } else {
                        // throw exception
                        System.out.println("out of request");
                    }
                }
            }
        }
    }

}
