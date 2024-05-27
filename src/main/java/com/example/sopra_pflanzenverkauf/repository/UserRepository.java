package com.example.sopra_pflanzenverkauf.repository;

import com.example.sopra_pflanzenverkauf.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username); //"findBy" ist Schlüsselwort für SELECT * FROM Benutzer WHERE username = x

}
