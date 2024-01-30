package epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.clienti;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
public record NewClienteDTOIndirizzoCompleto(
        @NotEmpty(message = "Ragione sociale è un campo obbligatorio")
        @Size(min = 3,message = "Minimo 3 caratteri per la ragione sociale")
        String ragioneSociale,
        @Email(message = "Inserisci un indirizzo e-mail valido")
        @NotEmpty(message = "Email aziendale è un campo obbligatorio")
        String emailAziendale,
        @NotEmpty
        @Size(min = 3,message = "Minimo 3 caratteri per la via")
        String via,
        int civico,
        String località,
        @NotEmpty
        @Size(min = 5, max = 5, message = "Il CAP deve avere esattamente 5 caratteri")
        @Pattern(regexp = "\\d{5}", message = "Il CAP deve essere composto da 5 caratteri numerici")
        String CAP,
        String comune

) {
}