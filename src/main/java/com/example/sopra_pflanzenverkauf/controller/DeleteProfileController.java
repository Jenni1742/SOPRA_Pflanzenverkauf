package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.User;
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

        User currentUser = userService.getCurrentUser();

        if (bCryptPasswordEncoder.matches(password, currentUser.getPassword())) {
            while (!currentUser.getPlantsToSell().isEmpty()) {
                plantService.deletePlantByPlantId(currentUser.getPlantsToSell().removeFirst().getPlantId());
            }

            while (!currentUser.getWishlistPlants().isEmpty()) {
                currentUser.getWishlistPlants().clear();
                userService.updateWishlist(currentUser);
            }

            while (!currentUser.getPurchasedPlants().isEmpty()) {
                currentUser.getPurchasedPlants().getFirst().setBuyer(null);
                plantService.updateBuyer(currentUser.getPurchasedPlants().getFirst());
                currentUser.getPurchasedPlants().removeFirst();
            }

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
