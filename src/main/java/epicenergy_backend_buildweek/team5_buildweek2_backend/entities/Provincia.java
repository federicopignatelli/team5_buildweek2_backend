package epicenergy_backend_buildweek.team5_buildweek2_backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Provincia {
    @Id
    private String sigla;
    private String nome;
    private String regione;
}
