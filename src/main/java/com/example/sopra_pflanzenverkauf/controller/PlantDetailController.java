package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.repository.PlantRepository;
import com.example.sopra_pflanzenverkauf.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;


@Controller
public class PlantDetailController {
    @Autowired
    private PlantService plantService;
    @Autowired
    private PlantRepository plantRepository;

    /**
    @GetMapping("/plant-detail/{id}")
    public String getPlantDetails(@PathVariable("id") Integer id, Map<String, Object> model) {
        Plant plant = plantService.findById(id);
        if (plant != null) {
            model.put("plant", plant);
            return "plant-detail";
        } else {
            return "error/404";
        }
    }
*/

    @GetMapping("/plant-detail/{id}")
    public String showPlantDetail(@PathVariable("id") Integer plantId, Model model) {
        Plant plant = plantRepository.findById(plantId)
                .orElseThrow(() -> {
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Plant not found");
                });
        model.addAttribute("plant", plant);
        return "plant-detail";  // assuming the Thymeleaf template is named 'plant-detail.html'
    }
}

