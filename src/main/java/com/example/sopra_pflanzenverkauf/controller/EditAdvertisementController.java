package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Category;
import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.repository.PlantRepository;
import com.example.sopra_pflanzenverkauf.service.CategoryService;
import com.example.sopra_pflanzenverkauf.service.PlantService;
import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class EditAdvertisementController {
    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private PlantService plantService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    /**
     * Handles GET requests targeted at the editAdvertisement page.
     *
     * @return  the editAdvertisement page
     */
    @GetMapping("/editAdvertisement/{id}")
    public String editPlant(@PathVariable("id") Integer plantId, Model model) {
        Plant plant = plantRepository.findById(plantId)
                .orElse(null);
        model.addAttribute("plant", plant);

        List<Category> categories = categoryService.findAllCategories();
        model.addAttribute("categories", categories);

        if (plant == null) {
            return "error/errorIDDoNotExist";
        } else if (userService.getCurrentUser() == plant.getSeller()){
            return "editAdvertisement";
        } else {
            return "error/errorEditAdvertisement";
        }
    }


    @RequestMapping(value="/editAdvertisement/{id}",  method = RequestMethod.POST)
    public String editPlant(@PathVariable("id") Integer plantId,
                            @RequestParam(value = "newplantname", required = false) String newplantname,
                            @RequestParam(value= "newlatinName", required = false) String newlatinName,
                            @Param(value="withPlanterTrue") Boolean withPlanterTrue,
                            @Param(value="withPlanterFalse") Boolean withPlanterFalse,
                            @RequestParam(value="newplantSize", required = false) Integer newplantSize,
                            @RequestParam(value="newprice", required = false) Double newprice,
                            @RequestParam(value="newplantDescription", required = false) String newplantDescription,
                            @RequestParam(value="newcareTips", required = false) String newcareTips,
                            @RequestParam(value="newzipCode", required = false) String newzipCode,
                            @RequestParam(value="newimageUrl", required = false) String newimageUrl,
                            Model model){

        Plant currentPlant = plantService.getPlantByPlantId(plantId);

        if(newplantname != null && !newplantname.isEmpty()){
            currentPlant.setPlantname(newplantname);
        }

        if(newlatinName != null && !newlatinName.isEmpty()){
            currentPlant.setLatinName(newlatinName);
        }

        if(withPlanterTrue == null){
            currentPlant.setPlanter(false);
        }

        if(withPlanterFalse != null){
            currentPlant.setPlanter(true);
        }

        if(newplantSize != null){
            currentPlant.setPlantSize(newplantSize);
        }

        if(newprice != null) {
            currentPlant.setPrice(newprice);
        }

        if(newplantDescription != null && !newplantDescription.isEmpty()){
            currentPlant.setPlantDescription(newplantDescription);
        }

        if(newcareTips != null && !newcareTips.isEmpty()){
            currentPlant.setCareTips(newcareTips);
        }

        //if(newzipCode != null && !newzipCode.isEmpty()){
        //    currentPlant.setZipCode(newzipCode);
        //}

        plantService.updatePlant(currentPlant);

        return "redirect:/myAdvertisements";
    }

}
