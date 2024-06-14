package com.example.sopra_pflanzenverkauf.service;

import com.example.sopra_pflanzenverkauf.entity.MessageJK;
import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.repository.MessageJKRepository;
import com.example.sopra_pflanzenverkauf.repository.PlantRepository;
import com.example.sopra_pflanzenverkauf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageJKService {

    @Autowired //Annotation zur Markierung von Objekten für die Spring Dependency Injection
    private MessageJKRepository messageJKRepository;

    public MessageJKService(MessageJKRepository messageJKRepository) {
        this.messageJKRepository = messageJKRepository;
    }

    public void updateMessageJK(MessageJK messageJK) {messageJKRepository.save(messageJK);}

    public List<MessageJK> getAllMessages() {
        return messageJKRepository.findAll();
    }

    public MessageJK getMessageJKByMessageId(Integer messageId) {
        return messageJKRepository.findByMessageId(messageId);
    }

    public MessageJK getMessageJKBySender(User sender) {
        return messageJKRepository.findBySender(sender);
    }
    public MessageJK getMessageJKByRecipient(User recipient) {
        return messageJKRepository.findByRecipient(recipient);
    }

    public MessageJK persistMessage(MessageJK messageJK) {
        return messageJKRepository.save(messageJK);
    }

    public void deleteMessageByMessageId(Integer messageId) {
        messageJKRepository.deleteById(messageId);
    }


}
