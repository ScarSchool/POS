package htlvillach.gutschim.wtapp.repository;

import htlvillach.gutschim.wtapp.models.Admin;
import htlvillach.gutschim.wtapp.models.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Long> {
}
