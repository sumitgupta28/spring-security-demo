package com.spring.demo.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.spring.demo.security.db.entity.User;
import com.spring.demo.security.db.repo.UserRepository;

@SpringBootApplication
public class SpringSecurityBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityBasicApplication.class, args);
	}

	@Profile("local")
	@Bean
	CommandLineRunner initDatabase(UserRepository repository) {
		return args -> {
			repository.save(new User("John", "Santideva", 33));
			repository.save(new User("Kevin", "Marie Kondo", 36));
			repository.save(new User("Mat", "Martin Fowler", 15));
		};
	}
}
