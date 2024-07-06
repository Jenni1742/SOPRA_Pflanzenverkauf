package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Category;
import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.repository.PlantRepository;
import com.example.sopra_pflanzenverkauf.service.CategoryService;
import com.example.sopra_pflanzenverkauf.service.PlantService;
import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class CreateAdvertisementController {

    @Autowired
    private PlantService plantService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    /**
     * Handles GET requests targeted at the createAdvertisement page.
     *
     * @return  the createAdvertisement page
     */
    @RequestMapping(value = "/createAdvertisement", method = RequestMethod.GET)
    public String showCreateAdvertisementsPage(Model model) {
        model.addAttribute("plant", new Plant());

        List<Category> categories = categoryService.findAllCategories();
        model.addAttribute("categories", categories);

        return "createAdvertisement";
    }

    @RequestMapping (value="/createAdvertisement", method = RequestMethod.POST)
    public String createPlant(@ModelAttribute("plant") Plant newPlant,
                              @Param("latinName") String latinName,
                              @RequestParam("price") Double price,
                              @RequestParam("categoryname") String categoryname,
                              @RequestParam("imageMp") MultipartFile multipartFile,
                              @RequestParam("imageMp2") MultipartFile multipartFileTwo,
                              @RequestParam("imageMp3") MultipartFile multipartFileThree,
                              @Param("withPlanter") Boolean withPlanter){

        User currentUser = userService.getCurrentUser();
        newPlant.setSeller(currentUser);
        currentUser.getPlantsToSell().add(newPlant);
        userService.updatePlantsToSell(currentUser);

        newPlant.setCategory(categoryService.getCategoryByName(categoryname));

        if(latinName.isEmpty()){
            newPlant.setLatinName(null);
        }

        newPlant.setPrice(price);

        //withPlanter ist standardmäßig false und wird hier dann auf true gesetzt
        if(withPlanter != null) {
            newPlant.setPlanter(withPlanter);
        }

        if (!multipartFile.isEmpty()) {
            try {
                newPlant.setImage(multipartFile.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (!multipartFileTwo.isEmpty()) {
                try {
                    newPlant.setImageTwo(multipartFileTwo.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (!multipartFileThree.isEmpty()) {
                try {
                    newPlant.setImageThree(multipartFileThree.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //Verkauft ist standardmäßig false
            plantService.persistPlant(newPlant);

        }
        return "redirect:/myAdvertisements";
    }
}


