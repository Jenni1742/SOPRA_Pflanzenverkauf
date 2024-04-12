package com.example.sopra_pflanzenverkauf.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.security.core.GrantedAuthority;

@Entity
public class Rolle implements GrantedAuthority {
    @Id
    @GeneratedValue
    private Integer id;

    private String rolename;

    public Rolle() {
        //empty constructor for Hibernate
    }

    public Rolle(String rolename) {
        this.rolename = rolename;
    }


    ////////////////////////Aus Demo Projekt
    @Override
    public String getAuthority(){
        return rolename;
    }
    ////////////////////////Aus Demo Projekt



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
