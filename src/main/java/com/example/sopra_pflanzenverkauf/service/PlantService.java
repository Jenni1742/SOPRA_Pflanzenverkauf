package com.example.sopra_pflanzenverkauf.service;

import com.example.sopra_pflanzenverkauf.entity.Category;
import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.repository.PlantRepository;
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

    public void updateSeller (Plant plant) {
        plantRepository.save(plant);
    }

    public void updateBuyer (Plant plant) {
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



    public void deletePlantByPlantId(Integer plantID) {
        plantRepository.deleteById(plantID);
    }

  /**  public Plant findById(Integer id) {
        return plantRepository.findById(id).orElse(null);
    }
   */
   public Plant findById(Integer id) {
   Optional<Plant> plant = plantRepository.findById(id);
   return plant.orElse(null);
   }

    public List<Plant> getAllPlants(String sort) {
        List<Plant> plants = plantRepository.findAll();

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
                    // If no sort parameter or unknown sort parameter, return the default order
                    break;
            }
        }

        return plants;
    }
}
