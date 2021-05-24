package janssen.spring.repositories;

import janssen.spring.entities.AnimalShelter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalShelterRepository extends JpaRepository<AnimalShelter, String> {

}
