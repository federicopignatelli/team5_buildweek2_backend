package epicenergy_backend_buildweek.team5_buildweek2_backend.payloads.users;

import java.util.UUID;

public record UserLoginResponseDTO(
        String token,
        UUID id,
        String name,
        String role,
        String surname,
        String username,
        String avatar
        ) {
}
