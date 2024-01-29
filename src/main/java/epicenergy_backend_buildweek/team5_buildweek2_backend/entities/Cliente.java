package epicenergy_backend_buildweek.team5_buildweek2_backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Cliente {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID partitaIva;
    private String nomeContatto;
    private Long telefonoContatto;
    private String emailContatto;
    private String ragioneSociale;

    private String email;
    private String pec;
    private Long telefono;
    private String indirizzoSedeOperativa;
    private String indirizzoSedeLegale;
    private String logoAziendale;
    private TipoAzienda tipo;

    private LocalDate dataInserimento = LocalDate.now();
    private LocalDate dataUltimoContatto;

    private int fatturatoAnnuale;

}
