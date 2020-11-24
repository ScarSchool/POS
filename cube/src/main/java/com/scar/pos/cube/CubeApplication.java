package com.scar.pos.cube;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CubeApplication {
	public static void main(String[] args) {
		SpringApplication.run(CubeApplication.class, args);
	}

	@RequestMapping("/hello")
	public String sayHello() {
		return "Hello World!";
	}

	@RequestMapping("/")
	public String defaultPage() { return "Hello! This is the default page."; }
}