package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Chat;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.service.ChatService;
import com.example.sopra_pflanzenverkauf.service.MessageService;
import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ChatService chatService;


    /**
     * Request Mapping after a successful login.
     *
     * @param model the model.
     * @return overview-page.
     */
    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public String showChatPage(Model model) {

        System.out.println("A");
        User currentUser = userService.getCurrentUser();

        List<Chat> chatList = currentUser.getReceivedChat();
        chatList.addAll(currentUser.getSentChat());
        model.addAttribute("chatList", chatList); //Auskommentieren

        System.out.println("B");


        /*
        if (!chatList.isEmpty()) {
            System.out.println("G");

            //Speichert alle leeren Chats in einer extra Liste

            List<Chat> emptyChats = new ArrayList<>();
            for (Chat chatEmpty : chatList) {
                if (chatEmpty.getMessagesInChat().size() == 0) {
                    emptyChats.add(chatEmpty);
                    chatList.remove(chatEmpty);
                }
            }

            if (chatList.isEmpty()) {
                model.addAttribute("chatList", emptyChats);
            } else {
                //Sortiert nach neuster Nachricht
                List<Chat> sortedChatList = new ArrayList<>();
                LocalDateTime timestampActualNewest = chatList.getFirst().getMessagesInChat().getLast().getTimestamp();
                LocalDateTime timestampOfChat;
                Chat actualNewestChat = chatList.getFirst();

                System.out.println("C");

                while (!chatList.isEmpty()) {
                    System.out.println("C111");

                    for (Chat chat : chatList) {
                        System.out.println("C222");

                        System.out.println("C777");

                        timestampOfChat = chat.getMessagesInChat().getLast().getTimestamp();
                        System.out.println("C333");
                        if (timestampActualNewest.isBefore(timestampOfChat)|| timestampActualNewest.equals(timestampOfChat)) {
                            System.out.println("C444");
                            timestampActualNewest = timestampOfChat;
                            actualNewestChat = chat;
                        }

                        /* Alt
                        if (!chat.getMessagesInChat().isEmpty()){
                            System.out.println("C777");

                            timestamp2 = chat.getMessagesInChat().getLast().getTimestamp();
                            System.out.println("C333");
                            if (timestamp.isBefore(timestamp2)|| timestamp.equals(timestamp2)) {
                                System.out.println("C444");
                                timestamp = timestamp2;
                                actualNewestChat = chat;
                            }
                        }else{
                            System.out.println("C555");
                            actualNewestChat = chat;
                        }*/

/*
                    }


                    System.out.println("C666");
                    sortedChatList.add(actualNewestChat);
                    chatList.remove(actualNewestChat);
                }
                // Fügt ans Ende der Sortierten Liste die leeren Chats an
/*
                System.out.println("D");
                sortedChatList.addAll(emptyChats);
                model.addAttribute("chatList", sortedChatList);
            }

        }else {
            System.out.println("F");
            model.addAttribute("chatList", chatList);
        }
*/

        model.addAttribute("currentUser", currentUser);

        return "chat";
    }

    /*
    @PostMapping("/chat/{chatId}")
    public String showSpecificChat(@PathVariable("chatId") Integer chatId,
                           Model model) {

        User currentUser = userService.getCurrentUser();

        Chat chat = chatService.getChatByChatId(chatId);

        model.addAttribute("specificChat", chat);


        model.addAttribute("currentUser", currentUser);

        return "chat";
    }*/

}