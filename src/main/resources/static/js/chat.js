document.addEventListener('DOMContentLoaded', function () {
    const sender = 'user1';  // Beispielhafter Sendername, später dynamisch ersetzen
    const recipient = 'user2';  // Beispielhafter Empfängername, später dynamisch ersetzen

    function fetchMessages() {
        fetch(`/api/messages?sender=${sender}&recipient=${recipient}`)
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
