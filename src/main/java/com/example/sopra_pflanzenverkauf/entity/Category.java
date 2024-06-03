package com.example.sopra_pflanzenverkauf.entity;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "Kategorie")
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "KategorieID")
    private Integer categoryID;

    @Column(name = "Kategoriename")
    private String categoryname;

    @OneToMany (mappedBy = "category")
    private java.util.List<Plant> categorizedPlants = new ArrayList<>();



    /**
     * Instantiates a new Category.
     */
    public Category() {
        // empty constructor for Hibernate
    }


    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }
}
