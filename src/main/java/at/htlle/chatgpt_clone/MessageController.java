package at.htlle.chatgpt_clone;

import at.htlle.chatgpt_clone.Message;
import at.htlle.chatgpt_clone.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @PostMapping("/messages")
    public ResponseEntity<Message> saveMessage(@RequestBody Message message) {
        Message savedMessage = messageRepository.save(message);
        return ResponseEntity.ok(savedMessage);
    }
}
