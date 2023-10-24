package at.htlle.chatgpt_clone;

import at.htlle.chatgpt_clone.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
