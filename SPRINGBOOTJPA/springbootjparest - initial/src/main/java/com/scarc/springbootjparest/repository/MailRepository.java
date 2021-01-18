package com.scarc.springbootjparest.repository;

import com.scarc.springbootjparest.models.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailRepository extends JpaRepository<Mail, Integer> {
}
