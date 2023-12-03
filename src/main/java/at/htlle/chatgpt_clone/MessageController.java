package at.htlle.chatgpt_clone;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

@RestController
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ChatHistoryRepository chatHistoryRepository;

    @PostMapping("/messages")
    public ResponseEntity<Message> saveMessage(@RequestBody Message message) {
        Message savedMessage = messageRepository.save(message);
        return ResponseEntity.ok(savedMessage);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getMessages(@RequestParam Integer chatHistoryId) {
        List<Message> messages = messageRepository.findByChatHistoryId(chatHistoryId);
        return ResponseEntity.ok(messages);
    }


    @Value("${openai.api.key}")
    private String openaiApiKey;

    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";

    @PostMapping("/chatgpt")
    public ResponseEntity<String> getChatGptResponse(@RequestParam String userInput, @RequestParam Integer chatHistoryId) {
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

        // Fetch the Chathistory object with the given chatHistoryId
        Chathistory chatHistory = chatHistoryRepository.findById(chatHistoryId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid chatHistoryId: " + chatHistoryId));

        // Save to database
        Message message = new Message();
        message.setChatHistory(chatHistory); // Set the chatHistory
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

    @PostMapping("/chatHistories")
    public ResponseEntity<Chathistory> saveChatHistory(@RequestBody Chathistory chatHistory) {
        Chathistory savedChatHistory = chatHistoryRepository.save(chatHistory);
        return ResponseEntity.ok(savedChatHistory);
    }

    @GetMapping("/chatHistories")
    public ResponseEntity<List<Chathistory>> getAllChatHistories() {
        List<Chathistory> chatHistories = chatHistoryRepository.findAll();
        return ResponseEntity.ok(chatHistories);
    }

    @GetMapping("/maxChatHistoryId")
    public ResponseEntity<Long> getMaxChatHistoryId() {
        Long maxChatHistoryId = chatHistoryRepository.findMaxChatHistoryId();
        //System.out.println(maxChatHistoryId);
        return ResponseEntity.ok(maxChatHistoryId);
    }

    @PostMapping("/newChatHistory")
    public ResponseEntity<Chathistory> createNewChatHistory(@RequestParam String firstMessage) {
        // Split the first message into words
        String[] words = firstMessage.split("\\s+");

        // If the first message has more than four words, take the first four words and append "..."
        String headline = String.join(" ", Arrays.copyOfRange(words, 0, Math.min(words.length, 4)));
        if (words.length > 4) {
            headline += headline.endsWith(".") ? ".." : "...";
        }

        // Fetch the maximum chatHistoryId from the chatHistoryRepository
        Long maxChatHistoryId = chatHistoryRepository.findMaxChatHistoryId();

        // Increment the maximum chatHistoryId by 1
        Integer newChatHistoryId = maxChatHistoryId != null ? maxChatHistoryId.intValue() + 1 : 1;

        // Create a new Chathistory object with the new chatHistoryId
        Chathistory newChatHistory = new Chathistory();
        newChatHistory.setChatHistoryId(newChatHistoryId);
        newChatHistory.setHeadline(headline); // Use the headline as the headline for the new chat history
        newChatHistory.setTimeStamp(LocalDateTime.now());

        // Save the new Chathistory object to the chatHistoryRepository
        chatHistoryRepository.save(newChatHistory);

        // Return the new Chathistory object in the response
        return ResponseEntity.ok(newChatHistory);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAll() {
        messageRepository.deleteAll();
        chatHistoryRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }


}