package com.example.sopra_pflanzenverkauf.repository;

import com.example.sopra_pflanzenverkauf.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer>{

    Role findByRolename(String roleName); //"findBy" ist Schlüsselwort für SELECT * FROM Rolle WHERE roleName = x
}

