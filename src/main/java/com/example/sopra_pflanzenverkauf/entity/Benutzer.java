package com.example.sopra_pflanzenverkauf.entity;

import com.example.sopra_pflanzenverkauf.SopraPflanzenverkaufApplication;
import com.example.sopra_pflanzenverkauf.repository.BenutzerRepository;
import jakarta.persistence.*;

import java.util.Set;

import com.example.sopra_pflanzenverkauf.service.UserService;
import org.hibernate.tool.schema.extract.internal.SequenceInformationExtractorH2DatabaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The type Benutzer.
 */
@Entity
public class Benutzer {


    @Id
    @GeneratedValue

    //TODO: Profilbild

    private Integer userId;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private String plz;

    private boolean enabled = true;


    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Rolle> roles;

    /**
     * Instantiates a new Benutzer.
     */
    public Benutzer() {
        // empty constructor for Hibernate
    }





    ///// Getter & Setter://///

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
     * Gets roles.
     *
     * @return the roles
     */
    public Set<Rolle> getRoles() {
        return roles;
    }

    /**
     * Sets roles.
     *
     * @param roles the roles
     */
    public void setRoles(Set<Rolle> roles) {
        this.roles = roles;
    }

}
