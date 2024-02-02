package epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.fatture;

import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.StatoFattura;

import java.time.LocalDate;
import java.util.UUID;

public record FatturaResponseDTO(long numeroFattura,
                                 LocalDate dataEmissione,
                                 double importo,
                                 UUID partitaIvaCliente,
                                 String ragioneSociale,
                                 String stato) {
}
