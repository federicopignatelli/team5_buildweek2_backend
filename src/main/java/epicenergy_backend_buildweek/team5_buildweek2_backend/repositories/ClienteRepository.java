package epicenergy_backend_buildweek.team5_buildweek2_backend.repositories;

import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Cliente;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    Cliente findByRagioneSociale(String ragioneSociale);

    @Query("SELECT c FROM Cliente c WHERE c.fatturatoAnnuale = :fatturato")
    List<Cliente> findByFatturatoAnnuale(Integer fatturato);

    List<Cliente> findByDataInserimento(LocalDate dataInserimento);

    List<Cliente> findByDataUltimoContatto(LocalDate dataInserimento);

    @Query ("SELECT c FROM Cliente c WHERE LOWER(c.ragioneSociale) LIKE LOWER(:ragioneSociale)")
    List<Cliente> findByPartRagioneSociale(String ragioneSociale);
}