package at.htlle.chatgpt_clone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

    @Value("${openai.api.key}")
    private String apiKey;

    @GetMapping("/api-key")
    public ResponseEntity<String> getApiKey() {
        return ResponseEntity.ok(apiKey);
    }
}
