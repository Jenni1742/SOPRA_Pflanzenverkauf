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

        Plant plant = null;

        for (Plant plantOfList : plantService.getAllPlants()) {
            if (plantOfList.getPlantname().equals(plantToBuyName)) {
                if (plantOfList.getPlantId().toString().equals(plantToBuyID)) {
                    plant = plantOfList;
                }
            }
        }


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



        if(chat1 == null) {
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
            model.addAttribute("specificChat", chat1 );
            model.addAttribute("chatId", chat1.getChatId());
            model.addAttribute("chatPlant", chat1.getChatPlant());

            List<Message> messages = chat1.getMessagesInChat();
            Collections.reverse(messages);
            model.addAttribute("messages", messages);

        }
        model.addAttribute("currentUser", userService.getCurrentUser());



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

            System.out.println(chat.getMessagesInChat().size());

            List<Message> messages = chat.getMessagesInChat();
            Collections.reverse(messages);
            model.addAttribute("messages", messages);


            model.addAttribute("currentUser", currentUser);

            return "chatSpecific";
        } else {
            return "error/errorSpecificChatNoAccess";
        }
    }

    @RequestMapping (value = "/chatSpecific/{chatId}", method = RequestMethod.POST)
    public String sendMessage (@PathVariable("chatId") Integer chatId,
                               @RequestParam("content") String content,
                               Model model){

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

        return "redirect:/chatSpecific/{chatId}";
    }

}
