package com.example.sopra_pflanzenverkauf.service;

import com.example.sopra_pflanzenverkauf.entity.Chat;
import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    @Autowired //Annotation zur Markierung von Objekten für die Spring Dependency Injection
    private ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public void updateChat(Chat chat) {
        chatRepository.save(chat);}

    public List<Chat> getAllChats() {
        return chatRepository.findAll();
    }

    public Chat getChatByChatId(Integer chatId) {
        return chatRepository.findByChatId(chatId);
    }

    public Chat getChatBySenderOfChat(User senderOfChat) {
        return chatRepository.findBySenderOfChat(senderOfChat);
    }

    public Chat getChatByRecipientOfChat(User recipientOfChat) {
        return chatRepository.findByRecipientOfChat(recipientOfChat);
    }

    public Chat getChatByChatPlant(Plant chatPlant) {
        return chatRepository.findByChatPlant(chatPlant);
    }

    public Chat persistChat(Chat chat) {
        return chatRepository.save(chat);
    }

    public void deleteChatByChatId(Integer chatId) {
        chatRepository.deleteById(chatId);
    }
}
