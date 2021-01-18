package com.scarc.springbootjparest.controllers;

import com.scarc.springbootjparest.models.Mail;
import com.scarc.springbootjparest.services.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Mails")
public class MailController {
    @Autowired
    private MailServiceImpl svc;

    @GetMapping("/")
    public List<Mail> getAllMails() {
        return svc.getAllMails();
    }

    @GetMapping("/{id}")
    public Mail getMailById(@PathVariable int id) {
        return svc.getOneMail(id);
    }

    @PostMapping("/")
    public Mail createMail(@RequestBody Mail Mail) {
        return svc.createMail(Mail);
    }

    @PutMapping("/")
    public Mail updateMail(@RequestBody Mail Mail) {
        return svc.createMail(Mail);
    }

    @DeleteMapping("/{id}")
    public void deleteMail(@PathVariable int id) {
        svc.deleteMail(id);
    }
}