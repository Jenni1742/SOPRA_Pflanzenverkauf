$(document).ready(function() {
    const chatBox = $('.chat-box');
    const typingArea = $('.typing-area');
    const recipientId = $('input[name="recipientId"]').val();
    const csrfToken = $('meta[name="_csrf"]').attr('content');
    const csrfHeader = $('meta[name="_csrf_header"]').attr('content');

    $.ajaxSetup({
        beforeSend: function(xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        }
    });

    function fetchMessages() {
        $.get('/chat/messages', function(messages) {
            const scrollHeight = chatBox[0].scrollHeight;
            const shouldScrollToBottom = chatBox.scrollTop() + chatBox.innerHeight() + 20 >= scrollHeight;
            chatBox.empty();
            messages.forEach(function(message) {
                const messageElement = $('<div>').addClass(message.sender === '1' ? 'outgoing' : 'incoming');
                messageElement.html('<div class="details"><p>' + message.content + '</p></div>');
                chatBox.append(messageElement);
            });
            if (shouldScrollToBottom) {
                chatBox.scrollTop(scrollHeight);
            }
        });
    }


    function sendMessage(content) {
        const messageData = {
            content: content,
            recipientId: recipientId
        };

        $.ajax({
            type: "POST",
            url: "/chat/messages",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(messageData),
            success: function() {
                fetchMessages();
                typingArea.find('input[name="content"]').val('');
            }
        });
    }

    typingArea.on('submit', function(event) {
        event.preventDefault();
        const content = $('input[name="content"]').val().trim();
        if (content) {
            sendMessage(content);
        }
    });

    fetchMessages();
    setInterval(fetchMessages, 5000);
});
