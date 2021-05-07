package com.tschlatd.springdatajpa.service;

import com.tschlatd.springdatajpa.model.Mail;

import java.util.List;

public interface MailService {
    List<Mail> getAllMails();
    Mail getOneMail(int id);
    Mail createMail(Mail Mail);
    boolean deleteMail(int id);
}
