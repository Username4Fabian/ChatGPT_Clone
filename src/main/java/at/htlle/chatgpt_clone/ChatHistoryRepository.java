package at.htlle.chatgpt_clone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// Repository interface for Chathistory entities
public interface ChatHistoryRepository extends JpaRepository<Chathistory, Integer> {

    // Custom query to find the maximum chatHistoryId
    @Query("SELECT MAX(c.chatHistoryId) FROM Chathistory c")
    Long findMaxChatHistoryId();
}