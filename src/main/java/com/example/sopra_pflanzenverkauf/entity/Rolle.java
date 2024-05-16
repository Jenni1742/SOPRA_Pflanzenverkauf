package com.example.sopra_pflanzenverkauf.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.security.core.GrantedAuthority;

/**
 * The type Rolle.
 */
@Entity
public class Rolle implements GrantedAuthority {
    @Id
    @GeneratedValue
    private Integer id;

    private String rolename;

    /**
     * Instantiates a new Rolle.
     */
    public Rolle() {
        //empty constructor for Hibernate
    }

    /**
     * Instantiates a new Rolle.
     *
     * @param rolename the rolename
     */
    public Rolle(String rolename) {
        this.rolename = rolename;
    }


    ////////////////////////Aus Demo Projekt
    @Override
    public String getAuthority(){
        return rolename;
    }
    ////////////////////////Aus Demo Projekt


    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets rolename.
     *
     * @return the rolename
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * Sets rolename.
     *
     * @param rolename the rolename
     */
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
