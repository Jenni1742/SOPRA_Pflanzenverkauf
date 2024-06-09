package com.example.sopra_pflanzenverkauf.repository;

import com.example.sopra_pflanzenverkauf.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username); //"findBy" ist Schlüsselwort für SELECT * FROM Benutzer WHERE username = x

    User findByEmail(String email);

    List<User> findAllByOrderByNumberOfPurchasedPlantsDesc();

    List<User> findAllByOrderByNumberOfSoldPlantsDesc();
}
