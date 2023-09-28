package at.htlle.chatgpt_clone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatGptCloneApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatGptCloneApplication.class, args);
        System.out.println("Server is running on port 8080");
    }

}
