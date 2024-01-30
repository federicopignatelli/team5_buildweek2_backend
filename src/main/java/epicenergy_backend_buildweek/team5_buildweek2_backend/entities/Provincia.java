package epicenergy_backend_buildweek.team5_buildweek2_backend.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

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

    public Provincia(String sigla, String provincia, String regine) {
        this.sigla = sigla;
        this.provincia = provincia;
        this.regine = regine;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Provincia{");
        sb.append("sigla='").append(sigla).append('\'');
        sb.append(", provincia='").append(provincia).append('\'');
        sb.append(", regine='").append(regine).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
