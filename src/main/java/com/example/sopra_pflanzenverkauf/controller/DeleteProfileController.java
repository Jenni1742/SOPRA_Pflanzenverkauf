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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//Hallo

@Controller
public class DeleteProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private PlantService plantService;

    @Autowired
    private MessageJKService messageJKService;

    @Autowired
    private ChatJKService chatJKService;

    /**
     * Handles GET requests targeted at the delete Userprofile page.
     *
     * @return  the delete Userprofile page
     */
    @RequestMapping(value = "/deleteProfile", method = RequestMethod.GET)
    public String showRegisterPage(Model model) {

        return "deleteProfile";
    }

    @PostMapping(path = "/deleteProfile")
    public String deleteProfile(@RequestParam("password") String password,
                                Model model) {

        System.out.println("A");
        User currentUser = userService.getCurrentUser();

        System.out.println("B");
        if (bCryptPasswordEncoder.matches(password, currentUser.getPassword())) {

            System.out.println("C");
            //Ändert bei anderen Usern den Verkäufer zu unbekannt in der Liste der gekauften Pflanzen
            for (Plant plant: currentUser.getSoldPlantsList()) {
                if (plant.getBuyer() == null) {
                    plantService.deletePlantByPlantId(plant.getPlantId());
                } else {
                    for (User user:userService.findAllUsers()) {
                        if (user.getPurchasedPlants().contains(plant)) {
                            Integer index = user.getPurchasedPlants().indexOf(plant);
                            user.getPurchasedPlants().get(index).setSellerWhenSold(null);
                            userService.updatePurchasedPlants(user);
                        }
                    }
                }
            }



            System.out.println("D");

            //Entfernt bei anderen Usern die Pflanzen aus der Merkliste
            for (Plant plant: currentUser.getPlantsToSell()) {
                for (User user:userService.findAllUsers()) {
                    System.out.println("a");
                    if (user.getWishlistPlants().contains(plant)) {
                        System.out.println("b");
                        user.getWishlistPlants().remove(plantService.getPlantByPlantId(plant.getPlantId()));
                        System.out.println("c");
                        userService.updateWishlist(user);
                        System.out.println("d");
                    }
                }
            }


            System.out.println("E");
            //Löscht alle zum Verkauf stehenden Pflanzen
            while (!currentUser.getPlantsToSell().isEmpty()) {
                plantService.deletePlantByPlantId(currentUser.getPlantsToSell().removeFirst().getPlantId());
            }

            System.out.println("F");
            //Leert die eigene Merkliste
            while (!currentUser.getWishlistPlants().isEmpty()) {
                currentUser.getWishlistPlants().clear();
                userService.updateWishlist(currentUser);
            }
            System.out.println("G");

            //Ändert bei anderen Usern den Käufer zu unbekannt in der Liste der verkauften Pflanzen
            while (!currentUser.getPurchasedPlants().isEmpty()) {
                if (currentUser.getPurchasedPlants().getFirst().getSeller() == null) {
                    Integer plantId = currentUser.getPurchasedPlants().getFirst().getPlantId();
                    currentUser.getPurchasedPlants().removeFirst();
                    plantService.deletePlantByPlantId(plantId);
                } else {
                    currentUser.getPurchasedPlants().getFirst().setBuyer(null);
                    plantService.updateBuyer(currentUser.getPurchasedPlants().getFirst());
                    currentUser.getPurchasedPlants().removeFirst();
                }

            }


            System.out.println("1");

            //Löscht alle gesendeten Nachrichten
            while (!currentUser.getSentMessages().isEmpty()) {
                MessageJK message = currentUser.getSentMessages().getFirst();
                currentUser.getSentMessages().removeFirst();
                messageJKService.deleteMessageByMessageId(message.getMessageId());
            }
            System.out.println("2");

            //Löscht alle empfangenen Nachrichten
            while (!currentUser.getReceivedMessages().isEmpty()) {
                MessageJK message = currentUser.getReceivedMessages().getFirst();
                currentUser.getReceivedMessages().removeFirst();
                messageJKService.deleteMessageByMessageId(message.getMessageId());
            }
            System.out.println("3");

            //Löscht alle gesendeten Chats
            while (!currentUser.getSentChat().isEmpty()) {
                ChatJK chat = currentUser.getSentChat().getFirst();
                currentUser.getSentChat().removeFirst();
                chatJKService.deleteChatByChatId(chat.getChatId());
            }
            System.out.println("4");

            //Löscht alle empfangenen Chats
            while (!currentUser.getReceivedChat().isEmpty()) {
                ChatJK chat = currentUser.getReceivedChat().getFirst();
                currentUser.getReceivedChat().removeFirst();
                chatJKService.deleteChatByChatId(chat.getChatId());
            }

            System.out.println("Hallo");

            //userService.persistUser(currentUser);
            userService.deleteUserById(currentUser.getUserId());
            model.addAttribute("userprofilGelöscht", "Das Userprofil wurde erfolgreich gelöscht.");
        } else {
            model.addAttribute("PasswortIstNichtKorrekt", "Das Passwort ist inkorrekt. Das Userprofil wurde nicht gelöscht.");
            return "deleteProfile";
        }

        return "login";
    }

}
