package com.example.sopra_pflanzenverkauf.service;

import com.example.sopra_pflanzenverkauf.entity.*;
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
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private LevelService levelService;

    /**
     * Executed during Spring boot startup.
     * @param args arguments.
     * @throws Exception exception.
     */
    @Override
    public void run(String... args) throws Exception {

        //create Category
        Category zimmerpflanze = new Category();
        zimmerpflanze.setCategoryname("Zimmerpflanze");
        categoryService.persistCategory(zimmerpflanze);

        Category outdoorpflanze = new Category();
        outdoorpflanze.setCategoryname("Outdoorpflanze");
        categoryService.persistCategory(outdoorpflanze);

        //create Level
        Level level0 = new Level();
        level0.setLevelname("Korn");
        levelService.persistLevel(level0);

        Level level1 = new Level();
        level1.setLevelname("Sprössling");
        levelService.persistLevel(level1);

        Level level2 = new Level();
        level2.setLevelname("Meister des Gartens");
        levelService.persistLevel(level2);

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
        jenniferKaisner.setSellingLevel(level1);
        jenniferKaisner.setBuyingLevel(level2);
        jenniferKaisner.setPicturePath("https://media.rimondo.net/1363699/conversions/4e431791-5f31-4f3e-9d2f-61eef8ffd846-400.webp?v=1637496632");
        //jenniferKaisner.setNonLocked(true);
        jenniferKaisner.setRoles(userRoles);
        userService.persistUser(jenniferKaisner);


        User aylinaAshkenov = new User();
        aylinaAshkenov.setEmail("aylinaaa.akv@mail.com");
        aylinaAshkenov.setUsername("AylinaA");
        aylinaAshkenov.setPassword("5678");
        aylinaAshkenov.setEnabled(true);
        aylinaAshkenov.setFirstName("Aylina");
        aylinaAshkenov.setLastName("Ashkenov");
        aylinaAshkenov.setPlz("70199");
        aylinaAshkenov.setSellingLevel(level1);
        aylinaAshkenov.setBuyingLevel(level2);
        aylinaAshkenov.setPicturePath("https://i.pinimg.com/236x/35/b1/61/35b1618bc1108909ac81f289f0814699.jpg");
        aylinaAshkenov.setRoles(userRoles);
        userService.persistUser(aylinaAshkenov);

        User alessiaSedelnikov = new User();
        alessiaSedelnikov.setEmail("alessia.sedelnikov@mail.com");
        alessiaSedelnikov.setUsername("AlessiaS");
        alessiaSedelnikov.setPassword("2345");
        alessiaSedelnikov.setEnabled(true);
        alessiaSedelnikov.setFirstName("Alessia");
        alessiaSedelnikov.setLastName("Sedelnikov");
        alessiaSedelnikov.setPlz("71283");
        alessiaSedelnikov.setSellingLevel(level1);
        alessiaSedelnikov.setBuyingLevel(level2);
        alessiaSedelnikov.setPicturePath("https://i.pinimg.com/236x/94/fe/45/94fe45409a5fb7909b655e0987798216.jpg");
        alessiaSedelnikov.setRoles(userRoles);
        userService.persistUser(alessiaSedelnikov);

        User markoPetric = new User();
        markoPetric.setEmail("marko.petric@mail.com");
        markoPetric.setUsername("MarkoP");
        markoPetric.setPassword("3456");
        markoPetric.setEnabled(true);
        markoPetric.setFirstName("Marko");
        markoPetric.setLastName("Petric");
        markoPetric.setPlz("70128");
        markoPetric.setSellingLevel(level1);
        markoPetric.setBuyingLevel(level2);
        markoPetric.setPicturePath("https://i.pinimg.com/474x/81/47/f6/8147f66671e37ef12238c49e42346953.jpg");
        markoPetric.setRoles(userRoles);
        userService.persistUser(markoPetric);

        User franziskaHornung = new User();
        franziskaHornung.setEmail("franziskaHornung@mail.com");
        franziskaHornung.setUsername("FranziH");
        franziskaHornung.setPassword("4567");
        franziskaHornung.setEnabled(true);
        franziskaHornung.setFirstName("Franziska");
        franziskaHornung.setLastName("Hornung");
        franziskaHornung.setPlz("78292");
        franziskaHornung.setSellingLevel(level1);
        franziskaHornung.setBuyingLevel(level2);
        franziskaHornung.setPicturePath("https://i.pinimg.com/236x/0a/ea/c8/0aeac8c1c7a0cebb97a01a26794498ac.jpg");
        franziskaHornung.setRoles(userRoles);
        userService.persistUser(franziskaHornung);

        // create plants-----------------------------------------------------------------------------------------------

        Plant kaffeestrauch = new Plant(
                "Kaffeestrauch",
                50,
                10,
                "Der Kaffeestrauch ist besonders, weil er mir jeden Morgen beim Kaffee trinken Gesellschaft geleistet hat. Da ich jetzt eine Partnerin gefunden habe, bin ich bereit meine Pflanze einem anderem Single zu überlassen",
                "Hin und wieder mal gießen. Etwas Kaffe mit ins Wasser geben",
                zimmerpflanze,
                markoPetric,
                12345,
                alessiaSedelnikov,
                true
        );
        kaffeestrauch.setImageUrl("https://www.blumen-brueder.de/Content/files/1886/Coffea-arabica-600x800-proportionalsmallest.webp");
        plantService.persistPlant(kaffeestrauch);

        alessiaSedelnikov.getPurchasedPlants().add(kaffeestrauch);
        userService.updatePurchasedPlants(alessiaSedelnikov);
        userService.updateNumberOfPurchasedPlants(alessiaSedelnikov);

        markoPetric.getSoldPlantsList().add(kaffeestrauch);
        userService.updateSoldPlantsList(markoPetric);
        userService.updateNumberOfSoldPlants(markoPetric);

        Plant allium = new Plant(
                "Allium",
                30,
                15,
                "Allium - auch als Zwibelpflanze bekannt. ",
                "Regelmäßig gießen und im Halbschatten halten.",
                outdoorpflanze,
                alessiaSedelnikov,
                70321,
                false
        );
        allium.setImageUrl("https://gaerten.uni-hohenheim.de/fileadmin/_processed_/csm_pfl-quartier2allium_03_e929dc3231.jpg");
        plantService.persistPlant(allium);

        Plant kniphofia = new Plant(
                "Kniphofia",
                35,
                20,
                "Was eine wunderschöne Pflanze! Die liebe Kniphofia sucht ein neues Zuhause",
                "Nicht zu viel gießen und viel Licht.",
                outdoorpflanze,
                jenniferKaisner,
                70321,
                alessiaSedelnikov,
                true
        );
        kniphofia.setImageUrl("https://gaerten.uni-hohenheim.de/fileadmin/_processed_/csm_pfl-quartier3kniphofia_03_0734dfd3c0.jpg");
        plantService.persistPlant(kniphofia);
        jenniferKaisner.getPlantsToSell().add(kniphofia);
        userService.updatePlantsToSell(jenniferKaisner);

        alessiaSedelnikov.getPurchasedPlants().add(kniphofia);
        userService.updatePurchasedPlants(alessiaSedelnikov);
        userService.updateNumberOfPurchasedPlants(alessiaSedelnikov);

        jenniferKaisner.getSoldPlantsList().add(kniphofia);
        userService.updateSoldPlantsList(jenniferKaisner);
        userService.updateNumberOfSoldPlants(jenniferKaisner);

        Plant melanostachy = new Plant(
                "Salix melanostachys",
                25,
                180,
                "melanostachy ist eine Pflanze, die im Juni blüht.",
                "Halbschatten und gleichmäßige Feuchtigkeit.",
                zimmerpflanze,
                franziskaHornung,
                70323,
                false

        );
        melanostachy.setImageUrl("https://gaerten.uni-hohenheim.de/fileadmin/_processed_/csm_Salix_melanostachys_ed7602445f.jpg");
        plantService.persistPlant(melanostachy);


        Plant prunus = new Plant(
                "Prunus subhirtella autumnalis",
                25,
                180,
                "Die Pflanze namens Prunus subhirtella autumnalis kann man nicht beschreiben, die muss man einfach sehen! Blüht hübsch in pink und weiß. Perfekt für Instagram-Bilder ",
                "Einfach nur in die Sonne stellen.",
                zimmerpflanze,
                aylinaAshkenov,
                70323,
                false

        );
        prunus.setImageUrl("https://gaerten.uni-hohenheim.de/fileadmin/_processed_/csm_Prunus_subhirtella_autumnalis_811bfd6a1f.jpg");
        plantService.persistPlant(prunus);


        Plant occidentalis = new Plant(
                "Cephalanthus occidentalis",
                25,
                180,
                "Sieht ein bisschen so aus wie eine Kastanie oder Corona ",
                "Sonne und hohe Luftfeuchtigkeit sind notwendig!",
                zimmerpflanze,
                jenniferKaisner,
                70323,
                false

        );
        occidentalis.setImageUrl("https://gaerten.uni-hohenheim.de/fileadmin/_processed_/csm_Cephalanthus_occidentalis_2c068fa11a.jpg");
        plantService.persistPlant(occidentalis);
        jenniferKaisner.getPlantsToSell().add(occidentalis);
        userService.updatePlantsToSell(jenniferKaisner);


        Plant hahn = new Plant(
                "Hahnenfussgewächs",
                25,
                180,
                "Sieht ein bisschen so aus wie eine Kastanie oder Corona ",
                "Sonne und hohe Luftfeuchtigkeit sind notwendig!",
                zimmerpflanze,
                alessiaSedelnikov,
                70323,
                jenniferKaisner,
                true

        );
        hahn.setImageUrl("https://gaerten.uni-hohenheim.de/typo3temp/_processed_/csm_HD_DE_HOH_DP_2013_-_00301i_d44bb98a17.jpg");
        plantService.persistPlant(hahn);
        jenniferKaisner.getPurchasedPlants().add(hahn);
        userService.updatePurchasedPlants(jenniferKaisner);
        userService.updateNumberOfPurchasedPlants(jenniferKaisner);

        alessiaSedelnikov.getSoldPlantsList().add(hahn);
        userService.updateSoldPlantsList(alessiaSedelnikov);
        userService.updateNumberOfSoldPlants(alessiaSedelnikov);


        // create wishlist-----------------------------------------------------------------------------------------------

        Set<Plant> wishlistPlants = new HashSet<>();
        wishlistPlants.add(prunus);
        wishlistPlants.add(melanostachy);
        wishlistPlants.add(allium);

        jenniferKaisner.setWishlistPlants(wishlistPlants);
        userService.updateWishlist(jenniferKaisner);
    }


}
