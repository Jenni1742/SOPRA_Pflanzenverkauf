package com.example.sopra_pflanzenverkauf.entity;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "Level")
public class Level {

    @Id
    @GeneratedValue
    @Column(name = "LevelID")
    private Integer levelID;

    @Column(name = "Levelname")
    private String levelname;

    @OneToMany (mappedBy = "buyingLevel")
    private java.util.List<User> buyerHavingThisLevel = new ArrayList<>();

    @OneToMany (mappedBy = "sellingLevel")
    private java.util.List<User> sellerHavingThisLevel = new ArrayList<>();

    /**
     * Instantiates a new Level.
     */
    public Level() {
        // empty constructor for Hibernate
    }


    public Integer getLevelID() {
        return levelID;
    }

    public void setLevelID(Integer levelID) {
        this.levelID = levelID;
    }


    public String getLevelname() {
        return levelname;
    }

    public void setLevelname(String levelname) {
        this.levelname = levelname;
    }
}
