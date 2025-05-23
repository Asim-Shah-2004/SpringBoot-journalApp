package net.journal.journalApp;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JournalAppApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String mongoUri = dotenv.get("MONGO_URI");
        if (mongoUri == null || mongoUri.trim().isEmpty()) {
            throw new IllegalStateException("MONGO_URI environment variable is not set in .env file");
        }
        System.setProperty("MONGO_URI", mongoUri);
        
        SpringApplication.run(JournalAppApplication.class, args);
    }
}
