package com.example.sopra_pflanzenverkauf.service;

import com.example.sopra_pflanzenverkauf.entity.Category;
import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.repository.PlantRepository;
import com.example.sopra_pflanzenverkauf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlantService {

    @Autowired
    private PlantRepository plantRepository;
    @Autowired
    private UserRepository userRepository;

    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }

    public List<Plant> searchPlantsByName(String plantname) {
        return plantRepository.findByPlantnameContainingIgnoreCase(plantname);
    }

    public List<Plant> searchPlants(String query) {
        return plantRepository.findByPlantnameContainingIgnoreCase(query);
    }

    public List<Plant> findAllPlants() {
        return plantRepository.findAll();
    }

    public Plant getPlantByName(String plantname) {
        return plantRepository.findByPlantname(plantname);
    }

    public void updateSeller(Plant plant) {
        plantRepository.save(plant);
    }

    public void updateBuyer(Plant plant) {
        plantRepository.save(plant);
    }


    public Plant getPlantByPlantId(Integer plantID) {
        return plantRepository.findByPlantId(plantID);
    }

    public Plant getPlantBySeller(User seller) {
        return plantRepository.findBySeller(seller);
    }

    public Plant persistPlant(Plant plant) {
        return plantRepository.save(plant);
    }

    public Plant updatePlant(Plant plant) {
        return plantRepository.save(plant);
    }


    public List<Plant> findFirstThreeUnsoldPlants() {
        return plantRepository.findBySoldFalseOrderByPlantIdAsc().stream()
                .limit(3)
                .collect(Collectors.toList());
    }


    public List<Plant> findFilteredAndSortedPlants(Category category, String price, Boolean sold) {
        List<Plant> plants = plantRepository.findAll();

        if (category != null) {
            plants = plants.stream()
                    .filter(plant -> plant.getCategory().equals(category))
                    .collect(Collectors.toList());
        }
        if (sold != null) {
            plants = plants.stream()
                    .filter(plant -> plant.getSold().equals(sold))
                    .collect(Collectors.toList());
        }
        /**
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
         */
        return plants;
    }

    public void deletePlantByPlantId(Integer plantID) {
        plantRepository.deleteById(plantID);
    }

    /**
     * public Plant findById(Integer id) {
     * return plantRepository.findById(id).orElse(null);
     * }
     */
    public Plant findById(Integer id) {
        Optional<Plant> plant = plantRepository.findById(id);
        return plant.orElse(null);
    }

/**
    public void boostAdvertisement(Long plantId, Long userId) {
        Plant ad = plantRepository.findById(Math.toIntExact(plantId))
                .orElseThrow(() -> new RuntimeException("Advertisement not found"));

        User user = userRepository.findById(Math.toIntExact(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getNumberPlantCoins() >= 10) {
            user.setNumberPlantCoins(user.getNumberPlantCoins() - 10);
            ad.setBoosted(true);
            userRepository.save(user);
            plantRepository.save(ad);
        } else {
            throw new RuntimeException("Not enough Plant Coins");
        }
    }
 */
}
