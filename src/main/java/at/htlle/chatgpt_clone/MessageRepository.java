package at.htlle.chatgpt_clone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository  // This interface is a Spring Data JPA repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    // Custom query to find messages by chatHistoryId
    @Query("SELECT m FROM Message m WHERE m.chatHistory.chatHistoryId = ?1")
    List<Message> findByChatHistoryId(Integer chatHistoryId);
}