package com.example.sopra_pflanzenverkauf.service;

import com.example.sopra_pflanzenverkauf.dto.MessageDto;
import com.example.sopra_pflanzenverkauf.entity.Message;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserService userService;

    public Message saveMessage(MessageDto messageDto) {
        User sender = userService.getCurrentUser();
        Message message = generateMessage(messageDto, sender);
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessageById(Long id) {
        return messageRepository.findById(id).orElseThrow(() -> new RuntimeException("Message not found"));
    }

    private Message generateMessage(MessageDto messageDto, User sender) {
        Message message = new Message();
        message.setContent(messageDto.getContent());
        message.setTimestamp(LocalDateTime.now());
        message.setRecipient(messageDto.getRecipientId());
        message.setSender(sender.getUserId().toString());
        return message;
    }
}
