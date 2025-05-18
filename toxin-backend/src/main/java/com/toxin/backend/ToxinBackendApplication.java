package com.toxin.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ToxinBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToxinBackendApplication.class, args);
    }

}
