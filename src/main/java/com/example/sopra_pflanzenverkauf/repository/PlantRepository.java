package com.example.sopra_pflanzenverkauf.repository;

import com.example.sopra_pflanzenverkauf.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface PlantRepository extends JpaRepository<Plant, Integer> {

        Plant findByPlantname(String plantname); //"findBy" ist Schlüsselwort für SELECT * FROM Pflanze WHERE name = x

        List<Plant> findByPlantnameContainingIgnoreCase(String plantname);

        List<Plant> findBySoldFalseOrderByPlantIdAsc();

        Plant findByPlantId(Integer plantID);
}
