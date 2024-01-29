package epicenergy_backend_buildweek.team5_buildweek2_backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comune {
   // @Id
    // si poterebbe usare l'embedded per l'id
    private String nome;
    //@Id
    //Embedded
    @ManyToOne
    @JoinColumn
    private Provincia provincia;
}