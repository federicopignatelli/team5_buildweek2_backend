package epicenergy_backend_buildweek.team5_buildweek2_backend.repositories;

import epicenergy_backend_buildweek.team5_buildweek2_backend.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia,String> {
}
