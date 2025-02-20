package com.example.test.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository)
    {
        return args -> {
            Student mariam = new Student(
                    LocalDate.of(2001, 1, 1),
                    "mariam@gmail.com",
                    "mariam"

            );
            Student alex = new Student(
                    LocalDate.of(2000, 1, 1),
                    "alex@gmail.com",
                    "alex"
            );
            repository.saveAll(
                    List.of(mariam,alex)
            );
        };
    }
}
