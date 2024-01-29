package Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Comune {
    private String codiceProvincia;
    private String denominazione;
    private UUID idRegionale;
}
