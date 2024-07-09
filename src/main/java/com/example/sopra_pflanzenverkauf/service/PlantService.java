package com.example.sopra_pflanzenverkauf.service;

import com.example.sopra_pflanzenverkauf.entity.Category;
import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.repository.PlantRepository;
import com.example.sopra_pflanzenverkauf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlantService {

    @Autowired
    private PlantRepository plantRepository;

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
    public List<Plant> getFilteredAndSortedPlants(Category category, String planter, Integer priceMin, Integer priceMax, Integer sizeMin, Integer sizeMax, String sort, boolean booster) {
        List<Plant> plants = getAllPlants();

        if (category != null) {
            plants = plants.stream()
                    .filter(plant -> category.equals(plant.getCategory()))
                    .collect(Collectors.toList());
        }

        if (planter != null) {
            plants = plants.stream()
                    .filter(plant -> plant.getPlanter())
                    .collect(Collectors.toList());
        }

        if (priceMin != null && priceMax != null) {
            plants = plants.stream()
                    .filter(plant -> plant.getPrice() >= priceMin && (priceMax < 100 ? plant.getPrice() <= priceMax : plant.getPrice() > 100))
                    .collect(Collectors.toList());
        }

        if (sizeMin != null && sizeMax != null) {
            plants = plants.stream()
                    .filter(plant -> plant.getPlantSize() >= sizeMin && (sizeMax < 100 ? plant.getPlantSize() <= sizeMax : plant.getPlantSize() > 100))
                    .collect(Collectors.toList());
        }

        if (booster) {
            List<Plant> boosterPlants = plants.stream()
                    .filter(Plant::getBooster)
                    .collect(Collectors.toList());
            plants = boosterPlants.isEmpty() ? plants : boosterPlants;
        }

        if (sort != null) {
            switch (sort) {
                case "price_asc":
                    plants = plants.stream()
                            .sorted(Comparator.comparing(Plant::getPrice))
                            .collect(Collectors.toList());
                    break;
                case "price_desc":
                    plants = plants.stream()
                            .sorted(Comparator.comparing(Plant::getPrice).reversed())
                            .collect(Collectors.toList());
                    break;
                case "size_asc":
                    plants = plants.stream()
                            .sorted(Comparator.comparing(Plant::getPlantSize))
                            .collect(Collectors.toList());
                    break;
                case "size_desc":
                    plants = plants.stream()
                            .sorted(Comparator.comparing(Plant::getPlantSize).reversed())
                            .collect(Collectors.toList());
                    break;
                default:
                    // Keine Sortierung
                    break;
            }
        }

        return plants;
    }

//    public List<Plant> getFilteredAndSortedPlants(Category category, String planter, Integer priceMin, Integer priceMax, Integer sizeMin, Integer sizeMax, boolean booster, String sort) {
//        List<Plant> plants = getFilteredPlants(category, planter, priceMin, priceMax, sizeMin, sizeMax, booster);
//        return sortPlants(plants, sort);
//    }
//
//    public List<Plant> getFilteredPlants(Category category, String planter,Integer priceMin, Integer priceMax, Integer sizeMin, Integer sizeMax, boolean booster) {
//        List<Plant> plants = getAllPlants();
//
//        if (category != null) {
//            plants = plants.stream()
//                    .filter(plant -> category.equals(plant.getCategory()))
//                    .collect(Collectors.toList());
//        }
//
//        if (planter != null ) {
//            plants = plants.stream()
//                    .filter(plant ->  plant.getPlanter())
//                    .collect(Collectors.toList());
//        }
//
//        if (priceMin != null && priceMax != null) {
//            plants = plants.stream()
//                    .filter(plant -> plant.getPrice() >= priceMin && (priceMax < 100 ? plant.getPrice() <= priceMax : plant.getPrice() > 100))
//                    .collect(Collectors.toList());
//        }
//
//        if (sizeMin != null && sizeMax != null) {
//            plants = plants.stream()
//                    .filter(plant -> plant.getPlantSize() >= sizeMin && (sizeMax < 100 ? plant.getPlantSize() <= sizeMax : plant.getPlantSize() > 100))
//                    .collect(Collectors.toList());
//        }
//        if (booster) {
//            // Filter for plants with booster
//            List<Plant> boosterPlants = plants.stream()
//                    .filter(Plant::getBooster)
//                    .collect(Collectors.toList());
//
//            // Logging before booster filtering
//            System.out.println("Number of plants with booster before filtering: " + boosterPlants.size());
//
//            // If there are booster plants, use them; otherwise, use all plants
//            plants = boosterPlants.isEmpty() ? plants : boosterPlants;
//
//            // Logging after booster filtering
//            System.out.println("Number of plants after booster filtering: " + plants.size());
//
//            // Debugging which plants are boosted
//            plants.forEach(plant -> System.out.println("Boosted plant: " + plant.getPlantId()));
//        }
//
//        return plants;
//    }
//
//    public List<Plant> sortPlants(List<Plant> plants, String sort) {
//        if (sort == null) {
//            return plants;
//        }
//        switch (sort) {
//            case "price_asc":
//                plants = plants.stream()
//                        .sorted(Comparator.comparing(Plant::getPrice))
//                        .collect(Collectors.toList());
//                break;
//            case "price_desc":
//                plants = plants.stream()
//                        .sorted(Comparator.comparing(Plant::getPrice).reversed())
//                        .collect(Collectors.toList());
//                break;
//            case "size_asc":
//                plants = plants.stream()
//                        .sorted(Comparator.comparing(Plant::getPlantSize))
//                        .collect(Collectors.toList());
//                break;
//            case "size_desc":
//                plants = plants.stream()
//                        .sorted(Comparator.comparing(Plant::getPlantSize).reversed())
//                        .collect(Collectors.toList());
//                break;
//            default:
//                // Unsortiert
//                break;
//        }
//
//        return plants;
//    }

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
        public Plant save(Plant plant) {
             return plantRepository.save(plant);
}
}
