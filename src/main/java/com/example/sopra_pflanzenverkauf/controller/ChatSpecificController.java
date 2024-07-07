package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Chat;
import com.example.sopra_pflanzenverkauf.entity.Message;
import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.repository.ChatRepository;
import com.example.sopra_pflanzenverkauf.service.ChatService;
import com.example.sopra_pflanzenverkauf.service.MessageService;
import com.example.sopra_pflanzenverkauf.service.PlantService;
import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class ChatSpecificController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ChatService chatService;
    @Autowired
    private PlantService plantService;
    @Autowired
    private ChatRepository chatRepository;

    @RequestMapping (value = "/chatSpecific", method = RequestMethod.GET)
    public String getChatPage(@RequestParam(value = "recipientUsername", required = false) String recipientUsername,
                              @RequestParam(value = "plantToBuyID", required = false) String plantToBuyID,
                              @RequestParam(value = "plantToBuyName", required = false) String plantToBuyName,
                              Model model) {

        System.out.println("XXX");
        Plant plant = null;

        for (Plant plantOfList : plantService.getAllPlants()) {
            if (plantOfList.getPlantname().equals(plantToBuyName)) {
                if (plantOfList.getPlantId().toString().equals(plantToBuyID)) {
                    plant = plantOfList;
                }
            }
        }
        System.out.println("YYY");


        User recipient = userService.getUserByUsername(recipientUsername);
        User currentUser = userService.getCurrentUser();

        Chat chat1 = null;

        for (Chat chat: chatService.getAllChats()) {
            if (chat.getRecipientOfChat() == recipient && chat.getSenderOfChat() == currentUser && chat.getChatPlant() == plant){
                chat1 = chat;
            }
        }

        /*for (Chat chat:chatService.getAllChats()) {
            if (chat.getRecipientOfChat() == recipient && chat.getSenderOfChat() == currentUser){
                chat1 = chat;
            }
            if (chat.getRecipientOfChat() == currentUser && chat.getSenderOfChat() == recipient ){
                chat1 = chat;
            }
        }*/

        System.out.println("ZZZ");


        if(chat1 == null) {
            System.out.println("NNN");
        Chat chatObject = new Chat();
        chatObject.setRecipientOfChat(recipient);
        chatObject.setSenderOfChat(currentUser);
        chatObject.setChatPlant(plant);
        chatService.persistChat(chatObject);
        model.addAttribute("chatId", chatObject.getChatId());
        model.addAttribute("specificChat", chatObject);
        model.addAttribute("chatPlant", plant);



            List<Message> messages = chatObject.getMessagesInChat();
            Collections.reverse(messages);
            model.addAttribute("messages", messages);



        } else {
            System.out.println("EEE");
            model.addAttribute("specificChat", chat1 );
            model.addAttribute("chatId", chat1.getChatId());
            model.addAttribute("chatPlant", chat1.getChatPlant());

            List<Message> messages = chat1.getMessagesInChat();
            Collections.reverse(messages);
            model.addAttribute("messages", messages);

        }
        model.addAttribute("currentUser", userService.getCurrentUser());

        System.out.println("AAA");

        List<Chat> chatList = currentUser.getReceivedChat();
        chatList.addAll(currentUser.getSentChat());
        model.addAttribute("chatList", chatList); //Auskommentieren

        System.out.println("BBB");

        /*
        List<Chat> sortedChatList = new ArrayList<>();
        LocalDateTime timestamp = chatList.getFirst().getMessagesInChat().getLast().getTimestamp();
        System.out.println("CCC");
        LocalDateTime timestamp2;
        Chat newestChat = chatList.getFirst();
        System.out.println("DDD");

        while (!chatList.isEmpty()) {
            System.out.println("h111");
            for (Chat chat : chatList) {
                System.out.println("h222");

                if (!chat.getMessagesInChat().isEmpty()){
                    System.out.println("h777");
                    timestamp2 = chat.getMessagesInChat().getLast().getTimestamp();
                    System.out.println("h333");
                    if (timestamp.isBefore(timestamp2)|| timestamp.equals(timestamp2)) {
                        System.out.println("h444");
                        timestamp = timestamp2;
                        newestChat = chat;
                    }
                }else{
                    System.out.println("h555");
                    newestChat = chat;
                }
            }
            System.out.println("h666");
            sortedChatList.add(newestChat);
            chatList.remove(newestChat);
        }
        model.addAttribute("chatList", sortedChatList);*/


        return "redirect:/chat";
    }

    @RequestMapping (value = "/chatSpecific/{chatId}", method = RequestMethod.GET)
    public String showSpecificChat(@PathVariable("chatId") Integer chatId,
                                   Model model) {

        User currentUser = userService.getCurrentUser();

        Chat chat = chatRepository.findById(chatId)
                .orElse(null);

        if (chat == null) {
            return "error/errorIDDoNotExist";
        } else if (chat.getRecipientOfChat() == currentUser || chat.getSenderOfChat() == currentUser){
            model.addAttribute("specificChat", chat);

            model.addAttribute("chatId", chatId);

            model.addAttribute("chatPlant", chat.getChatPlant());

            System.out.println("LOL");
            System.out.println(chat.getMessagesInChat().size());

            List<Message> messages = chat.getMessagesInChat();
            Collections.reverse(messages);
            model.addAttribute("messages", messages);


            model.addAttribute("currentUser", currentUser);

            List<Chat> chatList = currentUser.getReceivedChat();
            chatList.addAll(currentUser.getSentChat());
            model.addAttribute("chatList", chatList); //Auskommentieren

            /*
            List<Chat> sortedChatList = new ArrayList<>();

            sortedChatList.add(chatList.get(chatList.indexOf(chat)));
            chatList.remove(chat);


            LocalDateTime timestamp = chatList.getFirst().getMessagesInChat().getLast().getTimestamp();
            LocalDateTime timestamp2;
            Chat newestChat = chatList.getFirst();
            while (!chatList.isEmpty()) {
                System.out.println("G111");
                for (Chat chat3 : chatList) {
                    System.out.println("g222");

                    if (!chat3.getMessagesInChat().isEmpty()){
                        System.out.println("g777");
                        timestamp2 = chat3.getMessagesInChat().getLast().getTimestamp();
                        System.out.println("g333");
                        if (timestamp.isBefore(timestamp2)|| timestamp.equals(timestamp2)) {
                            System.out.println("g444");
                            timestamp = timestamp2;
                            newestChat = chat3;
                        }
                    }else{
                        System.out.println("g555");
                        newestChat = chat3;
                    }
                }
                System.out.println("g666");
                sortedChatList.add(newestChat);
                chatList.remove(newestChat);
            }
            model.addAttribute("chatList", sortedChatList);*/

            return "chatSpecific";
        } else {
            return "error/errorSpecificChatNoAccess";
        }
    }

    @RequestMapping (value = "/chatSpecific/{chatId}", method = RequestMethod.POST)
    public String sendMessage (@PathVariable("chatId") Integer chatId,
                               @RequestParam("content") String content,
                               Model model){

        User currentUser = userService.getCurrentUser();
        Chat chat = chatService.getChatByChatId(chatId);

        Message message = new Message();

        if(chat.getRecipientOfChat() == userService.getCurrentUser()){
            message.setRecipient(chat.getSenderOfChat());
        } else {
            message.setRecipient(chat.getRecipientOfChat());
        }

        User sender = userService.getCurrentUser();
        message.setSender(sender);

        message.setContent(content);

        message.setAssociatedChat(chat);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        message.setTimestamp(timestamp.toLocalDateTime());

        messageService.updateMessage(message);
        chatService.updateChat(chat);

        List<Message> messages = chat.getMessagesInChat();
        Collections.reverse(messages);
        model.addAttribute("messages", messages);

        List<Chat> chatList = currentUser.getReceivedChat();
        chatList.addAll(currentUser.getSentChat());
        model.addAttribute("chatList", chatList); //Auskommentieren

        /*
        List<Chat> sortedChatList = new ArrayList<>();


        sortedChatList.add(chatList.get(chatList.indexOf(chat)));
        chatList.remove(chat);

        LocalDateTime timestamp3 = chatList.getFirst().getMessagesInChat().getLast().getTimestamp();
        LocalDateTime timestamp2;
        Chat newestChat = chatList.getFirst();
        while (!chatList.isEmpty()) {
            System.out.println("P111");
            for (Chat chat3 : chatList) {
                System.out.println("P222");

                if (!chat3.getMessagesInChat().isEmpty()){
                    System.out.println("P777");
                    timestamp2 = chat3.getMessagesInChat().getLast().getTimestamp();
                    System.out.println("P333");
                    if (timestamp3.isBefore(timestamp2)|| timestamp3.equals(timestamp2)) {
                        System.out.println("P444");
                        timestamp3 = timestamp2;
                        newestChat = chat3;
                    }
                }else{
                    System.out.println("P555");
                    newestChat = chat3;
                }
            }
            System.out.println("P666");
            sortedChatList.add(newestChat);
            chatList.remove(newestChat);
        }
        model.addAttribute("chatList", sortedChatList);*/

        return "redirect:/chatSpecific/{chatId}";
    }

}
