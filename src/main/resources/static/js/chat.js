document.addEventListener('DOMContentLoaded', function () {
    const senderId = 'user1';  // Beispielhafter Sendername, später dynamisch ersetzen
    const recipient = 'user2';  // Beispielhafter Empfängername, später dynamisch ersetzen

    function fetchMessages() {
        fetch(`/api/messages?sender=${senderId}&recipient=${recipient}`)
            .then(response => response.json())
            .then(messages => {
                console.log(messages);  // Hier könntest du die Nachrichten in das UI einfügen
            });
    }

    function sendMessage(content) {
        const message = {
            content: content,
            recipient: { username: recipient }  // Erstelle eine passende Empfängerstruktur
        };

        fetch('/api/messages', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(message)
        }).then(response => response.json())
            .then(message => {
                console.log(message);  // Hier könntest du die gesendete Nachricht im UI anzeigen
            });
    }

    // Beispielhafte Nutzung
    fetchMessages();
    // sendMessage('Hallo Welt!');
});
document.querySelector('.typing-area').addEventListener('submit', function(event) {
    event.preventDefault();
    const content = document.querySelector('input[name="content"]').value;
    const recipientId = document.querySelector('input[name="recipientId"]').value;

    fetch('/chat', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'X-CSRF-TOKEN': document.querySelector('input[name="_csrf"]').value
        },
        body: `content=${content}&recipientId=${recipientId}`
    }).then(response => {
        if (response.ok) {
            return response.text();
        } else {
            throw new Error('Network response was not ok.');
        }
    }).then(data => {
        document.querySelector('.chat-box').innerHTML = data;
        document.querySelector('input[name="content"]').value = '';
    }).catch(error => {
        console.error('There has been a problem with your fetch operation:', error);
    });
});

