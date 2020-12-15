package htlvillach.gutschim.wtapp.repository;

import htlvillach.gutschim.wtapp.models.Admin;
import htlvillach.gutschim.wtapp.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
