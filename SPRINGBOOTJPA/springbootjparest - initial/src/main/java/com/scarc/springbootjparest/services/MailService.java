package com.scarc.springbootjparest.services;

import com.scarc.springbootjparest.models.Mail;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface MailService {
    List<Mail> getAllMails();
    Mail getOneMail(int id);
    Mail createMail(Mail Mail);
    boolean deleteMail(int id);
}
