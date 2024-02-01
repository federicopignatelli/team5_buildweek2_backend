package epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.clienti;

import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Indirizzo;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.enums.TipoAzienda;

import java.time.LocalDate;

public record UpdateClienteDTO(
        String nomeContatto,
        Long telefonoContatto,
        String emailContatto,
        String ragioneSociale,
        String emailAziendale,
        String pecAziendale,
        Long telefonoAziendale,
      //  Indirizzo indirizzoSedeOperativa,
      //  Indirizzo indirizzoSedeLegale,
      //  String urlLogoAziendale,
      //  TipoAzienda tipo,
        String tipo,
      //  LocalDate dataInserimento,
      //  LocalDate dataUltimoContatto,
        Integer fatturatoAnnuale
) {
}
