package com.example.sopra_pflanzenverkauf.service;

import com.example.sopra_pflanzenverkauf.dto.MessageDto;
import com.example.sopra_pflanzenverkauf.entity.Message;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Message saveMessage(MessageDto messageDto) {
        // TODO use userID from authentication
        Message message = generateMessage(messageDto, "1");
        return messageRepository.save(message);
    }
    private Message generateMessage(MessageDto messageDto, String senderId) {
        Message message = new Message();
        message.setContent(messageDto.getContent());
        message.setTimestamp(LocalDateTime.now());
        message.setRecipient(messageDto.getRecipientId());
        message.setContent(messageDto.getContent());
        message.setSender(senderId);
        return message;
    }

}
