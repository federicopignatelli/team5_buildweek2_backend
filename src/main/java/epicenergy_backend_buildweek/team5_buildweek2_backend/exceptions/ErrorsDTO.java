package epicenergy_backend_buildweek.team5_buildweek2_backend.exceptions;

import java.time.LocalDateTime;

public record ErrorsDTO(String message, LocalDateTime timestamp) {
}
