package com.tschlatd.springdatajpa.controller;

import com.tschlatd.springdatajpa.model.Mail;
import com.tschlatd.springdatajpa.service.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/mails")
public class MailController {
    @Autowired
    private MailServiceImpl service;

    @GetMapping("")
    public List<Mail> getAllMails() {
        return service.getAllMails();
    }

    @GetMapping("/{id}")
    public Mail getMailById(@PathVariable int id) {
        return service.getOneMail(id);
    }

    @PostMapping("")
    public Mail createMail(@RequestBody Mail Mail) {
        return service.createMail(Mail);
    }

    @PutMapping("")
    public Mail updateMail(@RequestBody Mail Mail) {
        return service.createMail(Mail);
    }

    @DeleteMapping("/{id}")
    public void deleteMail(@PathVariable int id) {
        service.deleteMail(id);
    }
}