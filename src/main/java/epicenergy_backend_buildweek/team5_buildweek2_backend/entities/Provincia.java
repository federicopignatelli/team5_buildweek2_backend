package epicenergy_backend_buildweek.team5_buildweek2_backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "province")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Provincia {
    @Id
    private String sigla;
    private String provincia;
    private String regine;
}
