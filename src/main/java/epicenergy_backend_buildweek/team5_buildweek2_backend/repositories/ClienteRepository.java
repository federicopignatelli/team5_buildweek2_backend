package epicenergy_backend_buildweek.team5_buildweek2_backend.repositories;

import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.UUID;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    Cliente findByRagioneSociale(String ragioneSociale);

    @Query("SELECT c FROM Cliente c WHERE EXTRACT(FATTURATO FROM fatturatoAnnuale) = :fatturato")
    List<Cliente> findByFatturatoAnnuale(int fatturato);
}