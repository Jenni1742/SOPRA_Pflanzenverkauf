package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.repository.PlantRepository;
import com.example.sopra_pflanzenverkauf.service.PlantService;
import  com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private PlantService plantService;

    @GetMapping("/")
    public String showHome(Model model, @RequestParam(value = "query", required = false) String query) {
        model.addAttribute("currentUser", userService.getCurrentUser());

        if (query != null && !query.isEmpty()) {
            List<Plant> plants = plantService.searchPlantsByName(query);
            model.addAttribute("plants", plants);
        }

        return "home";
    }

    @GetMapping("/search")
    public String searchPlants(@RequestParam("query") String query, Model model) {
        List<Plant> plants = plantService.searchPlantsByName(query);
        model.addAttribute("plants", plants);
        return "searchResults";
    }

    /**
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showHome(Model model, @RequestParam(value = "query", required = false) String query) {
        model.addAttribute("currentUser", userService.getCurrentUser());

        if (query != null && !query.isEmpty()) {
            List<Plant> plants = plantService.searchPlantsByName(query);
            model.addAttribute("plants", plants);
        }

        return "home";
    }

    @GetMapping("/search")
    public String searchPlants(@RequestParam("query") String query, Model model) {
        List<Plant> plants = plantService.searchPlantsByName(query);
        model.addAttribute("plants", plants);
            return "searchResults";
        }




    /*
    @GetMapping("/")
    public String showHome(Model model) {
        model.addAttribute("message", "Und hier sehen Sie ein ModelAttribut");
        return "home";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showHome(Map<String, Object> model) {
        // The following code may be used to redirect an admin to a specific admin-homepage instead of the default overview.
        // Of course this will work for other roles too.
        //if (userService.getCurrentUserDetails().getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            // redirect to another GET-request. You need another controller which would handle the request "/admin/"
            // As this controller is not defined yet, it is commented out.
            // return "redirect:/admin/";
        //}

        model.put("currentUser", userService.getCurrentUser());

        return "home";
    }
    ////////////////////////Aus Demo Projekt

*/

    }

