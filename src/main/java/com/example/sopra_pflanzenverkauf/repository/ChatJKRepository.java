package com.example.sopra_pflanzenverkauf.repository;

import com.example.sopra_pflanzenverkauf.entity.ChatJK;
import com.example.sopra_pflanzenverkauf.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatJKRepository extends JpaRepository<ChatJK, Integer> {

    ChatJK findByChatId(Integer chatId);

    ChatJK findBySenderOfChat(User sender);

    ChatJK findByRecipientOfChat(User recipient);
}
