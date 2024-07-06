package com.example.sopra_pflanzenverkauf.service;

import com.example.sopra_pflanzenverkauf.entity.Caretip;
import com.example.sopra_pflanzenverkauf.entity.Category;
import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.repository.CaretipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaretipService {
    @Autowired
    CaretipRepository caretipRepository;

    public CaretipService(CaretipRepository caretipRepository){
        this.caretipRepository = caretipRepository;
    }

    public List<Caretip> getAllCaretips() {
        return caretipRepository.findAll();
    }

    public List<Caretip> getCaretipsByCategory(Category category) {
        return caretipRepository.findByCategory(category);
    }

    public Caretip getCaretipById (Integer caretipId) {
        return caretipRepository.findByCaretipId(caretipId);
    }
}
