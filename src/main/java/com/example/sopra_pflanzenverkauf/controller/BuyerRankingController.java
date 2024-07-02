package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Controller
public class BuyerRankingController {

    @Autowired
    private UserService userService;

    /**
     * Request Mapping for the Buyer Ranking Page.
     *
     * @param model the model.
     * @return Buyer Ranking-page.
     */
    @RequestMapping(value = "/buyerRanking", method = RequestMethod.GET)
    public String showBuyerRankingPage(Model model) {

        User currentUser = userService.getCurrentUser();

        model.addAttribute("currentUser", currentUser);

        List<User> users = userService.findAllByOrderByNumberOfPurchasedPlantsDesc();
        model.addAttribute("users", users);

        Integer rank = users.indexOf(currentUser) + 1;
        model.addAttribute("rank", rank);

        return "buyerRanking";
    }
}
