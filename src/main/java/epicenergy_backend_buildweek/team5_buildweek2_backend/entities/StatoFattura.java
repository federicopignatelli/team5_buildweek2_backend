package epicenergy_backend_buildweek.team5_buildweek2_backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "stato_fattura")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class StatoFattura {
    @Id
    private String stato;
}