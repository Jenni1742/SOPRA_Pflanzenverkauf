package com.example.sopra_pflanzenverkauf.repository;

import com.example.sopra_pflanzenverkauf.entity.Benutzer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BenutzerRepository extends JpaRepository<Benutzer, Integer> {

    Benutzer findByUsername(String username); //"findBy" ist Schlüsselwort für SELECT * FROM Benutzer WHERE username = x

}
