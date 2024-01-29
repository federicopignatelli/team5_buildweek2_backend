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
    List<Provincia> findByFirstFourLetters(@Param("provincia") String provincia);
}
