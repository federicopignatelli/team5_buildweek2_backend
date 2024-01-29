package epicenergy_backend_buildweek.team5_buildweek2_backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comune {
    private Integer codiceProvincia;
    @Id
    @Column(updatable = false)
    private Integer progressiviDelComune;
    private String denominazioneInItaliano;
}
