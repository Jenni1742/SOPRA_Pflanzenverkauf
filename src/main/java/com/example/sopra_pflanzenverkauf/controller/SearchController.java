package com.example.sopra_pflanzenverkauf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class SearchController {

    @GetMapping("/searchresults")
    public String search(@RequestParam String query, Map<String, Object> model) {
        List<Object> results = sucheErgebnisse(query);
        model.put("results", results);
        System.out.println("Search query received: " + query);  // Logging für Debugging
        return "searchresults";
    }


    private List<Object> sucheErgebnisse(String query) {
        // Implementiere deine Suchlogik und gib Ergebnisse zurück
        return new ArrayList<>();
    }
}
