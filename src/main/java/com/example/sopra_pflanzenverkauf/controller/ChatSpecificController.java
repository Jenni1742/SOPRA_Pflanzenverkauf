package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.ChatJK;
import com.example.sopra_pflanzenverkauf.entity.MessageJK;
import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.service.ChatJKService;
import com.example.sopra_pflanzenverkauf.service.MessageJKService;
import com.example.sopra_pflanzenverkauf.service.PlantService;
import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;

@Controller
public class ChatSpecificController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageJKService messageJKService;

    @Autowired
    private ChatJKService chatJKService;
    @Autowired
    private PlantService plantService;

    @RequestMapping (value = "/chatSpecific", method = RequestMethod.GET)
    public String getChatPage(@RequestParam(value = "recipientUsername", required = false) String recipientUsername,
                              @RequestParam(value = "plantToBuyID", required = false) String plantToBuyID,
                              @RequestParam(value = "plantToBuyName", required = false) String plantToBuyName,
                              Model model) {

        Plant plant;

        for (Plant plantOfList : plantService.getAllPlants()) {
            if (plantOfList.getPlantname().equals(plantToBuyName)) {
                if (plantOfList.getPlantId().toString().equals(plantToBuyID)) {
                    plant = plantOfList;
                }
            }
        }



        User recipient = userService.getUserByUsername(recipientUsername);
        User currentUser = userService.getCurrentUser();

        ChatJK chatJK = null;

        for (ChatJK chat:chatJKService.getAllChats()) {
            if (chat.getRecipientOfChat() == recipient && chat.getSenderOfChat() == currentUser ){
                chatJK = chat;
            }
            if (chat.getRecipientOfChat() == currentUser && chat.getSenderOfChat() == recipient ){
                chatJK = chat;
            }
        }

        if(chatJK == null) {
        ChatJK chatjk = new ChatJK();
        chatjk.setRecipientOfChat(recipient);
        chatjk.setSenderOfChat(currentUser);
        chatJKService.persistChat(chatjk);
        model.addAttribute("chatId", chatjk.getChatId());
        model.addAttribute("specificChat", chatjk);
        } else {
            model.addAttribute("specificChat", chatJK );
            model.addAttribute("chatId", chatJK.getChatId());
        }
        model.addAttribute("currentUser", userService.getCurrentUser());

        return "redirect:/chatJK";
    }

    @RequestMapping (value = "/chatSpecific/{chatId}", method = RequestMethod.GET)
    public String showSpecificChat(@PathVariable("chatId") Integer chatId,
                                   Model model) {

        User currentUser = userService.getCurrentUser();

        ChatJK chat = chatJKService.getChatJKByChatId(chatId);

        model.addAttribute("specificChat", chat);

        model.addAttribute("chatId", chatId);

        System.out.println(chat.getMessagesInChat().size());


        model.addAttribute("currentUser", currentUser);

        return "chatSpecific";
    }

    @RequestMapping (value = "/chatSpecific/{chatId}", method = RequestMethod.POST)
    public String sendMessage (@PathVariable("chatId") Integer chatId,
                               @RequestParam("content") String content,
                               Model model){

        ChatJK chat = chatJKService.getChatJKByChatId(chatId);

        MessageJK message = new MessageJK();

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

        messageJKService.updateMessageJK(message);
        chatJKService.updateChatJK(chat);

        return "redirect:/chatSpecific/{chatId}";
    }

}
