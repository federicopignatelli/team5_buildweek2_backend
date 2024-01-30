package epicenergy_backend_buildweek.team5_buildweek2_backend.entities;

import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.enums.TipoAzienda;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
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
    private long telefonoContatto;
    private String emailContatto;
    private String ragioneSociale;

    private String emailAziendale;
    private String pecAziendale;
    private long telefonoAziendale;
    @OneToOne
    @JoinColumn(name = "sede_operativa_id")
    private Indirizzo indirizzoSedeOperativa;
    @OneToOne
    @JoinColumn(name = "sede_legale_id")
    private Indirizzo indirizzoSedeLegale;
    private String urlLogoAziendale;
    @Enumerated(EnumType.STRING)
    private TipoAzienda tipo;

    private LocalDate dataInserimento = LocalDate.now();
    private LocalDate dataUltimoContatto;

    private int fatturatoAnnuale;
    public Cliente(String ragioneSociale, String emailAziendale, Indirizzo indirizzoSedeOperativa) {
        this.ragioneSociale = ragioneSociale;
        this.emailAziendale = emailAziendale;
        this.indirizzoSedeOperativa = indirizzoSedeOperativa;
    }
}