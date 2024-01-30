package epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.indirizzo;

import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Comune;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewIndirizzoDTO(
        @NotEmpty
        String via,
        @NotEmpty
        int civico,
        @NotEmpty
        String località,
        @NotEmpty
        @Size(min = 5, max = 5, message = "il CAP dev'essere composto da 5 cifre")
        String CAP,
        Long idcomune
) {
}
