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

    @Autowired
    private MessageService messageService;

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
        level1.setLevelname("Grüner-Daumen");
        levelService.persistLevel(level2);

        Level level3 = new Level();
        level1.setLevelname("Pflanzenfreund");
        levelService.persistLevel(level3);

        Level level4 = new Level();
        level1.setLevelname("Gärtner-Novize");
        levelService.persistLevel(level4);

        Level level5 = new Level();
        level1.setLevelname("Blattmeister");
        levelService.persistLevel(level5);

        Level level6 = new Level();
        level1.setLevelname("Wurzelprofi");
        levelService.persistLevel(level6);

        Level level7 = new Level();
        level2.setLevelname("Pflanzenliebhaber");
        levelService.persistLevel(level7);

        Level level8 = new Level();
        level2.setLevelname("Botanik-Experte");
        levelService.persistLevel(level8);

        Level level9 = new Level();
        level2.setLevelname("Meister des Gartens");
        levelService.persistLevel(level7);

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

        Plant kaffeestrauch = new Plant(
                "Kaffeestrauch",
                50,
                10,
                "Der Kaffeestrauch ist besonders, weil er mir jeden Morgen beim Kaffee trinken Gesellschaft geleistet hat. Da ich jetzt eine Partnerin gefunden habe, bin ich bereit meine Pflanze einem anderem Single zu überlassen",
                "Hin und wieder mal gießen. Etwas Kaffe mit ins Wasser geben",
                zimmerpflanze,
                null,
                12345,
                alessiaSedelnikov,
                true,
                "https://www.blumen-brueder.de/Content/files/1886/Coffea-arabica-600x800-proportionalsmallest.webp"
        );
        plantService.persistPlant(kaffeestrauch);

        //Alessia kauft die Pflanze von Marko
        kaffeestrauch.setSellerWhenSold(markoPetric);
        plantService.updatePlant(kaffeestrauch);
        userService.updateNumberOfSoldPlants(markoPetric);
        userService.updateNumberOfPurchasedPlants(alessiaSedelnikov);


        Plant allium = new Plant(
                "Allium",
                30,
                15,
                "Allium - auch als Zwibelpflanze bekannt. ",
                "Regelmäßig gießen und im Halbschatten halten.",
                outdoorpflanze,
                alessiaSedelnikov,
                70321,
                false,
                "https://gaerten.uni-hohenheim.de/fileadmin/_processed_/csm_pfl-quartier2allium_03_e929dc3231.jpg"
        );
        plantService.persistPlant(allium);

        /*
        A1- K hat P in Merkliste und der V löscht sein Profil --Funktioniert
        → P muss aus Merkliste entfernt werden
        → Aktive P müssen gelöscht werden (PlantsToSell)

        A2-V löscht sein Profil aber hat P eingestellt  --Funktioniert
        --> P muss aus Home, Searchresults und Merklisten entfernt werden
        --> Aktive P müssen gelöscht werden (PlantsToSell)
        */

        //Jennifer fügt Pflanze ihrer Merkliste hinzu                 create Wishlist-------------------------------------------------
        Set<Plant> wishlistPlants = new HashSet<>();
        wishlistPlants.add(allium);
        jenniferKaisner.setWishlistPlants(wishlistPlants);
        userService.updateWishlist(jenniferKaisner);


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

        Plant melanostachy = new Plant(
                "Salix melanostachys",
                25,
                180,
                "melanostachy ist eine Pflanze, die im Juni blüht.",
                "Halbschatten und gleichmäßige Feuchtigkeit.",
                zimmerpflanze,
                franziskaHornung,
                70323,
                false,
                "https://cdn.pixabay.com/photo/2016/05/24/17/42/lotus-1412858_1280.jpg"

        );
        plantService.persistPlant(melanostachy);

        //Jennifer fügt Pflanze ihrer Merkliste hinzu
        jenniferKaisner.getWishlistPlants().add(melanostachy);
        userService.updateWishlist(jenniferKaisner);

        Plant prunus = new Plant(
                "Prunus subhirtella",
                25,
                180,
                "Die Pflanze namens Prunus subhirtella autumnalis kann man nicht beschreiben, die muss man einfach sehen! Blüht hübsch in pink und weiß. Perfekt für Instagram-Bilder ",
                "Einfach nur in die Sonne stellen.",
                zimmerpflanze,
                aylinaAshkenov,
                70323,
                false,
                "https://gaerten.uni-hohenheim.de/fileadmin/_processed_/csm_Prunus_subhirtella_autumnalis_811bfd6a1f.jpg"

        );
        plantService.persistPlant(prunus);

        //Jennifer fügt Pflanze ihrer Merkliste hinzu
        jenniferKaisner.getWishlistPlants().add(prunus);
        userService.updateWishlist(jenniferKaisner);

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
        Plant titanenwurzel = new Plant(
                "Titanenwurzel",
                160,
                100000,
                "Die Titanenwurz gilt als die größte Blume der Welt. An der Uni Hohenheim hat sie nun schon zum zweiten Mal geblüht. Das Interesse war groß, der Gestank auch.",
                "Du musst schon studieren, um zu wissen, wie man mit dieser Pflanze umgehen muss",
                outdoorpflanze,
                alessiaSedelnikov,
                56783,
                false,
                "https://www.swr.de/swraktuell/baden-wuerttemberg/stuttgart/1713352797444%2Ctitanenwurz-uni-hohenheim-stuttgart-100~_v-16x9@2dL_-6c42aff4e68b43c7868c3240d3ebfa29867457da.jpg"
        );
        plantService.persistPlant(titanenwurzel);


        Plant heidekraut = new Plant(
                "Heidekruat namens Erika",
                15,
                10,
                "Du kommst aus Heidenheim und hast noch keine Heidkraut? Hol dir diese Pflanze!",
                "Hin und wieder mal gießen. Ganz viel Sonne!",
                zimmerpflanze,
                aylinaAshkenov,
                23456,
                false,
                "https://cdn.pixabay.com/photo/2018/09/13/18/18/heide-3675445_1280.jpg"
        );
        plantService.persistPlant(heidekraut);

        Plant lotus = new Plant(
                "Lotus grüne Pflanze",
                25,
                20,
                "Jetzt wo ich auf Diät bin und keine Lotuskekse mehr essen kann, muss ich mich auch von der lotus Pflanze verabschieden, da sie mich zu sehr an die Leckerei erinnert mit ihrem Namen",
                "Jede woche mit 100 ml bewässern",
                zimmerpflanze,
                aylinaAshkenov,
                23456,
                false,
                "data:image/jpeg;base64,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"        );
        plantService.persistPlant(lotus);

        Plant kleingruen = new Plant(
                "Grüne Pflanze",
                5,
                5,
                "Erlich kein plan was das für eine Pflanze ist",
                "Jede woche mit 500 ml bewässern",
                zimmerpflanze,
                markoPetric,
                34567,
                false,
                "https://cdn.pixabay.com/photo/2019/02/25/20/29/houseplant-4020532_1280.jpg"
        );
        plantService.persistPlant(kleingruen);


        Plant jessica = new Plant(
                "Jessica der Traum in orange und weiß",
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

        Plant blau = new Plant(
                "Blaues Baby",
                2,
                40,
                "Hast du Blau mit Tau, wirst du zu Blau mit Frau.... Eine Dichter und Denker Pflanze",
                "Wasser, Sonne und Liebe",
                outdoorpflanze,
                aylinaAshkenov,
                70327,
                false,
                "https://cdn.pixabay.com/photo/2017/04/03/07/30/blue-flower-2197679_1280.jpg"
        );
        plantService.persistPlant(blau);


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