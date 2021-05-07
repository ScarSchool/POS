package com.tschlatd.springdatajpa.service;

import com.tschlatd.springdatajpa.model.Mail;
import com.tschlatd.springdatajpa.repository.MailRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    @Getter
    @Setter
    private MailRepository repo;

    @Override
    public List<Mail> getAllMails() {
        return repo.findAll();
    }

    @Override
    public Mail getOneMail(int id) {
        return repo.findById(id).get();
    }

    @Override
    public Mail createMail(Mail Mail) {
        return repo.save(Mail);
    }

    @Override
    public boolean deleteMail(int id) {
        repo.deleteById(id);
        return true;
    }
}