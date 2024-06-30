
package com.example.sopra_pflanzenverkauf.controller;

        import com.example.sopra_pflanzenverkauf.entity.Plant;
        import com.example.sopra_pflanzenverkauf.entity.User;
        import com.example.sopra_pflanzenverkauf.repository.PlantRepository;
        import com.example.sopra_pflanzenverkauf.service.PlantService;
        import com.example.sopra_pflanzenverkauf.service.UserService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Controller
public class ConfirmBoostController {

    @Autowired
    private UserService userService;

    @Autowired
    private PlantService plantService;

    @Autowired
    private PlantRepository plantRepository;
    @Autowired
    private ErrorIDDoNotExistController errorIDDoNotExistController;
    @Autowired
    private ErrorDeleteAdvertisement errorDeleteAdvertisement;


    @GetMapping("/confirmBoost/{id}")
    public String openConfirmBoost(@PathVariable("id") Integer plantId, Model model) {

        System.out.println("WE ARE INSIDE");

        model.addAttribute("plantId", plantId);

        Plant plant = plantRepository.findById(plantId)
                .orElse(null);

        model.addAttribute("plant", plant);

        return "confirmBoost";
        /*
        if (plant == null) {
            return "error/errorIDDoNotExist";
        } else if (userService.getCurrentUser() == plant.getSeller()){
            return "confirmBoost";
        } else {
            return "error/confirmBoost";
        } */
    }


    @PostMapping("/confirmBoost/{id}")
    public String confirmBoost(@PathVariable("id") Integer plantId, Model model) {

        User currentUser = userService.getCurrentUser();

        if (currentUser == plantService.getPlantByPlantId(plantId).getSeller() && !plantService.getPlantByPlantId(plantId).getSold()) {

            for (User user : userService.findAllUsers()) {
                if (user.getWishlistPlants().contains(plantService.getPlantByPlantId(plantId))) {
                    user.getWishlistPlants().remove(plantService.getPlantByPlantId(plantId));
                }
            }

            plantService.deletePlantByPlantId(plantId);

            List<Plant> plantList = userService.getCurrentUser().getPlantsToSell();
            model.addAttribute("plantList", plantList);
        }

        return "redirect:/myAdvertisements";
    }
}
