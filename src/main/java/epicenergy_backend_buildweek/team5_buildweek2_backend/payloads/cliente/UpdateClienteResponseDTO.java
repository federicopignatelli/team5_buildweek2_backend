package epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.cliente;

import java.util.UUID;

public record UpdateClienteResponseDTO(UUID id, String nomeContatto, String emailContatto) {
}
