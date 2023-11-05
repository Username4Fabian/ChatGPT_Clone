let API_KEY = '';
const submitButton = document.querySelector('#submit');
const outPutElement = document.querySelector('#output');
const inPutElement = document.querySelector('input');
const historyElement = document.querySelector('.history');
const buttonElement = document.querySelector('button');

// Fetch API key from the backend
async function fetchApiKey() {
    try {
        const response = await fetch('/api-key');
        API_KEY = await response.text();
    } catch (error) {
        console.error('Error fetching API key:', error);
    }
}
fetchApiKey();

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

    const options = {
        method: 'POST',
        headers: {
            'Authorization': `Bearer ${API_KEY}`,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            model: "gpt-3.5-turbo",
            messages: [{ role: "user", content: userMessage }],
            max_tokens: 100
        })
    };

    try {
        const response = await fetch('https://api.openai.com/v1/chat/completions', options);
        const data = await response.json();
        const botResponse = data.choices[0].message.content;

        // Append user's message to the history
        if (userMessage) {
            const pElement = document.createElement('p');
            pElement.textContent = userMessage;
            pElement.addEventListener('click', () => changeInput(pElement.textContent));
            //historyElement.append(pElement);
        }

        // Save the chat content and wait for it to finish
        await saveChatContent(userMessage, botResponse);

        // Introduce a short delay to ensure the database has time to update
        await new Promise(resolve => setTimeout(resolve, 500)); // 500ms delay

        // After saving, reload the chat history
        await loadChatHistory();

    } catch (error) {
        console.error(error);
    }

    // Clear the input after everything is done
    inPutElement.value = '';
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
