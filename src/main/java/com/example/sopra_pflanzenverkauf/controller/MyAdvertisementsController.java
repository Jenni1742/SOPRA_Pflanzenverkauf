package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.service.PlantService;
import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class MyAdvertisementsController {

    @Autowired
    private PlantService plantService;

    @Autowired
    private UserService userService;

    @GetMapping("/myAdvertisements")
    public String showMyAdvertisementsPage(Model model) {
        User currentUser = userService.getCurrentUser();
        List<Plant> plantList = currentUser.getPlantsToSell();

        model.addAttribute("plantList", plantList);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("coinCount", currentUser.getPlantCoinCount());

        return "myAdvertisements";
    }

    @PostMapping("/boostAdvertisement/{plantId}")
    public String boostAdvertisement(@PathVariable("plantId") Integer plantId, Model model) {
        User currentUser = userService.getCurrentUser();
        Plant plant = plantService.findById(plantId);

        if (plant == null || !currentUser.equals(plant.getSeller())) {
            return "error/errorIDDoNotExist";
        }

        if (plant.getBooster()) {
            model.addAttribute("boostMessage", "This advertisement is already boosted.");
            return "myAdvertisements";
        }

        if (currentUser.getPlantCoinCount() < 1) {
            model.addAttribute("boostMessage", "You do not have enough PlantCoins to boost this advertisement.");
            return "myAdvertisements";
        }

        plant.setBooster(true);
        currentUser.setPlantCoinCount(currentUser.getPlantCoinCount() - 1);
        userService.save(currentUser);
        plantService.save(plant);

        return "redirect:/myAdvertisements";
    }

    @PostMapping("/removeBoost/{plantId}")
    public String removeBoost(@PathVariable("plantId") Integer plantId, Model model) {
        User currentUser = userService.getCurrentUser();
        Plant plant = plantService.findById(plantId);

        if (plant == null || !currentUser.equals(plant.getSeller())) {
            return "error/errorIDDoNotExist";
        }

        plant.setBooster(false);
        plantService.save(plant);

        List<Plant> plantList = currentUser.getPlantsToSell();

        model.addAttribute("plantList", plantList);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("coinCount", currentUser.getPlantCoinCount());

        return "redirect:/myAdvertisements";
    }
}