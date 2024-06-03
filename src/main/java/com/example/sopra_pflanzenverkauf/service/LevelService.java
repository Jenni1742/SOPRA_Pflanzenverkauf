package com.example.sopra_pflanzenverkauf.service;

import com.example.sopra_pflanzenverkauf.entity.Category;
import com.example.sopra_pflanzenverkauf.entity.Level;
import com.example.sopra_pflanzenverkauf.repository.CategoryRepository;
import com.example.sopra_pflanzenverkauf.repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelService {

    @Autowired //Annotation zur Markierung von Objekten für die Spring Dependency Injection
    private LevelRepository levelRepository;

    /**
     * Constructor for spring dependency injection.
     */
    public LevelService(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    /**
     * Saves a level-object.
     * Persists a level in the database
     *
     * @param level the level to persist.
     * @return the persisted level-object incl. ID.
     */
    public Level persistLevel(Level level) {
        return levelRepository.save(level);
    }

    /**
     * Search for a Level by its name.
     *
     * @param levelname the name of the level.
     * @return the level that was found.
     */
    public Level getLevelByLevelname(String levelname) {
        return levelRepository.findByLevelname(levelname);
    }

    /**
     * Returns all levels persisted in the database.
     *
     * @return List of levels.
     */
    public List<Level> findAllLevels() {
        return levelRepository.findAll();
    }
}
