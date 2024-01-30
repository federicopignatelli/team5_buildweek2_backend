package epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.indirizzo;

import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Comune;

public record NewIndirizzoResponseDTO(String via, int civico, String località, String CAP, Comune comune) {
}
