package com.example.sopra_pflanzenverkauf.service;

import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantService {

    @Autowired //Annotation zur Markierung von Objekten für die Spring Dependency Injection
    private PlantRepository plantRepository;

    public PlantService(PlantRepository plantRepository){
        this.plantRepository = plantRepository;
    }

    public List<Plant> searchPlantsByName(String name) {
        return plantRepository.findByNameContainingIgnoreCase(name);
    }
    /**
     * Returns all plants persisted in the database.
     *
     * @return List of plants.
     */
    public List<Plant> findAllPlants() {
        return plantRepository.findAll();
    }

    /**
     * Search for a plant by its name.
     *
     * @param name
     * @return plant object
     */
    public Plant getUserByName(String name) {
        return plantRepository.findByName(name);
    }

    public Plant persistPlant (Plant plant) {
        return plantRepository.save(plant);
    }

    public List<Plant> findFirstThreeUnsoldPlants(){

        return plantRepository.findBySoldFalseOrderByPlantIdAsc().stream()
                .limit(3)
                .toList();
    }

}
