package epicenergy_backend_buildweek.team5_buildweek2_backend.entities;

import jakarta.persistence.*;
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
   @Id
   @GeneratedValue
   private long id;
   // @Id
    // si poterebbe usare l'embedded per l'id
    private String nome;
    //@Id
    //Embedded
    @ManyToOne
    @JoinColumn
    private Provincia provincia;
}