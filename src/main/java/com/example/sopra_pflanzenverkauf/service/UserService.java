package com.example.sopra_pflanzenverkauf.service;

import com.example.sopra_pflanzenverkauf.entity.Benutzer;
import com.example.sopra_pflanzenverkauf.repository.BenutzerRepository;
import com.example.sopra_pflanzenverkauf.entity.Rolle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired

    private BenutzerRepository userRepository;




    ////////////////////////Aus Demo Projekt
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Constructor for spring dependency injection.
     */
    public UserService(BenutzerRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * Saves a user-object and encodes its password.
     *
     * @param benutzer the user to persist.
     * @return the persisted user-object.
     */
    public Benutzer persistUser(Benutzer benutzer) {
        // encode password before saving
        benutzer.setPassword(bCryptPasswordEncoder.encode(benutzer.getPassword()));
        return userRepository.save(benutzer);
    }
    ////////////////////////Aus Demo Projekt



    /*
    public Benutzer saveUser(Benutzer user) {
        return userRepository.save(user);
    }
    */

    public List<Benutzer> findAllUsers() {
        return userRepository.findAll();
    }


    /**
     * Sucht nach einem User mit einem bestimmten Usernamen.
     *
     * @param username der username.
     * @return User-Objekt.
     */


    public Benutzer getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Spring Security Authentication Methoden
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Gibt den aktuell eingeloggten User zurück.
     *
     * @return User.
     */


    public Benutzer getCurrentUser() {
        return getUserByUsername(((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUsername());
    }

    /**
     * Gibt das UserDetails Objekt des aktuell eingeloggten Users zurück. Wird u.U. benötigt um
     * Rollen-Authentifizierungschecks durchzuführen.
     *
     * @return UserDetails Objekt der Spring Security.
     */

    public org.springframework.security.core.userdetails.User getCurrentUserDetails() {
        return (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
    }

    /**
     * Überschreibt die Methode, welche für den Login der Spring Security benötigt wird..
     *
     * @param username the username des Benutzers.
     * @return UserDetails Objekt des Spring Security Frameworks.
     * @throws UsernameNotFoundException exception.
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Benutzer user = userRepository.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Could not find the user for username " + username);
        }
        List<GrantedAuthority> grantedAuthorities = getUserAuthorities(user.getRoles());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, user.isEnabled(), grantedAuthorities);
    }

    /**
     * Gibt die Berechtigungen (Rollen) eines Benutzers zurück.
     *
     * @param roleSet die Menge der Rollen des Benutzers.
     * @return eine Liste von GrantedAuthority-Objekten.
     */
    private List<GrantedAuthority> getUserAuthorities(Set<Rolle> roleSet) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Rolle role : roleSet) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRolename()));
        }
        return grantedAuthorities;
    }

}
