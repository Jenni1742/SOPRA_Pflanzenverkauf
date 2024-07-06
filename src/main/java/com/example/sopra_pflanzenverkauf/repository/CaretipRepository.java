package com.example.sopra_pflanzenverkauf.repository;


import com.example.sopra_pflanzenverkauf.entity.Caretip;
import com.example.sopra_pflanzenverkauf.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CaretipRepository extends JpaRepository<Caretip, Integer> {

    List<Caretip> findByCategory(Category category);

    Caretip findByCaretipId(Integer caretipId);

    Caretip findByTitle(String title);
}
