package com.example.sopra_pflanzenverkauf.entity;

import jakarta.persistence.*;

import java.util.Set;

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
    private String plantname;

    @Column(name="Lateinischer Name")
    private String latinName;

    @Column(name = "Größe in cm")
    private Integer plantSize;

    @Column(name = "Preis")
    private double price;

    @Column(name = "Beschreibung der Pflanze")
    private String plantDescription;

    //TODO Bilder einfügen können
    @Column(name = "Fotos")
    private String imageUrl;

    @Column(name = "Pflegetipps")
    private String careTips;

    @Column(name = "Verkauft")
    private boolean sold = false;

    @Column(name = "Postleitzahl")
    private Integer zipCode;

    @ManyToOne
    @JoinColumn(name = "Kategorie")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "Verkäufer")
    private User seller;

    @ManyToOne
    @JoinColumn(name = "Käufer")
    private User buyer;

    
    /**
     * Instantiates a new Plant.
     */
    public Plant() {
        // empty constructor for Hibernate
    }

    public Plant (String plantname, Integer plantSize, double price, String plantDescription, String careTips, Category category, User seller, Integer zipCode, User buyer, Boolean sold) {
        this.plantname = plantname;
        this.plantSize = plantSize;
        this.price = price;
        this.plantDescription = plantDescription;
        this.careTips = careTips;
        this.category = category;
        this.seller = seller;
        this.zipCode = zipCode;
        this.buyer = buyer;
        this.sold = sold;
    }

    public Plant (String plantname, Integer plantSize, double price, String plantDescription, String careTips, Category category, User seller, Integer zipCode, Boolean sold) {
        this.plantname = plantname;
        this.plantSize = plantSize;
        this.price = price;
        this.plantDescription = plantDescription;
        this.careTips = careTips;
        this.category = category;
        this.seller = seller;
        this.zipCode = zipCode;
        this.sold = sold;
    }



    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getImageUrl(){
        return imageUrl;
    }

    public Integer getPlantId() {
        return plantId;
    }
    public void setPlantId(Integer plantId) {
        this.plantId = plantId;
    }

    public String getPlantname() {
        return plantname;
    }
    public void setPlantname(String name) {
        this.plantname = name;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
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

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Boolean getSold(){
        return sold;
    }
    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
