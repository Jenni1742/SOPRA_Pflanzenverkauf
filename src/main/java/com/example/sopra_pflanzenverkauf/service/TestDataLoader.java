package com.example.sopra_pflanzenverkauf.service;

import com.example.sopra_pflanzenverkauf.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
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


    @Autowired
    private ChatJKService chatJKService;

    @Autowired
    private MessageJKService messageJKService;
    /**
     * Executed during Spring boot startup.
     *
     * @param args arguments.
     * @throws Exception exception.
     */
    @Override
    public void run(String... args) throws Exception {

        // create Category----------------------------------------------------------------------------------------------
        Category zimmerpflanze = new Category();
        zimmerpflanze.setCategoryname("Zimmerpflanze");
        categoryService.persistCategory(zimmerpflanze);

        Category outdoorpflanze = new Category();
        outdoorpflanze.setCategoryname("Outdoorpflanze");
        categoryService.persistCategory(outdoorpflanze);

        Category samen = new Category();
        samen.setCategoryname("Samen");
        categoryService.persistCategory(samen);

        // create level-------------------------------------------------------------------------------------------------
        Level level0 = new Level();
        level0.setLevelname("Korn");
        levelService.persistLevel(level0);

        Level level1 = new Level();
        level1.setLevelname("Sprössling");
        levelService.persistLevel(level1);

        Level level2 = new Level();
        level2.setLevelname("Grüner-Daumen");
        levelService.persistLevel(level2);

        Level level3 = new Level();
        level3.setLevelname("Pflanzenfreund");
        levelService.persistLevel(level3);

        Level level4 = new Level();
        level4.setLevelname("Gärtner-Novize");
        levelService.persistLevel(level4);

        Level level5 = new Level();
        level5.setLevelname("Blattmeister");
        levelService.persistLevel(level5);

        Level level6 = new Level();
        level6.setLevelname("Wurzelprofi");
        levelService.persistLevel(level6);

        Level level7 = new Level();
        level7.setLevelname("Pflanzenliebhaber");
        levelService.persistLevel(level7);

        Level level8 = new Level();
        level8.setLevelname("Botanik-Experte");
        levelService.persistLevel(level8);

        Level level9 = new Level();
        level9.setLevelname("Meister des Gartens");
        levelService.persistLevel(level9);

        // create roles-------------------------------------------------------------------------------------------------
        Role userRole = new Role();
        userRole.setRolename("ROLE_USER");
        roleService.persistRole(userRole);
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);

        // create users-------------------------------------------------------------------------------------------------
        User jenniferKaisner = new User();
        jenniferKaisner.setUsername("JenniferK");
        jenniferKaisner.setPassword("1234");
        jenniferKaisner.setFirstName("Jennifer");
        jenniferKaisner.setLastName("Kaisner");
        jenniferKaisner.setEmail("Jennifer.Kaisner@mail.com");
        jenniferKaisner.setPlz("72108");
        jenniferKaisner.setBuyingLevel(level1);
        jenniferKaisner.setSellingLevel(level1);
        jenniferKaisner.setPicturePath("https://media.rimondo.net/1363699/conversions/4e431791-5f31-4f3e-9d2f-61eef8ffd846-400.webp?v=1637496632");
        jenniferKaisner.setRoles(userRoles);
        userService.persistUser(jenniferKaisner);


        User aylinaAshkenov = new User();
        aylinaAshkenov.setUsername("AylinaA");
        aylinaAshkenov.setPassword("1234");
        aylinaAshkenov.setFirstName("Aylina");
        aylinaAshkenov.setLastName("Ashkenov");
        aylinaAshkenov.setEmail("aylinaaa.akv@mail.com");
        aylinaAshkenov.setPlz("70199");
        aylinaAshkenov.setBuyingLevel(level0);
        aylinaAshkenov.setSellingLevel(level1);
        aylinaAshkenov.setPicturePath("https://i.pinimg.com/236x/35/b1/61/35b1618bc1108909ac81f289f0814699.jpg");
        aylinaAshkenov.setRoles(userRoles);
        userService.persistUser(aylinaAshkenov);

        User alessiaSedelnikov = new User();
        alessiaSedelnikov.setUsername("AlessiaS");
        alessiaSedelnikov.setPassword("1234");
        alessiaSedelnikov.setFirstName("Alessia");
        alessiaSedelnikov.setLastName("Sedelnikov");
        alessiaSedelnikov.setEmail("alessia.sedelnikov@mail.com");
        alessiaSedelnikov.setPlz("71283");
        alessiaSedelnikov.setBuyingLevel(level1);
        alessiaSedelnikov.setSellingLevel(level1);
        alessiaSedelnikov.setPicturePath("https://i.pinimg.com/236x/94/fe/45/94fe45409a5fb7909b655e0987798216.jpg");
        alessiaSedelnikov.setRoles(userRoles);
        userService.persistUser(alessiaSedelnikov);

        User markoPetric = new User();
        markoPetric.setUsername("MarkoP");
        markoPetric.setPassword("1234");
        markoPetric.setFirstName("Marko");
        markoPetric.setLastName("Petric");
        markoPetric.setEmail("marko.petric@mail.com");
        markoPetric.setPlz("70128");
        markoPetric.setBuyingLevel(level0);
        markoPetric.setSellingLevel(level1);
        markoPetric.setPicturePath("https://i.pinimg.com/474x/81/47/f6/8147f66671e37ef12238c49e42346953.jpg");
        markoPetric.setRoles(userRoles);
        userService.persistUser(markoPetric);

        User franziskaHornung = new User();
        franziskaHornung.setUsername("FranziH");
        franziskaHornung.setPassword("1234");
        franziskaHornung.setFirstName("Franziska");
        franziskaHornung.setLastName("Hornung");
        franziskaHornung.setEmail("franziskaHornung@mail.com");
        franziskaHornung.setPlz("78292");
        franziskaHornung.setBuyingLevel(level0);
        franziskaHornung.setSellingLevel(level1);
        franziskaHornung.setPicturePath("https://i.pinimg.com/236x/0a/ea/c8/0aeac8c1c7a0cebb97a01a26794498ac.jpg");
        franziskaHornung.setRoles(userRoles);
        userService.persistUser(franziskaHornung);

        // create plants-----------------------------------------------------------------------------------------------

        /*
        A3-V löscht sein Profil aber hat schon P verkauft --Funktioniert
        → Bei K muss Verkäufer auf null gesetzt werden
        → Verkaufte P V auf null (SoldPlants)

        A4-K löscht sein Profil aber hat schon P gekauft --Funktioniert
        → Bei V muss Käufer auf null gesetzt werden
        → Gekaufte P K auf null (purchasedPlants)
        */


        Plant kniphofia = new Plant(
                "Kniphofia",
                35,
                20,
                "Was eine wunderschöne Pflanze! Die liebe Kniphofia sucht ein neues Zuhause",
                "Nicht zu viel gießen und viel Licht.",
                outdoorpflanze,
                null,
                70321,
                alessiaSedelnikov,
                true,
                "https://gaerten.uni-hohenheim.de/fileadmin/_processed_/csm_pfl-quartier3kniphofia_03_0734dfd3c0.jpg"
        );
        plantService.persistPlant(kniphofia);

        //Alessia kauft die Pflanze von Jennifer
        kniphofia.setSellerWhenSold(jenniferKaisner);
        plantService.updatePlant(kniphofia);
        userService.updateNumberOfSoldPlants(jenniferKaisner);
        userService.updateNumberOfPurchasedPlants(alessiaSedelnikov);


        Plant occidentalis = new Plant(
                "Cephalanthus",
                25,
                180,
                "Sieht ein bisschen so aus wie eine Kastanie oder Corona ",
                "Sonne und hohe Luftfeuchtigkeit sind notwendig!",
                zimmerpflanze,
                jenniferKaisner,
                70323,
                false,
                "https://gaerten.uni-hohenheim.de/fileadmin/_processed_/csm_Cephalanthus_occidentalis_2c068fa11a.jpg"

        );
        plantService.persistPlant(occidentalis);

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
                true,
                "https://gaerten.uni-hohenheim.de/typo3temp/_processed_/csm_HD_DE_HOH_DP_2013_-_00301i_d44bb98a17.jpg"

        );
        plantService.persistPlant(hahn);

        //Jennifer kauft die Pflanze von Alessia
        hahn.setSeller(null);
        hahn.setSellerWhenSold(alessiaSedelnikov);
        plantService.updatePlant(hahn);
        userService.updateNumberOfSoldPlants(alessiaSedelnikov);
        userService.updateNumberOfPurchasedPlants(jenniferKaisner);

        // Weitere Pflanzen


        Plant jessica = new Plant(
                "Jessica",
                55,
                4,
                "Ich habe eine neue lieblingspflanze gekauft und brauch die jetzt nicht mehr",
                "Wasser, Sonne und Liebe",
                outdoorpflanze,
                jenniferKaisner,
                56789,
                false,
                "https://cdn.pixabay.com/photo/2011/04/06/15/05/garden-6239_1280.jpg"
        );
        plantService.persistPlant(jessica);



        ChatJK chatjk = new ChatJK();
        chatjk.setRecipientOfChat(alessiaSedelnikov);
        chatjk.setSenderOfChat(jenniferKaisner);
        chatjk.setChatPlant(hahn);
        chatjk.setRecipientAccept(false);
        chatjk.setSenderAccept(false);
        chatJKService.updateChatJK(chatjk);

        MessageJK messageJK = new MessageJK();
        messageJK.setContent("Hallo Alessia, ich würde gerne das Hahnenfussgewächs von dir kaufen. Ich wohne ganz in der Nähe.");
        messageJK.setRecipient(alessiaSedelnikov);
        messageJK.setSender(jenniferKaisner);
        messageJK.setAssociatedChat(chatjk);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        messageJK.setTimestamp(timestamp.toLocalDateTime());
        messageJKService.updateMessageJK(messageJK);
        chatJKService.updateChatJK(chatjk);

        MessageJK messageJK2 = new MessageJK();
        messageJK2.setContent("Hallo Jennifer, das freut mich! Wann möchtest du die Pflanze abholen?");
        messageJK2.setRecipient(jenniferKaisner);
        messageJK2.setSender(alessiaSedelnikov);
        messageJK2.setAssociatedChat(chatjk);
        Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
        messageJK2.setTimestamp(timestamp2.toLocalDateTime());
        messageJKService.updateMessageJK(messageJK2);
        chatJKService.updateChatJK(chatjk);

        MessageJK messageJK3 = new MessageJK();
        messageJK3.setContent("Samstag Nachmittag?");
        messageJK3.setRecipient(alessiaSedelnikov);
        messageJK3.setSender(jenniferKaisner);
        messageJK3.setAssociatedChat(chatjk);
        Timestamp timestamp3 = new Timestamp(System.currentTimeMillis());
        messageJK3.setTimestamp(timestamp.toLocalDateTime());
        messageJKService.updateMessageJK(messageJK3);
        chatJKService.updateChatJK(chatjk);

        MessageJK messageJK4 = new MessageJK();
        messageJK4.setContent("Ja. Komm gerne gegen 15 Uhr vorbei. Adresse ist die Beispielstraße 1 71283 Beispielstadt.");
        messageJK4.setRecipient(jenniferKaisner);
        messageJK4.setSender(alessiaSedelnikov);
        messageJK4.setAssociatedChat(chatjk);
        Timestamp timestamp4 = new Timestamp(System.currentTimeMillis());
        messageJK4.setTimestamp(timestamp4.toLocalDateTime());
        messageJKService.updateMessageJK(messageJK4);
        chatJKService.updateChatJK(chatjk);

        MessageJK messageJK5 = new MessageJK();
        messageJK5.setContent("Alles klar, dann bis Samstag!");
        messageJK5.setRecipient(alessiaSedelnikov);
        messageJK5.setSender(jenniferKaisner);
        messageJK5.setAssociatedChat(chatjk);
        Timestamp timestamp5 = new Timestamp(System.currentTimeMillis());
        messageJK5.setTimestamp(timestamp.toLocalDateTime());
        messageJKService.updateMessageJK(messageJK5);
        chatJKService.updateChatJK(chatjk);



    }
}

