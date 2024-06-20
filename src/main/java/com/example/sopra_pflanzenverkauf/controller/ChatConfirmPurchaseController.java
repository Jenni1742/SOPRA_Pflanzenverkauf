package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.ChatJK;
import com.example.sopra_pflanzenverkauf.entity.MessageJK;
import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;

@Controller
public class ChatConfirmPurchaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageJKService messageJKService;

    @Autowired
    private ChatJKService chatJKService;

    @Autowired
    private PlantService plantService;

    @Autowired
    private LevelService levelService;

    /**
     * Handles GET requests targeted at the confirm purchase page.
     *
     * @return  the confirm purchase page
     */
    @RequestMapping(value = "/chatConfirmPurchase", method = RequestMethod.GET)
    public String showChatConfirmPurchase(@RequestParam(value = "chatId", required = false) String chatId,
                                          Model model) {

        ChatJK currentChat = null;

        for (ChatJK chat : chatJKService.getAllChats()) {
            if (chat.getChatId().toString().equals(chatId)) {
                currentChat = chat;
            }
        }

        model.addAttribute("chat", currentChat);

        model.addAttribute("currentUser", userService.getCurrentUser());

        return "chatConfirmPurchase";
    }


    @RequestMapping (value = "/chatConfirmPurchase/{chatId}", method = RequestMethod.GET)
    public String showPurchase(@PathVariable("chatId") Integer chatId,
                                   Model model) {

        User currentUser = userService.getCurrentUser();

        ChatJK chat = chatJKService.getChatJKByChatId(chatId);

        model.addAttribute("specificChat", chat);

        model.addAttribute("chatId", chatId);

        model.addAttribute("chatPlant", chat.getChatPlant());

        System.out.println(chat.getMessagesInChat().size());

        model.addAttribute("currentUser", currentUser);

        return "chatConfirmPurchase";
    }

    @RequestMapping (value = "/chatConfirmPurchase/{chatId}", method = RequestMethod.POST)
    public String sendMessage (@PathVariable("chatId") Integer chatId,
                               Model model) {

        User currentUser = userService.getCurrentUser();

        ChatJK chat = chatJKService.getChatJKByChatId(chatId);

        Plant plant = chat.getChatPlant();


        if (chat.getRecipientAccept() == false) {
            chat.setSenderAccept(true);
            chatJKService.updateChatJK(chat);
        }

        if (chat.getRecipientAccept() == true) {

            chat.setSenderAccept(true);
            chatJKService.updateChatJK(chat);

            //Chats bzgl der Pflanze muss bei anderen gelöscht werden
            for (ChatJK chatOfAll : chatJKService.getAllChats()) {
                if (chatOfAll.getChatPlant() == chat.getChatPlant()) {
                    if (chatOfAll.getSenderOfChat() != currentUser) {
                        while (!chatOfAll.getMessagesInChat().isEmpty()) {
                            chatOfAll.getMessagesInChat().removeFirst();
                            chatJKService.updateChatJK(chatOfAll);
                        }
                        chatJKService.deleteChatByChatId(chatOfAll.getChatId());
                    }
                }
            }

            //Pflanzen Käufe rund Verkäufer eintragen und als Verkauft angeben
            User seller = plant.getSeller();
            plant.setSellerWhenSold(seller);
            plant.setSeller(null);
            plant.setBuyer(chat.getSenderOfChat());
            plant.setSold(true);
            plantService.updatePlant(plant);


            //Levels anpassen
            if (plant.getBuyer().getPurchasedPlants().size() < 1) {
                plant.getBuyer().setBuyingLevel(levelService.getLevelByLevelname("Korn"));
                if (plant.getBuyer().getPurchasedPlants().size() >= 1) {
                    plant.getBuyer().setBuyingLevel(levelService.getLevelByLevelname("Sprössling"));
                    if (plant.getBuyer().getPurchasedPlants().size() >= 5) {
                        plant.getBuyer().setBuyingLevel(levelService.getLevelByLevelname("Grüner-Daumen"));
                        if (plant.getBuyer().getPurchasedPlants().size() >= 10) {
                            plant.getBuyer().setBuyingLevel(levelService.getLevelByLevelname("Pflanzenfreund"));
                            if (plant.getBuyer().getPurchasedPlants().size() >= 15) {
                                plant.getBuyer().setBuyingLevel(levelService.getLevelByLevelname("Gärtner-Novize"));
                                if (plant.getBuyer().getPurchasedPlants().size() >= 25) {
                                    plant.getBuyer().setBuyingLevel(levelService.getLevelByLevelname("Blattmeister"));
                                    if (plant.getBuyer().getPurchasedPlants().size() >= 35) {
                                        plant.getBuyer().setBuyingLevel(levelService.getLevelByLevelname("Wurzelprofi"));
                                        if (plant.getBuyer().getPurchasedPlants().size() >= 50) {
                                            plant.getBuyer().setBuyingLevel(levelService.getLevelByLevelname("Pflanzenliebhaber"));
                                            if (plant.getBuyer().getPurchasedPlants().size() >= 75) {
                                                plant.getBuyer().setBuyingLevel(levelService.getLevelByLevelname("Botanik-Experte"));
                                                if (plant.getBuyer().getPurchasedPlants().size() >= 100) {
                                                    plant.getBuyer().setBuyingLevel(levelService.getLevelByLevelname("Meister des Gartens"));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }


            if (plant.getSellerWhenSold().getSoldPlantsList().size() < 1) {
                plant.getSellerWhenSold().setSellingLevel(levelService.getLevelByLevelname("Korn"));
                if (plant.getSellerWhenSold().getSoldPlantsList().size() >= 1) {
                    plant.getSellerWhenSold().setSellingLevel(levelService.getLevelByLevelname("Sprössling"));
                    if (plant.getSellerWhenSold().getSoldPlantsList().size() >= 1) {
                        plant.getSellerWhenSold().setSellingLevel(levelService.getLevelByLevelname("Grüner-Daumen"));
                        if (plant.getSellerWhenSold().getSoldPlantsList().size() >= 1) {
                            plant.getSellerWhenSold().setSellingLevel(levelService.getLevelByLevelname("Pflanzenfreund"));
                            if (plant.getSellerWhenSold().getSoldPlantsList().size() >= 1) {
                                plant.getSellerWhenSold().setSellingLevel(levelService.getLevelByLevelname("Gärtner-Novize"));
                                if (plant.getSellerWhenSold().getSoldPlantsList().size() >= 1) {
                                    plant.getSellerWhenSold().setSellingLevel(levelService.getLevelByLevelname("Blattmeister"));
                                    if (plant.getSellerWhenSold().getSoldPlantsList().size() >= 1) {
                                        plant.getSellerWhenSold().setSellingLevel(levelService.getLevelByLevelname("Wurzelprofi"));
                                        if (plant.getSellerWhenSold().getSoldPlantsList().size() >= 1) {
                                            plant.getSellerWhenSold().setSellingLevel(levelService.getLevelByLevelname("Pflanzenliebhaber"));
                                            if (plant.getSellerWhenSold().getSoldPlantsList().size() >= 1) {
                                                plant.getSellerWhenSold().setSellingLevel(levelService.getLevelByLevelname("Botanik-Experte"));
                                                if (plant.getSellerWhenSold().getSoldPlantsList().size() >= 1) {
                                                    plant.getSellerWhenSold().setSellingLevel(levelService.getLevelByLevelname("Meister des Gartens"));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }




        }


        return "redirect:/chatSpecific/{chatId}";
    }

}
