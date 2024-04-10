package com.example.sopra_pflanzenverkauf.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.sopra_pflanzenverkauf.entity.Benutzer;
import com.example.sopra_pflanzenverkauf.repository.BenutzerRepository;

import java.util.List;
@Service
public class UserService {
    @Autowired
    private BenutzerRepository userRepository;

    public Benutzer saveUser(Benutzer user) {
        return userRepository.save(user);
    }

    public List<Benutzer> findAllUsers() {
        return userRepository.findAll();
    }

}
