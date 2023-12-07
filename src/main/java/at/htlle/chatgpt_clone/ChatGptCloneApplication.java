package at.htlle.chatgpt_clone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

// Spring Boot Application
@SpringBootApplication
// Scanning for entities in the specified package
@EntityScan(basePackages = "at.htlle.chatgpt_clone")
public class ChatGptCloneApplication {

    public static void main(String[] args) {
        // Launching the application
        SpringApplication.run(ChatGptCloneApplication.class, args);
        // Indicating that the server is running
        System.out.println("Server is running at http://localhost:8080/");
    }
}