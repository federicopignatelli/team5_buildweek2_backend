package epicenergy_backend_buildweek.team5_buildweek2_backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Indirizzo {
    @Id
    @GeneratedValue
    private UUID id;
    private String via;
    private int civico;
    private String località;
    private int CAP;
    @OneToOne
    @JoinColumn
    private Comune comune;

    public Indirizzo(String via, int civico, String località, int CAP, Comune comune) {
        this.via = via;
        this.civico = civico;
        this.località = località;
        this.CAP = CAP;
        this.comune = comune;
    }
}
