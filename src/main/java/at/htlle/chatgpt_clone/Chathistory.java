package at.htlle.chatgpt_clone;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity  // Marks this class as a JPA entity
public class Chathistory {

    @Id  // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Specifies how the primary key is generated
    private int chatHistoryId;
    private LocalDateTime timeStamp;
    private String headline;

    /*
    *JPA, which is a specification for object-relational mapping in Java,
    * needs to be able to create an empty instance of the entity class before it
    * starts to populate the retrieved data into the fields.
    * This is done via the default constructor.
    */

    // Default constructor
    public Chathistory() {
    }

    // Constructor with parameters
    public Chathistory(int ChatHistoryId, LocalDateTime TimeStamp, String Headline) {
        this.chatHistoryId = ChatHistoryId;
        this.timeStamp = TimeStamp;
        this.headline = Headline;
    }

    // Getters and setters for the fields
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