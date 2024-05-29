package com.example.sopra_pflanzenverkauf.entity;

import jakarta.persistence.*;

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

    @Column(name = "Käuferlevel")
    private String buyingLevel;

    @Column(name = "Verkäuferlevel")
    private String sellingLevel;

    @Column(name = "Profilbild")
    private String picturePath;


    //TODO Wird wahrscheinlich nicht benötigt

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "benutzer_rolle", joinColumns = @JoinColumn(name = "benutzer_id"), inverseJoinColumns = @JoinColumn(name = "rolle_id"))
    private Set<Role> roles;



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

    /**
     * Gets buying Level.
     *
     * @return the buying Level
     */
    public String getBuyingLevel() {
        return buyingLevel;
    }

    /**
     * Sets buying Level.
     *
     * @param buyingLevel the buying Level
     */
    public void setBuyingLevel(String buyingLevel) {
        this.buyingLevel = buyingLevel;
    }

    /**
     * Gets sellingLevel.
     *
     * @return the selling Level
     */
    public String getSellingLevel() {
        return sellingLevel;
    }

    /**
     * Sets sellingLevel.
     *
     * @param sellingLevel the selling Level
     */
    public void setSellingLevel(String sellingLevel) {
        this.sellingLevel = sellingLevel;
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
}
