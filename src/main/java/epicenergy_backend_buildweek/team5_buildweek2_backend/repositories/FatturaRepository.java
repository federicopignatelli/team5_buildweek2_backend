package epicenergy_backend_buildweek.team5_buildweek2_backend.repositories;

import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura,Long> {
    List<Fattura> findByDataEmissione(LocalDate dataEmissione);
    List<Fattura> findByDataEmissioneAnno(LocalDate startDate, LocalDate endDate);

    List<Fattura> findByRangeImporti(double minImporto, double maxImporto);

    List<Fattura> findByClienteId(Long clienteId);
}
