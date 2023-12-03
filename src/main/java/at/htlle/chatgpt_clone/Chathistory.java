package at.htlle.chatgpt_clone;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Chathistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chatHistoryId;
    private LocalDateTime timeStamp;
    private String headline;

    public Chathistory() {
    }

    public Chathistory(int ChatHistoryId, LocalDateTime TimeStamp, String Headline) {
        this.chatHistoryId = ChatHistoryId;
        this.timeStamp = TimeStamp;
        this.headline = Headline;
    }

    public int getChatHistoryId() {
        return chatHistoryId;
    }

    public void setChatHistoryId(int chatHistoryId) {
        this.chatHistoryId = chatHistoryId;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }
}
