package com.example.sopra_pflanzenverkauf.entity;

import jakarta.persistence.*;

/**
 * The Entity "Pflanze".
 */
@Entity
@Table(name = "Pflanze")
public class Plant {

    @Id
    @GeneratedValue
    @Column(name = "PflanzenID")
    private Integer plantId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Größe in cm")
    private Integer plantSize;

    @Column(name = "Preis")
    private double price;

    @Column(name = "Beschreibung der Pflanze")
    private String plantDescription;

    //TODO Bilder einfügen können
    //@Column(name = "Fotos")
    //private Integer pictures;

    @Column(name = "Pflegetipps")
    private String careTips;

    @Column(name = "Verkauft")
    private boolean sold = false;

    @Column(name = "Postleitzahl")
    private Integer zipCode;
    @Column(name = "Kategorie")
    private String category;

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    @Column(name ="Verkäufer")
    private String seller;

    /**
     * Instantiates a new Plant.
     */
    public Plant() {
        // empty constructor for Hibernate
    }

    public Plant (String name, Integer plantSize, double price, String plantDescription, String careTips, String category) {
        this.name = name;
        this.plantSize = plantSize;
        this.price = price;
        this.plantDescription = plantDescription;
        this.careTips = careTips;
        this.category = category;
    }

    public Integer getPlantId() {
        return plantId;
    }
    public void setPlantId(Integer plantId) {
        this.plantId = plantId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getPlantSize() {
        return plantSize;
    }
    public void setPlantSize(Integer plantSize) {
        this.plantSize = plantSize;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String getPlantDescription() {
        return plantDescription;
    }
    public void setPlantDescription(String plantDescription) {
        this.plantDescription = plantDescription;
    }

    public String getCareTips() {
        return careTips;
    }
    public void setCareTips(String careTips) {
        this.careTips = careTips;
    }

    public boolean isSold() {
        return sold;
    }
    public void setSold(boolean sold) {
        this.sold = sold;
    }


    public void setCategory(){
        this.category = category;
    }
    public String getCategory() {
        return category;
    }



}
