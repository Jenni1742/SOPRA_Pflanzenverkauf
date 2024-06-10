package com.example.sopra_pflanzenverkauf.service;

import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.repository.UserRepository;
import com.example.sopra_pflanzenverkauf.entity.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired //Annotation zur Markierung von Objekten für die Spring Dependency Injection
    private UserRepository userRepository;


    ////////////////////////Aus Demo Projekt
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Constructor for spring dependency injection.
     */
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void updatePicturePath(User user) {
        userRepository.save(user);
    }

    public void updateUserPassword(User user) {
        userRepository.save(user);
    }

    public void updateUsername(User user) {
        userRepository.save(user);
    }

    public void updateFirstName(User user) {
        userRepository.save(user);
    }

    public void updateLastName(User user) {
        userRepository.save(user);
    }

    public void updateEmail(User user) {
        userRepository.save(user);
    }

    public void updatePLZ(User user) {
        userRepository.save(user);
    }

    public void updateWishlist(User user) {
        userRepository.save(user);
    }

    public void updateBuyingLevel(User user) {userRepository.save(user);}

    public void updateSellingLevel(User user) {userRepository.save(user);}

    public void updatePlantsToSell(User user) {userRepository.save(user);}

    public void updatePurchasedPlants(User user) {
        user.setNumberOfPurchasedPlants(user.getPurchasedPlants().size());
        userRepository.save(user);
    }

    public void updateNumberOfPurchasedPlants(User user) {
        userRepository.save(user);
    }

    public void updateSoldPlantsList(User user) {
        user.setNumberOfSoldPlants(user.getSoldPlantsList().size());
        userRepository.save(user);
    }

    public void updateNumberOfSoldPlants(User user) {
        userRepository.save(user);
    }



    /**
     * Saves a user-object and encodes its password.
     * Persists a user in the database
     *
     * @param user the user to persist.
     * @return the persisted user-object incl. ID.
     */
    public User persistUser(User user) {
        // encode password before saving
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public void deleteUserById(Integer userId) {
        userRepository.deleteById(userId);
    }
    ////////////////////////Aus Demo Projekt



    /*
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    */

    /**
     * Returns all users persisted in the database.
     *
     * @return List of users.
     */
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public List<User> findAllByOrderByNumberOfPurchasedPlantsDesc() {
        return userRepository.findAllByOrderByNumberOfPurchasedPlantsDesc();
    }

    public List<User> findAllByOrderByNumberOfSoldPlantsDesc() {
        return userRepository.findAllByOrderByNumberOfSoldPlantsDesc();
    }


    /**
     * Sucht nach einem User mit einem bestimmten Usernamen.
     *
     * @param username der username.
     * @return User-Objekt.
     */


    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Spring Security Authentication Methoden
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Gibt den aktuell eingeloggten User zurück.
     *
     * @return User.
     */


    public User getCurrentUser() {
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
        User user = userRepository.findByUsername(username);
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
    private List<GrantedAuthority> getUserAuthorities(Set<Role> roleSet) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role role : roleSet) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRolename()));
        }
        return grantedAuthorities;
    }
}
