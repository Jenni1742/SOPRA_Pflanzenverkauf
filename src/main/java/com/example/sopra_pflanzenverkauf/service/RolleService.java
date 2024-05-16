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
/*
    public Rolle saveRolle(Rolle rolle) {
        return RolleRepository.save(rolle);
    }
*/
    public List<Rolle> findAllRolle() {
        return rolleRepository.findAll();
    }







    ////////////////////////Aus Demo Projekt
    /**
     * Constructor for spring dependency injection.
     */
    public RolleService(RolleRepository rolleRepository) {
        this.rolleRepository = rolleRepository;
    }

    public Rolle persistRole(Rolle role) {
        return rolleRepository.save(role);
    }

    /**
     * Sucht nach einer Rolle anhand ihres Namens.
     *
     * @param rolename der Name der Rolle.
     * @return die gefundene Rolle.
     */
    public Rolle findRoleByName(String rolename) {
        return rolleRepository.findByRolename(rolename);
    }
    ////////////////////////Aus Demo Projekt

}
