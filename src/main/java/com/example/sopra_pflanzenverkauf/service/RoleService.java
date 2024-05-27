package com.example.sopra_pflanzenverkauf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sopra_pflanzenverkauf.entity.Role;
import com.example.sopra_pflanzenverkauf.repository.RoleRepository;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

/*
    public Role saveRole(Role role) {
        return RoleRepository.save(role);
    }
*/
    public List<Role> findAllRole() {
        return roleRepository.findAll();
    }







    ////////////////////////Aus Demo Projekt
    /**
     * Constructor for spring dependency injection.
     */
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role persistRole(Role role) {
        return roleRepository.save(role);
    }

    /**
     * Sucht nach einer Rolle anhand ihres Namens.
     *
     * @param rolename der Name der Rolle.
     * @return die gefundene Rolle.
     */
    public Role findRoleByName(String rolename) {
        return roleRepository.findByRolename(rolename);
    }
    ////////////////////////Aus Demo Projekt

}
