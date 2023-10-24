package at.htlle.chatgpt_clone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "at.htlle.chatgpt_clone")
public class ChatGptCloneApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatGptCloneApplication.class, args);
        System.out.println("Server is running at http://localhost:8080/");
    }

}
