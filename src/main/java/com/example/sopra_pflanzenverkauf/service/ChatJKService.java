package com.example.sopra_pflanzenverkauf.service;

import com.example.sopra_pflanzenverkauf.entity.ChatJK;
import com.example.sopra_pflanzenverkauf.entity.MessageJK;
import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.repository.ChatJKRepository;
import com.example.sopra_pflanzenverkauf.repository.MessageJKRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatJKService {

    @Autowired //Annotation zur Markierung von Objekten für die Spring Dependency Injection
    private ChatJKRepository chatJKRepository;

    public ChatJKService(ChatJKRepository chatJKRepository) {
        this.chatJKRepository = chatJKRepository;
    }

    public void updateChatJK(ChatJK chatJK) {chatJKRepository.save(chatJK);}

    public List<ChatJK> getAllChats() {
        return chatJKRepository.findAll();
    }

    public ChatJK getChatJKByChatId(Integer chatId) {
        return chatJKRepository.findByChatId(chatId);
    }

    public ChatJK getChatJKBySenderOfChat(User senderOfChat) {
        return chatJKRepository.findBySenderOfChat(senderOfChat);
    }

    public ChatJK getChatJKByRecipientOfChat(User recipientOfChat) {
        return chatJKRepository.findByRecipientOfChat(recipientOfChat);
    }

    public ChatJK getChatJKByChatPlant(Plant chatPlant) {
        return chatJKRepository.findByChatPlant(chatPlant);
    }

    public ChatJK persistChat(ChatJK chatJK) {
        return chatJKRepository.save(chatJK);
    }

    public void deleteChatByChatId(Integer chatId) {
        chatJKRepository.deleteById(chatId);
    }
}
