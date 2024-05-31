package com.example.sopra_pflanzenverkauf.service;

import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlantService {

    @Autowired
    private PlantRepository plantRepository;

    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public List<Plant> searchPlantsByName(String name) {
        return plantRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Plant> findAllPlants() {
        return plantRepository.findAll();
    }

    public Plant getPlantByName(String name) {
        return plantRepository.findByName(name);
    }

    public Plant persistPlant(Plant plant) {
        return plantRepository.save(plant);
    }

    public List<Plant> findFirstThreeUnsoldPlants() {
        return plantRepository.findBySoldFalseOrderByPlantIdAsc().stream()
                .limit(3)
                .collect(Collectors.toList());
    }

    public List<Plant> findFilteredAndSortedPlants(String category, String price) {
        List<Plant> plants = plantRepository.findAll();

        if (category != null && !category.isEmpty()) {
            plants = plants.stream()
                    .filter(plant -> plant.getCategory().equalsIgnoreCase(category))
                    .collect(Collectors.toList());
        }

        if (price != null && !price.isEmpty()) {
            // Assuming price is in format "min-max"
            String[] priceRange = price.split("-");
            if (priceRange.length == 2) {
                try {
                    double minPrice = Double.parseDouble(priceRange[0]);
                    double maxPrice = Double.parseDouble(priceRange[1]);
                    plants = plants.stream()
                            .filter(plant -> plant.getPrice() >= minPrice && plant.getPrice() <= maxPrice)
                            .collect(Collectors.toList());
                } catch (NumberFormatException e) {
                    // Handle exception
                }
            }
        }

        return plants;
    }
}
