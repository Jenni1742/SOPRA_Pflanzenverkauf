package com.example.sopra_pflanzenverkauf.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

/**
 * The type Rolle.
 */
@Entity
@Table(name = "Rolle")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue
    @Column(name = "RollenID")
    private Integer roleId;

    @Column(name = "Rollenname")
    private String rolename;

    /**
     * Instantiates a new Role.
     */
    public Role() {
        //empty constructor for Hibernate
    }

    /**
     * Instantiates a new Role.
     *
     * @param rolename the rolename
     */
    public Role(String rolename) {
        this.rolename = rolename;
    }


    ////////////////////////Aus Demo Projekt
    @Override
    public String getAuthority(){
        return rolename;
    }
    ////////////////////////Aus Demo Projekt




    ///////////////////////////////////////////////
    // Getter & Setter
    ///////////////////////////////////////////////

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setRoleId(Integer id) {
        this.roleId = id;
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
