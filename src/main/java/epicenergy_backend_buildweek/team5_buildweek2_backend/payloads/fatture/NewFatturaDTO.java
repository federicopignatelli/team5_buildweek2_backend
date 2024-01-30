package epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.fatture;

import java.util.UUID;

public record NewFatturaDTO(double importo, UUID idCliente) {
}