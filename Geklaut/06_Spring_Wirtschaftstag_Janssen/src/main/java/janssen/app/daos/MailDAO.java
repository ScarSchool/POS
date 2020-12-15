package janssen.app.daos;

import janssen.app.models.Mail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailDAO extends JpaRepository<Mail, Integer> {

}
