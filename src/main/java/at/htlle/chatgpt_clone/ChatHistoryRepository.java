package at.htlle.chatgpt_clone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChatHistoryRepository extends JpaRepository<Chathistory, Integer> {
    @Query("SELECT MAX(c.chatHistoryId) FROM Chathistory c")
    Long findMaxChatHistoryId();
}