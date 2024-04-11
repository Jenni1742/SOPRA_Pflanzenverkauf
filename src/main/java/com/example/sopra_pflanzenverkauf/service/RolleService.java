package com.example.sopra_pflanzenverkauf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sopra_pflanzenverkauf.entity.Rolle;
import com.example.sopra_pflanzenverkauf.repository.RolleRepository;

import java.util.List;

@Service
public class RolleService {
    @Autowired

    private RolleRepository rolleRepository;

    public Rolle saveRolle(Rolle rolle) {
        return RolleRepository.save(rolle);
    }

    public List<Rolle> findAllRolle() {
        return rolleRepository.findAll();
    }
}
