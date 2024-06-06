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

import java.util.List;
import java.util.Map;

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

        List<Plant> plantList = userService.getCurrentUser().getPlantsToSell();
        model.addAttribute("plantList", plantList);

        return "myAdvertisements";
    }

    @RequestMapping(value = "/myAdvertisements", method = RequestMethod.POST)

    public String removePlant(@RequestParam("plantId") Integer plantId,
                              Model model) {
        User currentUser = userService.getCurrentUser();

        List<Plant> plantsToSell = currentUser.getPlantsToSell();
        plantsToSell.remove(plantService.getPlantByPlantId(plantId));

        //plantService.deletePlant(plantService.getPlantByPlantId(plantId));

        //model.addAttribute("message", "Pflanze erfolgreich gelöscht.");

        return "myAdvertisements";
    }

    /**
    @RequestMapping(value = "/myAdvertisements", method = RequestMethod.POST)
    public String showPlant(@RequestParam("plantIdToShow") Integer plantId,
                              Model model) {
        User currentUser = userService.getCurrentUser();

        currentUser.setPlantToShow(plantId);

        return "showAdvertisement";
    }
    */

}
