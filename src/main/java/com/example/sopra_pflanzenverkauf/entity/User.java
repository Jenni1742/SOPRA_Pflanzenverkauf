package com.example.sopra_pflanzenverkauf.entity;

import jakarta.persistence.*;
import org.hibernate.mapping.List;


import java.util.ArrayList;
import java.util.Set;

/**
 * The Entity "Benutzer".
 */
@Entity
@Table(name = "Benutzer")
public class User {

    @Id
    @GeneratedValue

    //TODO: Profilbild

    @Column(name = "BenutzerID")
    private Integer userId;

    @Column(name = "Benutzername")
    private String username;

    @Column(name = "Passwort")
    private String password;

    @Column(name = "Vorname")
    private String firstName;

    @Column(name = "Nachname")
    private String lastName;

    @Column(name = "E-mail")
    private String email;

    @Column(name = "Postleitzahl")
    private String plz;

    @Column(name = "aktiviert")
    private boolean enabled = true;

    @ManyToOne
    @JoinColumn(name = "Käuferlevel")
    private Level buyingLevel;

    @ManyToOne
    @JoinColumn(name = "Verkäuferlevel")
    private Level sellingLevel;

    @Column(name = "Profilbild")
    private String picturePath;

    @Column(name = "Gekaufte Pflanzen")
    private List purchasedPlants;

    @Column(name = "Verkaufte Pflanzen")
    private List soldPlants;

    //TODO Wird wahrscheinlich nicht benötigt

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "benutzer_rolle", joinColumns = @JoinColumn(name = "BenutzerID"), inverseJoinColumns = @JoinColumn(name = "RollenID"))
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Merkliste", joinColumns = @JoinColumn(name = "BenutzerID"), inverseJoinColumns = @JoinColumn(name = "PflanzenID"))
    private Set<Plant> wishlistPlants;

    @OneToMany (mappedBy = "seller")
    private java.util.List<Plant> plantsToSell = new ArrayList<>();

    @OneToMany (mappedBy = "buyer")
    private java.util.List<Plant> boughtPlants = new ArrayList<>();

    //wird bei showAdvertisement verwendet
    private Integer plantToShow;

    /**
     * Instantiates a new User.
     */
    public User() {
        // empty constructor for Hibernate
    }





    ///////////////////////////////////////////////
    // Getter & Setter
    ///////////////////////////////////////////////

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets plz.
     *
     * @return the plz
     */
    public String getPlz() {
        return plz;
    }

    /**
     * Sets plz.
     *
     * @param plz the plz
     */
    public void setPlz(String plz) {
        this.plz = plz;
    }

    /**
     * Is enabled boolean.
     *
     * @return the boolean
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets enabled.
     *
     * @param enabled the enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }




    //TODO Wird wahrscheinlich nicht benötigt (Jetzt gerade aber schon wegen Userservice)

    /**
     * Gets roles.
     *
     * @return the roles
     * */
    public Set<Role> getRoles() {
        return roles;
    }

    //TODO Wird wahrscheinlich nicht benötigt (Jetzt gerade aber schon wegen Userservice)

    /**
     * Sets roles.
     *
     * @param roles the roles
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    /**
     * Gets picture path.
     *
     * @return the picture path
     * */
    public String getPicturePath() {
        return picturePath;
    }

    /**
     * Sets picture path.
     *
     * @param picturePath the picture path
     */
    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    /**
     * Gets list of purchased plants.
     *
     * @return the list of purchased plants
     * */
    public List getPurchasedPlants() {
        return purchasedPlants;
    }

    /**
     * Sets list of purchased plants.
     *
     * @param purchasedPlants the list of purchased plants
     */
    public void setPurchasedPlants(List purchasedPlants) {
        this.purchasedPlants = purchasedPlants;
    }

    /**
     * Gets list of sold plants.
     *
     * @return the list of dold plants
     * */
    public List getSoldPlants() {
        return soldPlants;
    }

    /**
     * Sets list of sold plants.
     *
     * @param soldPlants the list of sold plants
     */
    public void setSoldPlants(List soldPlants) {
        this.soldPlants = soldPlants;
    }

    public java.util.List<Plant> getPlantsToSell() {
        return plantsToSell;
    }

    public void setPlantsToSell(java.util.List<Plant> plantsToSell) {
        this.plantsToSell = plantsToSell;
    }

    public java.util.List<Plant> getBoughtPlants() {
        return boughtPlants;
    }

    public void setBoughtPlants(java.util.List<Plant> boughtPlants) {
        this.boughtPlants = boughtPlants;
    }

    /**
     * Gets buying Level.
     *
     * @return the buying Level
     */
    public Level getBuyingLevel() {
        return buyingLevel;
    }

    /**
     * Sets buying Level.
     *
     * @param buyingLevel the buying Level
     */
    public void setBuyingLevel(Level buyingLevel) {
        this.buyingLevel = buyingLevel;
    }

    /**
     * Gets sellingLevel.
     *
     * @return the selling Level
     */
    public Level getSellingLevel() {
        return sellingLevel;
    }

    /**
     * Sets sellingLevel.
     *
     * @param sellingLevel the selling Level
     */
    public void setSellingLevel(Level sellingLevel) {
        this.sellingLevel = sellingLevel;
    }

    public Set<Plant> getWishlistPlants() {
        return wishlistPlants;
    }

    public void setWishlistPlants(Set<Plant> wishlistPlants) {
        this.wishlistPlants = wishlistPlants;
    }

    public Integer getPlantToShow() {
        return plantToShow;
    }

    public void setPlantToShow(Integer plantToShow) {
        this.plantToShow = plantToShow;
    }



}
