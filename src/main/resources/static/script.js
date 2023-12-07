/*
TODO:
- Add a delete button for each chat history
- Add a rename button for each chat history
- Add timestamps to history
 */

const submitButton = document.querySelector('#submit');
const inPutElement = document.querySelector('input');
const buttonElement = document.querySelector('button');
let isFirstMessage = true;
let chatHistoryId = null;

displayHistory();

// Function to start a new chat
async function startNewChat(firstMessage) {
    clearChatContainer(); // Clear chat container to not display messages from previous chat
    const response = await fetch('/newChatHistory', {
        method: 'POST',
        headers: {'Content-Type': 'application/x-www-form-urlencoded'}, // // Sets the 'Content-Type' header for the HTTP request to 'application/x-www-form-urlencoded'
        body: `firstMessage=${encodeURIComponent(firstMessage)}`
    });

    const newChatHistory = await response.json();
    displayHistory();
    return newChatHistory.chatHistoryId;
}

// Function to get a message from the user, send it to ChatGPT, and display the response
async function getMessage() {
    const userMessage = inPutElement.value;
    const chatContainer = document.getElementById('chat-container');
    const userMessageDiv = document.createElement('div');

    userMessageDiv.classList.add('message', 'user-message'); // Add classes to userMessageDiv
    userMessageDiv.textContent = userMessage;
    chatContainer.appendChild(userMessageDiv);
    chatContainer.scrollTop = chatContainer.scrollHeight;
    inPutElement.value = "";

    if (isFirstMessage) { // Check if it is the first message & wait for the new chat history to be created
        chatHistoryId = await startNewChat(userMessage);
        isFirstMessage = false;
    }

    try {
        await fetch('/chatgpt', { // Fetch request to send message to ChatGPT
            method: 'POST',
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            body: `userInput=${encodeURIComponent(userMessage)}&chatHistoryId=${chatHistoryId}`
        });

        loadChatHistory(chatHistoryId);
    } catch (error) {
        console.error('Error:', error);
    }
}

// Function to load chat history from the database and display it
function loadChatHistory(chatHistoryId) {
    clearChatContainer(); // Clear chat container to not display messages double
    fetch(`/messages?chatHistoryId=${chatHistoryId}`)
        .then(response => response.json())
        .then(messages => {
            const chatContainer = document.getElementById('chat-container');
            if (Array.isArray(messages)) {
                messages.forEach(message => {
                    if (message.input) { // Check if message is an input or output
                        const userInputDiv = document.createElement('div');
                        userInputDiv.classList.add('message', 'user-message');
                        userInputDiv.textContent = message.input;
                        chatContainer.appendChild(userInputDiv);
                    }

                    if (message.output) {
                        const botResponseDiv = document.createElement('div');
                        botResponseDiv.classList.add('message', 'bot-message');
                        botResponseDiv.textContent = message.output;
                        chatContainer.appendChild(botResponseDiv);
                    }
                });
            }
            chatContainer.scrollTop = chatContainer.scrollHeight;
        })
        .catch(error => console.error('Error loading chat history:', error));
}


// Event listeners for the submit button and the input field
submitButton.addEventListener('click', getMessage);
inPutElement.addEventListener('keypress', function (event) {
    if (event.key === 'Enter') {
        event.preventDefault();
        getMessage();
    }
});

// Function to clear the input field
function clearInput() {
    inPutElement.value = "";
}

// Function to clear the chat container
function clearChatContainer() {
    const chatContainer = document.getElementById('chat-container'); // Get chat container
    chatContainer.innerText = "";
}

// Function to display chat histories
function displayHistory() {
    fetch('/chatHistories') // Fetch request to get chat histories
        .then(response => response.json())
        .then(chatHistories => {
            const historyList = document.getElementById('history-list'); // Get history list
            historyList.innerHTML = '';

            chatHistories.forEach(chatHistory => {
                const chatHistoryDiv = document.createElement('div');
                chatHistoryDiv.textContent = chatHistory.headline;

                chatHistoryDiv.addEventListener('click', () => {
                    chatHistoryId = chatHistory.chatHistoryId; // Update chatHistoryId
                    loadChatHistory(chatHistoryId);
                });

                historyList.appendChild(chatHistoryDiv); // Add chat history to history list
            });
        })
        .catch(error => console.error('Error loading chat histories:', error));
}

// Event listeners for the new chat button
document.querySelector('#newChatButton').addEventListener('click', async () => {
    clearInput();
    const firstMessage = inPutElement.value;
    const chatContainer = document.getElementById('chat-container'); // Get chat container

    if (firstMessage.trim() !== '') { // Check if firstMessage is not empty
        chatHistoryId = await startNewChat(firstMessage);
        isFirstMessage = true;
        clearChatContainer();
        loadChatHistory(chatHistoryId);
    }

    if (chatContainer.children.length > 0) { // Check if chatContainer is not empty
        clearChatContainer();
        isFirstMessage = true;
    }
});

// Event listeners for the delete button
document.querySelector('#deleteAllButton').addEventListener('click', () => {
    fetch('/deleteAll', {method: 'DELETE'}) // Fetch request to delete all chat histories
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            isFirstMessage = true;
            clearChatContainer();
            loadChatHistory(chatHistoryId); // Reload chat history & display chat histories
            displayHistory();
        })
        .catch(error => console.error('Error:', error));
});

// Event listener for the clear button
buttonElement.addEventListener('click', clearInput);

