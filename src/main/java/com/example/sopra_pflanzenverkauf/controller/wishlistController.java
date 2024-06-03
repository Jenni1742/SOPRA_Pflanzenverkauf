package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.service.PlantService;
import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class wishlistController {

    @Autowired
    private UserService userService;

    @Autowired
    private PlantService plantService;


    @RequestMapping(value = "/wishlist", method = RequestMethod.GET)
    public String showMyWishlist(Map<String, Object> model) {

        model.put("currentUser", userService.getCurrentUser());

        return "wishlist";
    }

    @PostMapping(path = "/wishlist")
    public String removePlant(@RequestParam("plant") String plant,
                                 Map<String, Object> model) {
        User currentUser = userService.getCurrentUser();

        currentUser.getWishlistPlants().remove(plantService.getPlantByName(plant));
        userService.updateWishlist(currentUser);
        model.put("message", "Pflanze erfolgreich von Merkliste entfernt.");

        model.put("currentUser", currentUser);
        return "wishlist";
    }

}
