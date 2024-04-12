package com.example.sopra_pflanzenverkauf.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Benutzer {

    @Id
    @GeneratedValue
    private Integer userId;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private String plz;

    //TODO: Profilbild
/*
    private boolean enabled = true;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Rolle> roles;
*/
    public Benutzer() {
        // empty constructor for Hibernate
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }
/*
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Rolle> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rolle> rollen) {
        this.roles = rollen;
    }
*/
}
