package epicenergy_backend_buildweek.team5_buildweek2_backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
    @Id
    @GeneratedValue
    private Long id;
    private Integer codiceProvincia;
    private Integer progressiviDelComune;
    private String denominazioneInItaliano;
}
