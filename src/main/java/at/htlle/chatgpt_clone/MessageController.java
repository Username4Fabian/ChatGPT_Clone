package at.htlle.chatgpt_clone;

import at.htlle.chatgpt_clone.Message;
import at.htlle.chatgpt_clone.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @PostMapping("/messages")
    public ResponseEntity<Message> saveMessage(@RequestBody Message message) {
        Message savedMessage = messageRepository.save(message);
        return ResponseEntity.ok(savedMessage);
    }
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getChatHistory() {
        // This should fetch the chat history from the database
        List<Message> chatHistory = messageRepository.findAll();
        return ResponseEntity.ok(chatHistory);
    }

}
