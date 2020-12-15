package htlvillach.gutschim.wtapp.repository;

import htlvillach.gutschim.wtapp.models.Admin;
import htlvillach.gutschim.wtapp.models.Responsible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsibleRepository extends JpaRepository<Responsible, Long> {
}
