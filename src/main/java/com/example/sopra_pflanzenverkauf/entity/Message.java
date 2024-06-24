package com.example.sopra_pflanzenverkauf.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Message {

    @Id
    @GeneratedValue
    @Column(name = "NachrichtID")
    private Integer messageId;

    @Column(name = "Inhalt")
    private String content;

    @Column(name = "Zeitstempel")
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "Sender")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "Empfänger")
    private User recipient;

    @ManyToOne
    @JoinColumn(name = "Chat")
    private Chat associatedChat;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public Chat getAssociatedChat() {
        return associatedChat;
    }

    public void setAssociatedChat(Chat associatedChat) {
        this.associatedChat = associatedChat;
    }
}
