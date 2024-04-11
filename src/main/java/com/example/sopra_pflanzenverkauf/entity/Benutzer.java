package com.example.sopra_pflanzenverkauf.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Das ist mein Test Alessia
 */
@Entity
public class Benutzer {
//Test
    @Id
    @GeneratedValue
    private Integer userId;

    private String username;

    private String password;

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

}
