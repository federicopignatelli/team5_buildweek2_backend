package epicenergy_backend_buildweek.team5_buildweek2_backend.repositories;

import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, String> {
    @Query("SELECT p FROM Provincia p WHERE p.provincia LIKE CONCAT('%', :provincia, '%')")
    List<Provincia> findByLikeProvincia(@Param("provincia") String provincia);

    @Query("SELECT p FROM Provincia p WHERE SUBSTRING(p.provincia, 1, 4) = :provincia OR SUBSTRING(p.provincia, LENGTH(p.provincia) - 3, 4) = :provincia")
    List<Provincia> findByFirstOrLastFourLetters(@Param("provincia") String provincia);

    @Query("SELECT p FROM Provincia p WHERE SUBSTRING(p.provincia, 1, 4) = :provincia")
    List<Provincia> findByFirstFourLetters(@Param("provincia") String provincia);

//    SELECT * FROM provincia WHERE SUBSTRING(provincia.provincia, 1, 4) = 'Sud Sardegna';
}
