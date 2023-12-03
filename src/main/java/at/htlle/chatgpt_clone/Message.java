package at.htlle.chatgpt_clone;

import jakarta.persistence.*; //jakarta and not javax !!!!!!!

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chat_history_id")
    private Chathistory chatHistory;

    private String input;
    private String output;

    public Message() {}

    public Message(Chathistory chatHistory, String input, String output) {
        this.chatHistory = chatHistory;
        this.input = input;
        this.output = output;
    }

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
