package janssen.app.controllers;

import janssen.app.NotFoundException;
import janssen.app.daos.MailDAO;
import janssen.app.models.Mail;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mails")
public class MailController {

  private final MailDAO dao;

  public MailController(MailDAO dao) {
    this.dao = dao;
  }

  @GetMapping
  public List<Mail> listMails() {
    return dao.findAll();
  }

  @GetMapping("{id}")
  public Mail getMail(@PathVariable String id) {
    return dao
      .findById( Integer.parseInt(id) )
      .orElseThrow(() -> new NotFoundException(id));
  }

  @PostMapping
  public Mail createMail(@RequestBody Mail mail) {
    return dao.save(mail);
  }

  @PutMapping({"", "{id}"})
  public Mail updateMail(@PathVariable Optional<String> id, @RequestBody Mail mail) {
    id.ifPresent(idVal -> mail.setId( Integer.parseInt(idVal) ));
    return dao.save(mail);
  }

  @DeleteMapping("{id}")
  public void deleteMail(@PathVariable String id) {
    Mail mail = dao
      .findById( Integer.parseInt(id) )
      .orElseThrow(() -> new NotFoundException(id));

    dao.delete(mail);
  }

}
