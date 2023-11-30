package at.htlle.chatgpt_clone;

import at.htlle.chatgpt_clone.Message;
import at.htlle.chatgpt_clone.MessageRepository;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

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


    @Value("${openai.api.key}")
    private String openaiApiKey;

    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";

    @PostMapping("/chatgpt")
    public ResponseEntity<String> getChatGptResponse(@RequestParam String userInput) {
        // Set up headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + openaiApiKey);

        // Create request body
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", "gpt-3.5-turbo"); // Adjust the model as needed
        JSONArray messages = new JSONArray();
        messages.put(new JSONObject().put("role", "user").put("content", userInput));
        requestBody.put("messages", messages);
        requestBody.put("max_tokens", 100);

        // Make the request
        HttpEntity<String> request = new HttpEntity<>(requestBody.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(OPENAI_API_URL, request, String.class);

        // Extract the ChatGPT response
        JSONObject jsonResponse = new JSONObject(response.getBody());
        String chatGptResponse = jsonResponse.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content");

        // Save to database
        Message message = new Message();
        message.setInput(userInput);
        message.setOutput(chatGptResponse);
        messageRepository.save(message);

        // Return a simple acknowledgment (optional)
        return ResponseEntity.ok("Response saved");
    }

    // Clears the database (temporary method)
    @PostMapping("/clearDatabase")
    public ResponseEntity<?> clearDatabase() {
        try {
            // Implement the logic to clear the database
            // For example, if using a JPA repository:
            messageRepository.deleteAll();

            return ResponseEntity.ok().body("Database cleared successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error clearing the database");
        }
    }
}


