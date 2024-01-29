package Entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Provincia {
    private String sigla;
    private String nome;
    private String regione;
}
