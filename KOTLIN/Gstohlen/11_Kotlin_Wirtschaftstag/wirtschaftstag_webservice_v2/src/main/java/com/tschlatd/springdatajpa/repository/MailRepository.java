package com.tschlatd.springdatajpa.repository;

import com.tschlatd.springdatajpa.model.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailRepository extends JpaRepository<Mail, Integer> {
}
