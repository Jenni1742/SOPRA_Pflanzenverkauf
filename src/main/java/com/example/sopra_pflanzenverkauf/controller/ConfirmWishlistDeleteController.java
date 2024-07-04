package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.repository.PlantRepository;
import com.example.sopra_pflanzenverkauf.service.PlantService;
import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class ConfirmWishlistDeleteController {

    @Autowired
    private UserService userService;

    @Autowired
    private PlantService plantService;

    @Autowired
    private PlantRepository plantRepository;

    @GetMapping("/confirmWishlistDelete/{id}")
    public String openConfirmBoost(@PathVariable("id") Integer plantId,
                                   Model model) {

        model.addAttribute("plantId", plantId);
        model.addAttribute("currentUser", userService.getCurrentUser());

        Plant plant = plantRepository.findById(plantId)
                .orElse(null);

        model.addAttribute("plant", plant);

        if (plant == null) {
            return "error/errorIDDoNotExist";
        } else if (userService.getCurrentUser() == plant.getSeller() && plant.getBooster() == false){
            return "confirmWishlistDelete";
        } else {
            return "error/errorConfirmWishlistDelete";
        }

    }


    @PostMapping("/confirmWishlistDelete/{id}")
    public String confirmBoost(@PathVariable("id") Integer plantId,
                               Model model) {

        Plant plant = plantService.getPlantByPlantId(plantId);
        User currentUser = userService.getCurrentUser();
        System.out.println(plantService.getPlantByPlantId(plantId).getPlantname());

        if (currentUser != null && plant != null) {
            currentUser.getWishlistPlants().add(plantService.getPlantByPlantId(plantId));

            userService.updateWishlist(currentUser);
        }

        model.addAttribute("currentUser", currentUser);

        return "redirect:/myAdvertisements";
    }

}
