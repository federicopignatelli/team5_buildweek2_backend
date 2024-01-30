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

                // skips the header(first line)
                csvReader.skip(1);

                // loop entire csv file
                String[] line;

                // progressivi_di_comune used to fix progressivo del comune di Sassari
                int progressivi_di_comune = 0;
                while ((line = csvReader.readNext()) != null) {
                    // save province
                    if(line.length == 3) {
                        // save sud sardegna
                        Provincia sudSardegna = new Provincia("SU", "Sud Sardegna", "Sardegna");
                        provinciaRepository.save(sudSardegna);

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
                        List<Provincia> provinciaList = provinciaRepository.findByLikeProvincia(line[3]);

                        // if list is empty let's try to use another query method


                        if(provinciaList.isEmpty()) {
                            System.out.println("I passed here!");
                            provinciaList = provinciaRepository.findByFirstFourLetters(line[3]);
                            System.out.println("This is handled reagions: " + provinciaList);
                        }


                        // if we find multiple provinces
                        if(provinciaList.size() > 1) {
                            System.out.println("this is list: " + provinciaList + " check this: " + line[3]);

                            // throw exception if necessary
                        }

                        // saves comune without any problem

                        if(!provinciaList.isEmpty()) {
                            Comune comune = new Comune();
                            comune.setCodiceProvincia(Integer.parseInt(line[0]));
                            comune.setProvincia(provinciaList.get(0));
                            try {
                                comune.setProgressiviDelComune(Integer.parseInt(line[1]));
                            } catch (NumberFormatException ex) {
                                comune.setProgressiviDelComune(progressivi_di_comune);
                                System.out.println(comune.getProgressiviDelComune() + " comune: " + comune.getCodiceProvincia() + progressivi_di_comune);
                                progressivi_di_comune++;
                            }


                            comune.setDenominazioneInItaliano(line[2]);
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

                    /*
                        line[3]                             | provincia | regione(region isn't unique)
                        ------------------------------------------------------------
                        Verbano-Cusio-Ossola                | Verbania | Piemonte
                        Valle d'Aosta/Vallée d'Aoste        | Aosta | Valle d'Aosta
                        Monza e della Brianza               | Monza-Brianza | Lombardia
                        Bolzano/Bozen                       | Bolzano | Trentino Alto Adige
                        La Spezia                           | La-Spezia | Liguria
                        Reggio nell'Emilia                  | Reggio-Emilia | Emilia Romagna
                        Forlì-Cesena                        | Forli-Cesena | Emilia Romagna
                        Pesaro e Urbino                     | Pesaro-Urbino | Marche
                        Ascoli Piceno                       | Ascoli-Piceno | Marche
                        Reggio Calabria                     | Reggio-Calabria | Calabria
                        Vibo Valentia                       | Vibo-Valentia | Calabria
                        */
