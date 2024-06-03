package com.example.sopra_pflanzenverkauf.service;

import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.entity.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

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
    private RoleService roleService;

    @Autowired
    private PlantService plantService;

    /**
     * Executed during Spring boot startup.
     * @param args arguments.
     * @throws Exception exception.
     */
    @Override
    public void run(String... args) throws Exception {


        // create roles
        Role userRole = new Role();
        userRole.setRolename("ROLE_USER");
        roleService.persistRole(userRole);

        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);

        // create users
        User jenniferKaisner = new User();
        jenniferKaisner.setEmail("Jennifer.Kaisner@mail.com");
        jenniferKaisner.setUsername("JenniferK");
        jenniferKaisner.setPassword("1234");
        jenniferKaisner.setEnabled(true);
        jenniferKaisner.setFirstName("Jennifer");
        jenniferKaisner.setLastName("Kaisner");
        jenniferKaisner.setPlz("72108");
        jenniferKaisner.setBuyingLevel("Meister des Gartens");
        jenniferKaisner.setSellingLevel("Sprössling");
        jenniferKaisner.setPicturePath("https://media.rimondo.net/1363699/conversions/4e431791-5f31-4f3e-9d2f-61eef8ffd846-400.webp?v=1637496632");
        //jenniferKaisner.setNonLocked(true);
        jenniferKaisner.setRoles(userRoles);
        userService.persistUser(jenniferKaisner);



        // create users
        User jenniferK = new User();
        jenniferK.setEmail("Jennifer.K@mail.com");
        jenniferK.setUsername("JenniferKa");
        jenniferK.setPassword("12345");
        jenniferK.setEnabled(true);
        //jenniferK.setNonLocked(true);
        jenniferK.setRoles(userRoles);
        userService.persistUser(jenniferK);

        // create plant
        Plant cactus = new Plant(
                "Kaktus",
                50,
                10,
                "Mein kleiner grüner Kaktus",
                "Hin und wieder mal gießen.",
                "Zimmerpflanze",
                jenniferKaisner,
                12345,
                jenniferK
        );
        cactus.setImageUrl("https://i.pinimg.com/236x/b2/9e/4d/b29e4d6a53f3c2b86a5f3d965b1b49e3.jpg");
        plantService.persistPlant(cactus);

        Plant cactus2 = new Plant();
        cactus2.setPlantname("Kaktus");
        cactus2.setPlantSize(50);
        cactus2.setPrice(10.5);
        cactus2.setPlantDescription("Mein kleiner grüner Kaktus");
        cactus2.setCareTips("Hin und wieder mal gießen.");
        cactus2.setSold(true);
        cactus2.setImageUrl("https://i.pinimg.com/236x/b2/9e/4d/b29e4d6a53f3c2b86a5f3d965b1b49e3.jpg");
        plantService.persistPlant(cactus2);

        // create lilies
        Plant lily1 = new Plant(
                "Lilie",
                30,
                15,
                "Weiße Lilie",
                "Regelmäßig gießen und im Halbschatten halten.",
                "Outdoorpflanze",
                jenniferKaisner,
                70321,
                jenniferK
        );
        lily1.setImageUrl("https://i.pinimg.com/236x/ca/3f/3b/ca3f3b68120160f5400b562f29f27bc8.jpg");
        plantService.persistPlant(lily1);

        Plant lily2 = new Plant(
                "Lilie",
                35,
                20,
                "Gelbe Lilie",
                "Nicht zu viel gießen und viel Licht.",
                "Zimmerpflanze",
                jenniferKaisner,
                70321,
                jenniferK
        );
        lily2.setImageUrl("https://i.pinimg.com/236x/ca/3f/3b/ca3f3b68120160f5400b562f29f27bc8.jpg");
        plantService.persistPlant(lily2);

        Plant lily3 = new Plant(
                "Lilie",
                25,
                18,
                "Rote Lilie",
                "Halbschatten und gleichmäßige Feuchtigkeit.",
                "Zimmerpflanze",
                jenniferKaisner,
                70323,
                jenniferK

        );
        lily3.setImageUrl("https://i.pinimg.com/236x/ca/3f/3b/ca3f3b68120160f5400b562f29f27bc8.jpg");
        plantService.persistPlant(lily3);

    }


}
