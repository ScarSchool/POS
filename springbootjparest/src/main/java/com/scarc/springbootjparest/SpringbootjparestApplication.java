package com.scarc.springbootjparest;

import com.scarc.springbootjparest.models.Admin;
import com.scarc.springbootjparest.repository.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@SpringBootApplication
@RestController
public class SpringbootjparestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootjparestApplication.class, args);
    }

    @GetMapping("/api/v1/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @Bean
    @Transactional
    public CommandLineRunner mockData(AdminRepository adminRepo) {
        System.out.println("Command line runner?");

        Admin admin = Admin.builder()
                .name("Pferd")
                .eMail("dog-sausage@htl.at")
                .pwdToken("GEHEIM").build();

        adminRepo.save(admin);

        return null;
    }
}
