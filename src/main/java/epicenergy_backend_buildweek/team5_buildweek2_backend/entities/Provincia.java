package epicenergy_backend_buildweek.team5_buildweek2_backend.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Provincia {
    @Id
    private String sigla;
    private String provincia;
    private String regine;

    @OneToMany(mappedBy = "provincia", cascade = CascadeType.ALL)
    private List<Comune> comuneList;
}
