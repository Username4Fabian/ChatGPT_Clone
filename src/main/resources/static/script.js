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

function changeInput(value) {
    inPutElement.value = value;
}

async function saveChatContent(sender, content) {
    try {
        const response = await fetch('http://localhost:8080/messages', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                sender: sender,
                content: content
            })
        });

        if (!response.ok) {
            console.error('Failed to save chat content:', await response.text());
            return;
        }
    } catch (error) {
        console.error('Error saving chat content:', error);
    }
}

async function getMessage() {
    console.log('clicked');
    const userMessage = inPutElement.value;

    // Save user's message to the database
    await saveChatContent('user', userMessage);

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

        // Save bot's response to the database
        await saveChatContent('bot', botResponse);

        // Display bot's response
        outPutElement.textContent = botResponse;

        // Append user's message to the history
        if (userMessage) {
            const pElement = document.createElement('p');
            pElement.textContent = userMessage;
            pElement.addEventListener('click', () => changeInput(pElement.textContent));
            historyElement.append(pElement);
        }
    } catch (error) {
        console.error(error);
    }
}

submitButton.addEventListener('click', getMessage);

function clearInput() {
    inPutElement.value = "";
}

buttonElement.addEventListener('click', clearInput);
