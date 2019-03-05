package br.com.players.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableAutoConfiguration
@ComponentScan("br.com.players.*")
@EntityScan("br.com.players.persistence.model")
@EnableJpaRepositories("br.com.players.persistence.repository")
public class Application {
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}