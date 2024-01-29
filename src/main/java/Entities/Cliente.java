package Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Cliente {
    @Id
    @GeneratedValue
    private UUID id;
    private String nomeContatto;
    private Long telefonoContatto;
    private String emailContatto;
    private String regioneSociale;

    private String partitaIva;
    private String email;
    private String pec;
    private Long telefono;
    private String indirizzoSedeOperativa;
    private String indirizzoSedeLegale;
    private String logoAziendale;
    private TipoAzienda tipo;

    private LocalDate dataInserimento;
    private LocalDate dataUltimoContatto;

    private int fatturatoAnnuale;

}
