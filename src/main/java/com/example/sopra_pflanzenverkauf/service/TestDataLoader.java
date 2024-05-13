package com.example.sopra_pflanzenverkauf.service;

import com.example.sopra_pflanzenverkauf.entity.Benutzer;
import com.example.sopra_pflanzenverkauf.entity.Rolle;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import com.example.sopra_pflanzenverkauf.service.RolleService;
import com.example.sopra_pflanzenverkauf.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;


/*
@Component
public class TestDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(TestDataLoader.class);

    //@Autowired
    //private BCryptPasswordEncoder passwordEncoder;

    //@Autowired
    //private RolleService rolleService;

    @Autowired
    private UserService userService;

    /**
     * Diese Methode wird zum Aufsetzen von Testdaten für die Datenbank verwendet werden. Die Methode wird immer dann
     * ausgeführt, wenn der Spring Kontext initialisiert wurde, d.h. wenn Sie Ihren Server (neu-)starten.
     */

/*
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("Initialisiere Datenbank mit Testdaten...");

        // Initialisieren Sie Beispielobjekte und speichern Sie diese über Ihre Services
        /*Rolle userRole = new Rolle("ROLE_USER");
        Rolle adminRole = new Rolle("ROLE_ADMIN");
        rolleService.saveRolle(userRole);
        rolleService.saveRolle(adminRole);

        Set<Rolle> userRoles = new HashSet<>();
        userRoles.add(userRole);

        Set<Rolle> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);*/
/*
        Benutzer jenniferKaisner = new Benutzer();
        jenniferKaisner.setUsername("JenniferK");
        jenniferKaisner.setPassword("1234");
        jenniferKaisner.setFirstName("Jennifer");
        jenniferKaisner.setLastName("Kaisner");
        jenniferKaisner.setEmail("Jennifer.Kaisner@beispiel.de");
        jenniferKaisner.setPlz("72108");
        jenniferKaisner.setEnabled(true);
        //jenniferKaisner.setPassword(passwordEncoder.encode("1234"));
        //jenniferKaisner.setRoles(userRoles);
        //userService.saveUser(jenniferKaisner);

        /*
        Benutzer admin = new Benutzer();
        admin.setUsername("admin");
        admin.setPassword("1234");
        //admin.setPassword(passwordEncoder.encode("admin"));
        //admin.setRoles(adminRoles);
        userService.saveUser(admin);
        */
/*
    }
}
*/




////////////////////////Aus Demo Projekt
/**
 *
 * This class is responsible for setting up some test data.
 */
@Component
public class TestDataLoader implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private RolleService roleService;

    /**
     * Executed during Spring boot startup.
     * @param args arguments.
     * @throws Exception exception.
     */
    @Override
    public void run(String... args) throws Exception {

        // create roles
        Rolle userRole = new Rolle();
        userRole.setRolename("ROLE_USER");
        roleService.persistRole(userRole);

        Set<Rolle> userRoles = new HashSet<>();
        userRoles.add(userRole);

        // create users
        Benutzer jenniferKaisner = new Benutzer();
        jenniferKaisner.setEmail("Jennifer.Kaisner@mail.com");
        jenniferKaisner.setUsername("JenniferK");
        jenniferKaisner.setPassword("1234");
        jenniferKaisner.setEnabled(true);
        //jenniferKaisner.setNonLocked(true);
        jenniferKaisner.setRoles(userRoles);
        userService.persistUser(jenniferKaisner);


    }
}
////////////////////////Aus Demo Projekt