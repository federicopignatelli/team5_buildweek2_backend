package epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewUserDTO(
        @NotEmpty(message = "Il campo nome non può essre vuoto")
        @Size(min = 3, max = 30, message = "Il nome deve essere compreso tra 3 e 30 caratteri!")
        String name,
        @NotEmpty(message = "Il campo nome non può essre vuoto")
        @Size(min = 3, max = 30, message = "Il nome deve essere compreso tra 3 e 30 caratteri!")
        String surname,
        @NotEmpty(message = "Il campo nome non può essre vuoto")
        @Size(min = 3, max = 30, message = "Il nome deve essere compreso tra 3 e 15 caratteri!")
        String username,
        @Email
        String email,
        @NotEmpty(message = "La password è un campo obbligatorio!")
        @Size(min = 4, message = "La password deve avere minimo 4 caratteri!")
        String password,
        String role
) {
}
