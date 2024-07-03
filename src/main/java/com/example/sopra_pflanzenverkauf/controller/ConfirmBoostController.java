package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.repository.PlantRepository;
import com.example.sopra_pflanzenverkauf.service.PlantService;
import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Controller
public class ConfirmBoostController {

        @Autowired
        private UserService userService;

        @Autowired
        private PlantService plantService;

        @Autowired
        private PlantRepository plantRepository;

    @GetMapping("/confirmBoost/{id}")
    public String openConfirmBoost(@PathVariable("id") Integer plantId, Model model) {

        model.addAttribute("plantId", plantId);
        model.addAttribute("currentUser", userService.getCurrentUser());

        Plant plant = plantRepository.findById(plantId)
                .orElse(null);

        model.addAttribute("plant", plant);

        if (plant == null) {
            return "error/errorIDDoNotExist";
        } else if (userService.getCurrentUser() == plant.getSeller() && plant.getBooster() == false){
            return "confirmBoost";
        } else {
            return "error/errorConfirmBoost";
        }

    }

    @PostMapping("/confirmBoost/{id}")
    public String confirmBoost(@PathVariable("id") Integer plantId, Model model) {
        User currentUser = userService.getCurrentUser();
        Plant plant = plantService.getPlantByPlantId(plantId);


        if (currentUser.getPlantCoinCount() < 10) {
            model.addAttribute("NichtGenugCoins", "Du hast nicht genug PlantCoins, um eine Verkaufsanzeige zu boosten.");
            model.addAttribute("plant", plant);
            model.addAttribute("currentUser", currentUser);
            model.addAttribute("plantId", plantId);
            return "/confirmBoost";
        }

        plant.setBooster(true);
        currentUser.setPlantCoinCount(currentUser.getPlantCoinCount() - 10);
        userService.save(currentUser);
        plantService.save(plant);

        return "redirect:/myAdvertisements";
    }

}

