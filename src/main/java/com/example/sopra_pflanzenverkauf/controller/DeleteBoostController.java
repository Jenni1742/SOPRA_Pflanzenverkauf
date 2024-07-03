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

import java.util.List;

@Controller
public class DeleteBoostController {

    @Autowired
    private UserService userService;

    @Autowired
    private PlantService plantService;

    @Autowired
    private PlantRepository plantRepository;


    @GetMapping("/deleteBoost/{id}")
    public String openDeletePlant(@PathVariable("id") Integer plantId, Model model) {

        model.addAttribute("plantId", plantId);
        model.addAttribute("currentUser", userService.getCurrentUser());

        Plant plant = plantRepository.findById(plantId)
                .orElse(null);

        model.addAttribute("plant", plant);

        if (plant == null) {
            return "error/errorIDDoNotExist";
        } else if (userService.getCurrentUser() == plant.getSeller() && plant.getBooster() == true){
            return "deleteBoost";
        } else {
            return "error/errorDeleteBoost";
        }

    }


    @PostMapping("/deleteBoost/{id}")
    public String deletePlant(@PathVariable("id") Integer plantId, Model model) {

        User currentUser = userService.getCurrentUser();

        Plant plant = plantService.findById(plantId);

        plant.setBooster(false);
        plantService.save(plant);

        List<Plant> plantList = currentUser.getPlantsToSell();

        model.addAttribute("plantList", plantList);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("coinCount", currentUser.getPlantCoinCount());


        return "redirect:/myAdvertisements";

    }
}
