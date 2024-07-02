package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Chat;
import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.repository.ChatRepository;
import com.example.sopra_pflanzenverkauf.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatConfirmPurchaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private PlantService plantService;

    @Autowired
    private LevelService levelService;

    @Autowired
    private ChatRepository chatRepository;

    /**
     * Handles GET requests targeted at the confirm purchase page.
     *
     * @return  the confirm purchase page
     */
    @RequestMapping(value = "/chatConfirmPurchase", method = RequestMethod.GET)
    public String showChatConfirmPurchase(@RequestParam(value = "chatId", required = false) String chatId,
                                          Model model) {

        Chat currentChat = null;

        for (Chat chat : chatService.getAllChats()) {
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

        Chat chat = chatRepository.findById(chatId)
                .orElse(null);

        if (chat == null) {
            return "error/errorIDDoNotExist";
        } else if (chat.getSenderOfChat() == currentUser) {
            model.addAttribute("specificChat", chat);

            model.addAttribute("chatId", chatId);

            model.addAttribute("chatPlant", chat.getChatPlant());

            System.out.println(chat.getMessagesInChat().size());

            model.addAttribute("currentUser", currentUser);

            return "chatConfirmPurchase";
        } else {
            return "error/errorChatConfirmPurchaseNotAllowed";
        }


    }

    @RequestMapping (value = "/chatConfirmPurchase/{chatId}", method = RequestMethod.POST)
    public String sendMessage (@PathVariable("chatId") Integer chatId,
                               Model model) {

        User currentUser = userService.getCurrentUser();

        Chat chat = chatService.getChatByChatId(chatId);

        Plant plant = chat.getChatPlant();


        if (chat.getRecipientAccept() == false) {
            chat.setSenderAccept(true);
            chatService.updateChat(chat);
        }

        if (chat.getRecipientAccept() == true) {

            chat.setSenderAccept(true);
            chatService.updateChat(chat);

            //Chats bzgl der Pflanze muss bei anderen gelöscht werden
            for (Chat chatOfAll : chatService.getAllChats()) {
                if (chatOfAll.getChatPlant() == chat.getChatPlant()) {
                    if (chatOfAll.getSenderOfChat() != currentUser) {
                        while (!chatOfAll.getMessagesInChat().isEmpty()) {
                            chatOfAll.getMessagesInChat().removeFirst();
                            chatService.updateChat(chatOfAll);
                        }
                        chatService.deleteChatByChatId(chatOfAll.getChatId());
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


            //Number of Purchased/Sold Plants anpassen
            chat.getSenderOfChat().setNumberOfPurchasedPlants(chat.getSenderOfChat().getNumberOfPurchasedPlants() + 1);
            chat.getRecipientOfChat().setNumberOfSoldPlants(chat.getRecipientOfChat().getNumberOfSoldPlants() + 1);

            //Levels anpassen
            if (plant.getBuyer().getPurchasedPlants().size() < 1) {
                plant.getBuyer().setBuyingLevel(levelService.getLevelByLevelname("Korn"));
                userService.updateBuyingLevel(plant.getBuyer());
            }

            if (plant.getBuyer().getPurchasedPlants().size() >= 1) {
                plant.getBuyer().setBuyingLevel(levelService.getLevelByLevelname("Sprössling"));
                userService.updateBuyingLevel(plant.getBuyer());
                if (plant.getBuyer().getPurchasedPlants().size() >= 5) {
                    plant.getBuyer().setBuyingLevel(levelService.getLevelByLevelname("Grüner-Daumen"));
                    userService.updateBuyingLevel(plant.getBuyer());
                    if (plant.getBuyer().getPurchasedPlants().size() >= 10) {
                        plant.getBuyer().setBuyingLevel(levelService.getLevelByLevelname("Pflanzenfreund"));
                        userService.updateBuyingLevel(plant.getBuyer());
                        if (plant.getBuyer().getPurchasedPlants().size() >= 15) {
                            plant.getBuyer().setBuyingLevel(levelService.getLevelByLevelname("Gärtner-Novize"));
                            userService.updateBuyingLevel(plant.getBuyer());
                            if (plant.getBuyer().getPurchasedPlants().size() >= 25) {
                                plant.getBuyer().setBuyingLevel(levelService.getLevelByLevelname("Blattmeister"));
                                userService.updateBuyingLevel(plant.getBuyer());
                                if (plant.getBuyer().getPurchasedPlants().size() >= 35) {
                                    plant.getBuyer().setBuyingLevel(levelService.getLevelByLevelname("Wurzelprofi"));
                                    userService.updateBuyingLevel(plant.getBuyer());
                                    if (plant.getBuyer().getPurchasedPlants().size() >= 50) {
                                        plant.getBuyer().setBuyingLevel(levelService.getLevelByLevelname("Pflanzenliebhaber"));
                                        userService.updateBuyingLevel(plant.getBuyer());
                                        if (plant.getBuyer().getPurchasedPlants().size() >= 75) {
                                            plant.getBuyer().setBuyingLevel(levelService.getLevelByLevelname("Botanik-Experte"));
                                            userService.updateBuyingLevel(plant.getBuyer());
                                            if (plant.getBuyer().getPurchasedPlants().size() >= 100) {
                                                plant.getBuyer().setBuyingLevel(levelService.getLevelByLevelname("Meister des Gartens"));
                                                userService.updateBuyingLevel(plant.getBuyer());
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
                userService.updateSellingLevel(plant.getSellerWhenSold());
            }
            if (plant.getSellerWhenSold().getSoldPlantsList().size() >= 1) {
                plant.getSellerWhenSold().setSellingLevel(levelService.getLevelByLevelname("Sprössling"));
                userService.updateSellingLevel(plant.getSellerWhenSold());
                if (plant.getSellerWhenSold().getSoldPlantsList().size() >= 5) {
                    plant.getSellerWhenSold().setSellingLevel(levelService.getLevelByLevelname("Grüner-Daumen"));
                    userService.updateSellingLevel(plant.getSellerWhenSold());
                    if (plant.getSellerWhenSold().getSoldPlantsList().size() >= 10) {
                        plant.getSellerWhenSold().setSellingLevel(levelService.getLevelByLevelname("Pflanzenfreund"));
                        userService.updateSellingLevel(plant.getSellerWhenSold());
                        if (plant.getSellerWhenSold().getSoldPlantsList().size() >= 15) {
                            plant.getSellerWhenSold().setSellingLevel(levelService.getLevelByLevelname("Gärtner-Novize"));
                            userService.updateSellingLevel(plant.getSellerWhenSold());
                            if (plant.getSellerWhenSold().getSoldPlantsList().size() >= 25) {
                                plant.getSellerWhenSold().setSellingLevel(levelService.getLevelByLevelname("Blattmeister"));
                                userService.updateSellingLevel(plant.getSellerWhenSold());
                                if (plant.getSellerWhenSold().getSoldPlantsList().size() >= 35) {
                                    plant.getSellerWhenSold().setSellingLevel(levelService.getLevelByLevelname("Wurzelprofi"));
                                    userService.updateSellingLevel(plant.getSellerWhenSold());
                                    if (plant.getSellerWhenSold().getSoldPlantsList().size() >= 50) {
                                        plant.getSellerWhenSold().setSellingLevel(levelService.getLevelByLevelname("Pflanzenliebhaber"));
                                        userService.updateSellingLevel(plant.getSellerWhenSold());
                                        if (plant.getSellerWhenSold().getSoldPlantsList().size() >= 75) {
                                            plant.getSellerWhenSold().setSellingLevel(levelService.getLevelByLevelname("Botanik-Experte"));
                                            userService.updateSellingLevel(plant.getSellerWhenSold());
                                            if (plant.getSellerWhenSold().getSoldPlantsList().size() >= 100) {
                                                plant.getSellerWhenSold().setSellingLevel(levelService.getLevelByLevelname("Meister des Gartens"));
                                                userService.updateSellingLevel(plant.getSellerWhenSold());
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