/*
Fehlerquellen:


A) Mögliche Fehlerquellen beim Profil löschen: ----------------------------------------------------------------------------

A1- K hat P in Merkliste und der V löscht sein Profil --Funktioniert
--> P muss aus Merkliste entfernt werden
--> Aktive P müssen gelöscht werden (PlantsToSell)

A2-V löscht sein Profil aber hat P eingestellt --Funktioniert
--> P muss aus Home, Searchresults und Merklisten entfernt werden
--> Aktive P müssen gelöscht werden (PlantsToSell)

A3-V löscht sein Profil aber hat schon P verkauft --Funktioniert
--> Bei K muss Verkäufer auf null gesetzt werden
--> Verkaufte P V auf null (SoldPlants)

A4-K löscht sein Profil aber hat schon P gekauft --Funktioniert
--> Bei V muss Käufer auf null gesetzt werden
--> Gekaufte P K auf null (purchasedPlants)

A5-V löscht sein Profil
--> Nachrichtenaustausch muss gestoppt werden


B) Mögliche Fehlerquellen beim Anzeige löschen: ----------------------------------------------------------------------------

B1- P wurde in Merklisten von mehreren K hinzugefügt
-->P muss aus allen Merklisten entfernt werden
--> Chat muss gestoppt werden
--> P muss aus Home und searchresults verschwinden

C) Mögliche Fehlerquellen beim Kauf -------------------------------------------------------------------------------------

C1- K kauft P von V
--> P muss von Home und Searchresults verschwinden
--> P muss aus PlantsToSell raus
--> P muss beiK in gek. P
--> P muss bei V in verk. P
 */