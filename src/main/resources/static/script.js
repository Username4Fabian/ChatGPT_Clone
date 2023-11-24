const submitButton = document.querySelector('#submit');
const outPutElement = document.querySelector('#output');
const inPutElement = document.querySelector('input');
const historyElement = document.querySelector('.history');
const buttonElement = document.querySelector('button');


const sessionId = '123456';  // Placeholder session ID. Generate or fetch dynamically for new chat sessions.

function saveChatContent(userInput, botOutput) {
    const messageData = {
        sessionId: sessionId,
        input: userInput,
        output: botOutput
    };

    fetch('http://localhost:8080/messages', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + API_KEY
        },
        body: JSON.stringify(messageData)
    })
        .then(response => response.json())
        .then(data => console.log('Message saved:', data))
        .catch((error) => console.error('Error:', error));
}

async function getMessage() {
    console.log('clicked');
    const userMessage = inPutElement.value;

    // Display the user's message
    const userMessageDiv = document.createElement('div');
    userMessageDiv.classList.add('message', 'user-message');
    userMessageDiv.textContent = userMessage;
    const chatContainer = document.getElementById('chat-container');
    chatContainer.appendChild(userMessageDiv);
    chatContainer.scrollTop = chatContainer.scrollHeight;
    inPutElement.value = "";

    // Send the message to your backend
    try {
        await fetch('/chatgpt', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: `userInput=${encodeURIComponent(userMessage)}`
        });

        // Optionally display a loading message or placeholder here
        // Example: displayLoadingMessage();

        // Refresh the chat history to show the new response
        // This could be optimized to avoid reloading the entire history
        loadChatHistory();

    } catch (error) {
        console.error('Error:', error);
    }
}



function loadChatHistory() {
    fetch('/messages') // Ensure this points to the correct backend endpoint
        .then(response => response.json())
        .then(messages => {
            const chatContainer = document.getElementById('chat-container');
            messages.forEach(message => {
                // Create a div for the user's message
                if (message.input) {
                    const userInputDiv = document.createElement('div');
                    userInputDiv.classList.add('message', 'user-message');
                    userInputDiv.textContent = message.input;
                    chatContainer.appendChild(userInputDiv);
                }

                // Create a div for the bot's response
                if (message.output) {
                    const botResponseDiv = document.createElement('div');
                    botResponseDiv.classList.add('message', 'bot-message');
                    botResponseDiv.textContent = message.output;
                    chatContainer.appendChild(botResponseDiv);
                }
            });

            // Scroll to the bottom to show the latest messages
            chatContainer.scrollTop = chatContainer.scrollHeight;
        })
        .catch(error => console.error('Error loading chat history:', error));
}


loadChatHistory(); // Call this function to load the chat history when the page loads


submitButton.addEventListener('click', getMessage);

// Assuming 'inPutElement' is your input field and 'getMessage' is the function to send the message
inPutElement.addEventListener('keypress', function(event) {
    if (event.key === 'Enter') {
        event.preventDefault(); // Prevent the default action to avoid submitting a form if it's part of one
        getMessage(); // Call the function to handle sending the message
    }
});


function clearInput() {
    inPutElement.value = "";
}

buttonElement.addEventListener('click', clearInput);

