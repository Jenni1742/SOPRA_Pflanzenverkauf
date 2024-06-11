package com.example.sopra_pflanzenverkauf.service;

import com.example.sopra_pflanzenverkauf.entity.Category;
import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.Role;
import com.example.sopra_pflanzenverkauf.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired //Annotation zur Markierung von Objekten für die Spring Dependency Injection
    private CategoryRepository categoryRepository;


    public List<Category> findAllRole() {
        return categoryRepository.findAll();
    }

    /**
     * Constructor for spring dependency injection.
     */
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Saves a category-object.
     * Persists a category in the database
     *
     * @param category the category to persist.
     * @return the persisted category-object incl. ID.
     */
    public Category persistCategory(Category category) {
        return categoryRepository.save(category);
    }

    /**
     * Search for a Category by its name.
     *
     * @param categoryname the name of the category.
     * @return the category that was found.
     */
    public Category getCategoryByCategoryname(String categoryname) {
        return categoryRepository.findByCategoryname(categoryname);
    }

    /**
     * Returns all categories persisted in the database.
     *
     * @return List of categories.
     */
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryByName(String categoryname) {
        return categoryRepository.findByCategoryname(categoryname);
    }



}
