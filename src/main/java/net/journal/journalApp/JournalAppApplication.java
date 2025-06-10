package net.journal.journalApp;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Slf4j
public class JournalAppApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String mongoUri = dotenv.get("MONGO_URI");
        String activeEnvironment = dotenv.get("SPRING_PROFILES_ACTIVE", "dev");
        log.info("MONGO URI : {}", mongoUri);
        log.info("ACTIVE ENVIRONMENT : {}", activeEnvironment);
        
        if (mongoUri == null || mongoUri.trim().isEmpty()) {
            throw new IllegalStateException("MONGO_URI environment variable is not set in .env file");
        }
        
        System.setProperty("spring.data.mongodb.uri", mongoUri);
        System.setProperty("spring.profiles.active", activeEnvironment);
        
        SpringApplication.run(JournalAppApplication.class, args);
    }
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}