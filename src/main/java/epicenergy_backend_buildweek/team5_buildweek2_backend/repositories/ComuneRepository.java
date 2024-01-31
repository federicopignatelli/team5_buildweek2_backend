package epicenergy_backend_buildweek.team5_buildweek2_backend.repositories;

import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Comune;
import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComuneRepository extends JpaRepository<Comune,Long> {
//    @Query("SELECT p, c FROM Provincia p JOIN p.citta c WHERE p.provincia LIKE ('%', :provincia, '%')")
//    List<Provincia> findByProvincia(@Param("provincia") String provincia);

    @Query("SELECT c FROM Provincia p JOIN p.comuneList c WHERE p.provincia LIKE CONCAT('%', :provincia, '%')")
    List<Comune> findByProvincia(@Param("provincia") String provincia);

    @Override
    List<Comune> findAll();
}
str