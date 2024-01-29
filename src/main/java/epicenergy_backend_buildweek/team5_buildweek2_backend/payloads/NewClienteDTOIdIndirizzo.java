package epicenergy_backend_buildweek.team5_buildweek2_backend.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewClienteDTOIdIndirizzo(
        @NotEmpty(message = "Ragione sociale è un campo obbligatorio")
        @Size(min = 3,message = "Minimo 3 caratteri per la ragione sociale")
        String ragioneSociale,
        @Email(message = "Inserisci un indirizzo e-mail valido")
        @NotEmpty(message = "Email aziendale è un campo obbligatorio")
        String emailAziendale,
        @NotEmpty
        long idIndirizzo
) {
}
