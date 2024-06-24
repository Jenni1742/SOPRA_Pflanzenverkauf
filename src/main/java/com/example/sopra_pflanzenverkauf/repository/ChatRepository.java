package com.example.sopra_pflanzenverkauf.repository;

import com.example.sopra_pflanzenverkauf.entity.Chat;
import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

    Chat findByChatId(Integer chatId);

    Chat findBySenderOfChat(User sender);

    Chat findByRecipientOfChat(User recipient);

    Chat findByChatPlant(Plant chatPlant);
}
