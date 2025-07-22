package org.bram;

import me.paulschwarz.springdotenv.DotenvPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import me.paulschwarz.springdotenv.DotenvPropertySource;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        DotenvPropertySource.load();
        SpringApplication.run(Main.class, args);
    }
}
