package net.plavcak.tutorial.tutorial.jpa.versioning.triggers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TriggersApplication {

	public static void main(String[] args) {
		SpringApplication.run(TriggersApplication.class, args);
	}
}
