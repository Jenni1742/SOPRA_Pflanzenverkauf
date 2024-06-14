package com.example.sopra_pflanzenverkauf.repository;

import com.example.sopra_pflanzenverkauf.entity.Message;
import com.example.sopra_pflanzenverkauf.entity.MessageJK;
import com.example.sopra_pflanzenverkauf.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageJKRepository extends JpaRepository<MessageJK, Integer> {

    MessageJK findByMessageId(Integer messageId);

    MessageJK findBySender(User sender);

    MessageJK findByRecipient(User recipient);

}
