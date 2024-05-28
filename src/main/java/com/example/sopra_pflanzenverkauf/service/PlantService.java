package com.example.sopra_pflanzenverkauf.service;

import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantService {

    private final PlantRepository plantRepository;

    @Autowired
    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }

    public List<Plant> searchPlants(String query) {
        // Implementiere die Logik zur Suche nach Pflanzen basierend auf dem Query
        return plantRepository.findByNameContaining(query);
    }

    public List<Plant> filterAndSortPlants(String query, String category, String price) {
        // Implementiere die Filter- und Sortierlogik hier
        // Dies ist nur ein einfaches Beispiel, du musst die Logik an deine Anforderungen anpassen
        if (category != null && !category.isEmpty()) {
            return plantRepository.findByCategory(category);
        } else if (price != null && !price.isEmpty()) {
            return plantRepository.findByPrice(Double.parseDouble(price));
        } else {
            return getAllPlants();
        }
    }
}

