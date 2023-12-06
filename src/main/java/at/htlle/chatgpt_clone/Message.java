package at.htlle.chatgpt_clone;

import jakarta.persistence.*;

@Entity  // This class is a JPA entity
public class Message {

    @Id  // Primary key for this entity
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // The primary key value is auto-generated
    private Long id;

    @ManyToOne  // Many-to-one relationship with the Chathistory entity
    @JoinColumn(name = "chat_history_id")  // Foreign key column in the Message table
    private Chathistory chatHistory;

    private String input;
    private String output;

    // Default constructor and constructor with parameters (See ChatHistory.java for more information)
    public Message() {
    }

    // Constructor with parameters
    public Message(Chathistory chatHistory, String input, String output) {
        this.chatHistory = chatHistory;
        this.input = input;
        this.output = output;
    }

    // Getters and setters for the fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Chathistory getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(Chathistory chatHistory) {
        this.chatHistory = chatHistory;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }


}
