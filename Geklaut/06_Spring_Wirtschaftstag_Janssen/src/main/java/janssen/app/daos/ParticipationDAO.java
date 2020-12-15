package janssen.app.daos;

import janssen.app.models.Participation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipationDAO extends JpaRepository<Participation, Integer> {

}
