package epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.cliente;

import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Indirizzo;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.enums.TipoAzienda;

import java.time.LocalDate;

public record UpdateClienteDTO(
        String nomeContatto,
        long telefonoContatto,
        String emailContatto,
        String ragioneSociale,
        String emailAziendale,
        String pecAziendale,
        long telefonoAziendale,
        Indirizzo indirizzoSedeOperativa,
        Indirizzo indirizzoSedeLegale,
        String urlLogoAziendale,
        TipoAzienda tipo,
        LocalDate dataInserimento,
        LocalDate dataUltimoContatto,
        int fatturatoAnnuale
) {
}
