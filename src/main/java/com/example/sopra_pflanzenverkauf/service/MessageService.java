package com.example.sopra_pflanzenverkauf.service;

import com.example.sopra_pflanzenverkauf.entity.Message;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired //Annotation zur Markierung von Objekten für die Spring Dependency Injection
    private MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void updateMessage(Message message) {
        messageRepository.save(message);}

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessageByMessageId(Integer messageId) {
        return messageRepository.findByMessageId(messageId);
    }

    public Message getMessageBySender(User sender) {
        return messageRepository.findBySender(sender);
    }
    public Message getMessageByRecipient(User recipient) {
        return messageRepository.findByRecipient(recipient);
    }

    public Message persistMessage(Message message) {
        return messageRepository.save(message);
    }

    public void deleteMessageByMessageId(Integer messageId) {
        messageRepository.deleteById(messageId);
    }


}
