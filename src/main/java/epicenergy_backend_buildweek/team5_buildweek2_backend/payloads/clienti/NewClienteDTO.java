package epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.clienti;

import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Indirizzo;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.enums.TipoAzienda;
import epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.indirizzo.NewIndirizzoDTO;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.net.URL;
import java.time.LocalDate;
import java.util.UUID;



public record NewClienteDTO(
        NewIndirizzoDTO addressSedeLegale,
        NewIndirizzoDTO addressSedeOperativo,
        String nomeContatto,
        Long telefonoContatto,
        String emailContatto,
        String ragioneSociale,
        String emailAziendale,
        String pecAziendale,
        Long telefonoAziendale,

        // better to be a url
        String urlLogoAziendale,
        String tipoAziendale,
        Integer fatturatoAnnuale
) {
}