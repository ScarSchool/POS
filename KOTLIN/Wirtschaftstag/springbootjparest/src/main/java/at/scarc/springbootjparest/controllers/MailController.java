package at.scarc.springbootjparest.controllers;

import at.scarc.springbootjparest.models.Mail;
import at.scarc.springbootjparest.services.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mails")
public class MailController {

    @Autowired
    private MailServiceImpl svc;

    @GetMapping()
    public List<Mail> getAllMails() { return svc.getAll(); }

    @GetMapping("/{id}")
    public Mail getMailById(@PathVariable Long id) { return svc.getOne(id); }

    @PostMapping()
    public Mail createMail(@RequestBody Mail mail) { return svc.createOrUpdateOne(mail); }

    @PutMapping()
    public Mail updateMail(@RequestBody Mail mail) { return svc.createOrUpdateOne(mail); }

    @DeleteMapping("/{id}")
    public boolean deleteMail(@PathVariable Long id) { return svc.delete(id); }
}