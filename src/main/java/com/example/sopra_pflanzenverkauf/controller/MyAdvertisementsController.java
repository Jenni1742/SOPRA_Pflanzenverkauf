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

    /**
     * Handles GET requests targeted at the advertisements page.
     *
     * @return  the advertisements page
     */

    @RequestMapping(value = "/myAdvertisements", method = RequestMethod.GET)
    public String showMyAdvertisementsPage(Model model) {
        User currentUser = userService.getCurrentUser();
        List<Plant> plantList = currentUser.getPlantsToSell();

        model.addAttribute("plantList", plantList);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("coinCount", currentUser.getPlantCoinCount());

        return "myAdvertisements";
    }

    @RequestMapping(value = "/myAdvertisements/{plantId}", method = RequestMethod.POST)
    public String removePlant(@PathVariable("plantId") Integer plantId,
                              Model model) {
        User currentUser = userService.getCurrentUser();

        for (User user:userService.findAllUsers()) {
            if(user.getWishlistPlants().contains(plantService.getPlantByPlantId(plantId))){
                user.getWishlistPlants().remove(plantService.getPlantByPlantId(plantId));
            }
        }

        currentUser.getPlantsToSell().remove(plantService.getPlantByPlantId(plantId));
        userService.updatePlantsToSell(currentUser);

        plantService.deletePlantByPlantId(plantId);
        //model.addAttribute("message", "Pflanze erfolgreich gelöscht.");

        /*
        List<Plant> plantList = currentUser.getPlantsToSell();
        model.addAttribute("plantList", plantList);
         */

        model.addAttribute("currentUser", currentUser);

        return "myAdvertisements";
    }

    @PostMapping("/boostAdvertisement/{plantId}")
    public String boostAdvertisement(@PathVariable("plantId") Integer plantId, Model model) {
        System.out.println("Bin HIER" + plantId);
        User currentUser = userService.getCurrentUser();
        Plant plant = plantService.findById(Math.toIntExact(plantId));

        //Übernommen
        List<Plant> plantList = currentUser.getPlantsToSell();

        model.addAttribute("plantList", plantList);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("coinCount", currentUser.getPlantCoinCount());

        if (currentUser.getPlantCoinCount() >= 1) {
            currentUser.setPlantCoinCount(currentUser.getPlantCoinCount() - 1);
            userService.save(currentUser);
            plant.setBooster(true);
            plantService.save(plant);
            return "redirect:/myAdvertisements";
        } else {
            return "redirect:/myAdvertisements";
        }

    }

}

