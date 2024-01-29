package epicenergy_backend_buildweek.team5_buildweek2_backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
@Entity
@Table(name = "fatture")
@Getter
@NoArgsConstructor
@ToString
public class Fattura {
    @Id
    @GeneratedValue
    private long numero;
    @Column(name = "data_emissione")
    private LocalDate dataEmissione = LocalDate.now();
    @Setter
    @Column(name = "importo (€)")
    private double importo;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    public Fattura(double importo, Cliente cliente) {
        this.importo = importo;
        this.cliente = cliente;
    }
}
