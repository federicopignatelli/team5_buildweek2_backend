package epicenergy_backend_buildweek.team5_buildweek2_backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comune {
    @Id
    @GeneratedValue
    private Long id;
    private Integer codiceProvincia;
    private Integer progressiviDelComune;
    private String denominazioneInItaliano;
}
