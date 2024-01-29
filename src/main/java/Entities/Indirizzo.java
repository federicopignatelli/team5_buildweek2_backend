package Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Indirizzo {
    @Id
    @GeneratedValue
    private UUID id;
    private String via;
    private int civico;
    private String località;
    private int CAP;
    private String comune;

}
