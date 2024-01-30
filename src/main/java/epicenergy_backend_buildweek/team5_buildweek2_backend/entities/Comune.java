package epicenergy_backend_buildweek.team5_buildweek2_backend.entities;

import com.opencsv.bean.CsvBindByPosition;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comuni")
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
    @ManyToOne
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;
}

