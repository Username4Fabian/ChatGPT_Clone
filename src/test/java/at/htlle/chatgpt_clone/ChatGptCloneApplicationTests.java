package at.htlle.chatgpt_clone;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ChatGptCloneApplicationTests {

    @Autowired
    private MessageController messageController;

    @MockBean
    private MessageRepository messageRepository;

    @MockBean
    private ChatHistoryRepository chatHistoryRepository;


    @Test
    public void saveMessageReturnsSavedMessage() {
        Message message = new Message();
        when(messageRepository.save(any(Message.class))).thenReturn(message);

        ResponseEntity<Message> response = messageController.saveMessage(message);

        assertEquals(message, response.getBody());
    }

    @Test
    public void getMessagesReturnsMessagesByChatHistoryId() {
        List<Message> messages = Arrays.asList(new Message(), new Message());
        when(messageRepository.findByChatHistoryId(anyInt())).thenReturn(messages);

        ResponseEntity<List<Message>> response = messageController.getMessages(1);

        assertEquals(messages, response.getBody());
    }

    @Test
    public void getChatGptResponseSavesMessageAndReturnsAcknowledgment() {
        Chathistory chatHistory = new Chathistory();
        when(chatHistoryRepository.findById(anyInt())).thenReturn(java.util.Optional.of(chatHistory));

        ResponseEntity<String> response = messageController.getChatGptResponse("Hello", 1);

        verify(messageRepository, times(1)).save(any(Message.class));
        assertEquals("Response saved", response.getBody());
    }

    @Test
    public void saveChatHistoryReturnsSavedChatHistory() {
        Chathistory chatHistory = new Chathistory();
        when(chatHistoryRepository.save(any(Chathistory.class))).thenReturn(chatHistory);

        ResponseEntity<Chathistory> response = messageController.saveChatHistory(chatHistory);

        assertEquals(chatHistory, response.getBody());
    }

    @Test
    public void getAllChatHistoriesReturnsAllChatHistories() {
        List<Chathistory> chatHistories = Arrays.asList(new Chathistory(), new Chathistory());
        when(chatHistoryRepository.findAll()).thenReturn(chatHistories);

        ResponseEntity<List<Chathistory>> response = messageController.getAllChatHistories();

        assertEquals(chatHistories, response.getBody());
    }

    @Test
    public void getMaxChatHistoryIdReturnsMaxChatHistoryId() {
        Long maxChatHistoryId = 1L;
        when(chatHistoryRepository.findMaxChatHistoryId()).thenReturn(maxChatHistoryId);

        ResponseEntity<Long> response = messageController.getMaxChatHistoryId();

        assertEquals(maxChatHistoryId, response.getBody());
    }

    @Test
    public void createNewChatHistoryReturnsNewChatHistory() {
        Chathistory chatHistory = new Chathistory();
        chatHistory.setChatHistoryId(1);
        chatHistory.setHeadline("Hello world");
        chatHistory.setTimeStamp(LocalDateTime.now());
        when(chatHistoryRepository.save(any(Chathistory.class))).thenReturn(chatHistory);

        ResponseEntity<Chathistory> response = messageController.createNewChatHistory("Hello world");

        assertEquals(chatHistory.getChatHistoryId(), response.getBody().getChatHistoryId());
        assertEquals(chatHistory.getHeadline(), response.getBody().getHeadline());
    }

    @Test
    public void deleteAllDeletesAllData() {
        doNothing().when(messageRepository).deleteAll();
        doNothing().when(chatHistoryRepository).deleteAll();

        ResponseEntity<Void> response = messageController.deleteAll();

        verify(messageRepository, times(1)).deleteAll();
        verify(chatHistoryRepository, times(1)).deleteAll();
        assertEquals(204, response.getStatusCodeValue());
    }
}