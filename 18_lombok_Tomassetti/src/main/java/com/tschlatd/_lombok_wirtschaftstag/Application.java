package com.scarc._lombok_tomassetti.models;

import com.tschlatd._lombok_wirtschaftstag.models.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@GetMapping("/hello")
	public String printHello() {
		return "Hello how goes it you?";
	}

	@GetMapping("/test")
	public Object justATest() {
		return new User();
	}
}
