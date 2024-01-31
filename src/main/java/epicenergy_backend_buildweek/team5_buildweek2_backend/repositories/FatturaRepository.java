package epicenergy_backend_buildweek.team5_buildweek2_backend.repositories;

import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Cliente;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Fattura;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.StatoFattura;
import kong.unirest.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura,Long> {
    List<Fattura> findByDataEmissione(LocalDate dataEmissione);
    @Query("SELECT f FROM Fattura f WHERE f.dataEmissione BETWEEN :startDate AND :endDate ")
    List<Fattura> findByRangeDiDate(LocalDate startDate,LocalDate endDate);
    @Query("SELECT f FROM Fattura f WHERE EXTRACT(YEAR FROM dataEmissione) = :year")
    List<Fattura> findByYearOfIssue(int year);

    @Query("SELECT f FROM Fattura f WHERE importo BETWEEN :minImporto AND :maxImporto")
    List<Fattura> findByRangeImporti(double minImporto, double maxImporto);
    @Query("SELECT f FROM Fattura f WHERE importo > :minImporto")
    List<Fattura> findByImportoBiggerThan(double minImporto);

     List<Fattura> findByCliente(Cliente cliente);
     List<Fattura> findByStato(StatoFattura stato);
}
