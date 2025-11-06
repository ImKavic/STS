package com.sts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.sts.repository") 
public class App implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private ApplicationContext applicationContext; 

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("--- Starting Database Connection Check ---");
        try {
            jdbcTemplate.execute("SELECT 1"); 
            System.out.println("✅ Database connection successful! Checking DDL status...");

        } catch (Exception e) {
            System.err.println("❌ FAILED: Critical database connection error!");
            System.err.println("Error details: " + e.getMessage()); 
            System.err.println("Stopping application gracefully.");

            SpringApplication.exit(applicationContext, () -> 1);
            System.exit(1); 
        }
    }
}