package at.scarc.springbootjparest.repositories;

import at.scarc.springbootjparest.models.TimeSlot;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeSlotRepository extends org.springframework.data.jpa.repository.JpaRepository<TimeSlot, Long> {
}
