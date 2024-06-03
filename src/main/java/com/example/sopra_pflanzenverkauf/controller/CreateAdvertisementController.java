package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.repository.PlantRepository;
import com.example.sopra_pflanzenverkauf.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class CreateAdvertisementController {

    @Autowired
    private PlantService plantService;

    /**
     * Handles GET requests targeted at the createAdvertisement page.
     *
     * @return  the createAdvertisement page
     */
    @RequestMapping(value = "/createAdvertisement", method = RequestMethod.GET)
    public String showCreateAdvertisementsPage(Model model) {
        model.addAttribute("plant", new Plant());
        return "createAdvertisement";
    }


    /**
    @RequestMapping (value="/createAdvertisement", method = RequestMethod.POST)
    public String createPlant(@RequestParam("plantname") String plantname,
                              @RequestParam("plantSize") int plantSize,
                              @RequestParam("price") double price,
                              @RequestParam("plantDescription") String plantDescription,
                              @RequestParam("careTips") String caretips,
                              @RequestParam("zipCode") int zipCode){

        Plant plant = new Plant();
        plant.setPlantname(plantname);
        plant.setPlantSize(plantSize);
        plant.setPrice(price);
        plant.setPlantDescription(plantDescription);
        plant.setCareTips(caretips);
        plant.setZipCode(zipCode);

        plantService.persistPlant(plant);

        return "myAdvertisements";
    }
    */

    /**
    @RequestMapping (value="/createAdvertisement", method = RequestMethod.POST)
    public String createPlant(@RequestBody Plant plant){

        plantService.persistPlant(plant);

        return "myAdvertisements";
    }
     */

    @RequestMapping (value="/createAdvertisement", method = RequestMethod.POST)
    public String createPlant(@ModelAttribute("plant") Plant newPlant){

        plantService.persistPlant(newPlant);

        return "redirect:/myAdvertisements";
    }

}
