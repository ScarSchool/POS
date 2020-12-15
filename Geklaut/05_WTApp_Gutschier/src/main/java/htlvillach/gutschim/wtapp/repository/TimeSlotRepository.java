package htlvillach.gutschim.wtapp.repository;

import htlvillach.gutschim.wtapp.models.Admin;
import htlvillach.gutschim.wtapp.models.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
}
