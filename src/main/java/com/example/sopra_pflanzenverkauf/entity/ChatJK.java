package com.example.sopra_pflanzenverkauf.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ChatJK {

    @Id
    @GeneratedValue
    @Column(name = "ChatID")
    private Integer chatId;

    @Column(name = "Verkäufer akzeptiert")
    private Boolean recipientAccept = false;

    @Column(name = "Käufer akzeptiert")
    private Boolean senderAccept = false;

    @ManyToOne
    @JoinColumn(name = "Sender")
    private User senderOfChat;

    @ManyToOne
    @JoinColumn(name = "Empfänger")
    private User recipientOfChat;

    @ManyToOne
    @JoinColumn(name = "Pflanze")
    private Plant chatPlant;

    @OneToMany (mappedBy = "associatedChat")
    private java.util.List<MessageJK> messagesInChat = new ArrayList<>();


    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public User getSenderOfChat() {
        return senderOfChat;
    }

    public void setSenderOfChat(User senderOfChat) {
        this.senderOfChat = senderOfChat;
    }

    public User getRecipientOfChat() {
        return recipientOfChat;
    }

    public void setRecipientOfChat(User recipientOfChat) {
        this.recipientOfChat = recipientOfChat;
    }

    public List<MessageJK> getMessagesInChat() {
        return messagesInChat;
    }

    public void setMessagesInChat(List<MessageJK> messagesInChat) {
        this.messagesInChat = messagesInChat;
    }

    public Plant getChatPlant() {
        return chatPlant;
    }

    public void setChatPlant(Plant chatPlant) {
        this.chatPlant = chatPlant;
    }

    public Boolean getRecipientAccept() {
        return recipientAccept;
    }

    public void setRecipientAccept(Boolean recipientAccept) {
        this.recipientAccept = recipientAccept;
    }

    public Boolean getSenderAccept() {
        return senderAccept;
    }

    public void setSenderAccept(Boolean senderAccept) {
        this.senderAccept = senderAccept;
    }
}
