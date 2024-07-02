package com.example.sopra_pflanzenverkauf.service;

import com.example.sopra_pflanzenverkauf.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
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
    private ChatService chatService;

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
        alessiaSedelnikov.setPlantCoinCount(100);
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
                true,
                "https://cdn.pixabay.com/photo/2016/05/24/17/42/lotus-1412858_1280.jpg",
                null

        );

        File fnew=new File("C:\\Users\\hornu\\IdeaProjects\\SOPRA_Pflanzenverkauf\\src\\main\\resources\\static\\images\\plantPictures\\lotus-1412858_1280.jpg");
        BufferedImage originalImage=ImageIO.read(fnew);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        ImageIO.write(originalImage, "jpg", baos );
        byte[] imageInByte=baos.toByteArray();

        melanostachy.setImage(imageInByte);

        plantService.persistPlant(melanostachy);
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
                false,
                "https://www.blumen-brueder.de/Content/files/1886/Coffea-arabica-600x800-proportionalsmallest.webp"
        );
        plantService.persistPlant(kaffeestrauch);

        //Alessia kauft die Pflanze von Marko
        kaffeestrauch.setSellerWhenSold(markoPetric);
        plantService.updatePlant(kaffeestrauch);
        markoPetric.setNumberOfSoldPlants(markoPetric.getNumberOfSoldPlants() + 1);
        userService.updateNumberOfSoldPlants(markoPetric);
        alessiaSedelnikov.setNumberOfPurchasedPlants(alessiaSedelnikov.getNumberOfPurchasedPlants() + 1);
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
                false,
                "https://gaerten.uni-hohenheim.de/fileadmin/_processed_/csm_pfl-quartier2allium_03_e929dc3231.jpg",
                null
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
        //Set<Plant> wishlistPlants = new HashSet<>();
        //wishlistPlants.add(allium);
        //jenniferKaisner.setWishlistPlants(wishlistPlants);
        //userService.updateWishlist(jenniferKaisner);


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
                false,
                "https://gaerten.uni-hohenheim.de/fileadmin/_processed_/csm_pfl-quartier3kniphofia_03_0734dfd3c0.jpg"
        );
        plantService.persistPlant(kniphofia);

        //Alessia kauft die Pflanze von Jennifer
        kniphofia.setSellerWhenSold(jenniferKaisner);
        plantService.updatePlant(kniphofia);
        jenniferKaisner.setNumberOfSoldPlants(jenniferKaisner.getNumberOfSoldPlants() + 1);
        userService.updateNumberOfSoldPlants(jenniferKaisner);
        alessiaSedelnikov.setNumberOfPurchasedPlants(alessiaSedelnikov.getNumberOfPurchasedPlants() + 1);
        userService.updateNumberOfPurchasedPlants(alessiaSedelnikov);


        //Jennifer fügt Pflanze ihrer Merkliste hinzu
        //jenniferKaisner.getWishlistPlants().add(melanostachy);
        //userService.updateWishlist(jenniferKaisner);

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
                true,
                "https://gaerten.uni-hohenheim.de/fileadmin/_processed_/csm_Prunus_subhirtella_autumnalis_811bfd6a1f.jpg",
                null

        );
        plantService.persistPlant(prunus);

        //Jennifer fügt Pflanze ihrer Merkliste hinzu
        //jenniferKaisner.getWishlistPlants().add(prunus);
        //userService.updateWishlist(jenniferKaisner);

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
                false,
                "https://gaerten.uni-hohenheim.de/fileadmin/_processed_/csm_Cephalanthus_occidentalis_2c068fa11a.jpg",
                null

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
                false,
                "https://gaerten.uni-hohenheim.de/typo3temp/_processed_/csm_HD_DE_HOH_DP_2013_-_00301i_d44bb98a17.jpg"

        );
        plantService.persistPlant(hahn);

        //Jennifer kauft die Pflanze von Alessia
        hahn.setSeller(null);
        hahn.setSellerWhenSold(alessiaSedelnikov);
        plantService.updatePlant(hahn);
        alessiaSedelnikov.setNumberOfSoldPlants(alessiaSedelnikov.getNumberOfSoldPlants() + 1);
        userService.updateNumberOfSoldPlants(alessiaSedelnikov);
        jenniferKaisner.setNumberOfPurchasedPlants(jenniferKaisner.getNumberOfPurchasedPlants() + 1);
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
                false,
                "https://www.swr.de/swraktuell/baden-wuerttemberg/stuttgart/1713352797444%2Ctitanenwurz-uni-hohenheim-stuttgart-100~_v-16x9@2dL_-6c42aff4e68b43c7868c3240d3ebfa29867457da.jpg",
                null
        );
        plantService.persistPlant(titanenwurzel);


        Plant heidekraut = new Plant(
                "Heidekruat Erika",
                15,
                10,
                "Du kommst aus Heidenheim und hast noch keine Heidkraut? Hol dir diese Pflanze!",
                "Hin und wieder mal gießen. Ganz viel Sonne!",
                zimmerpflanze,
                aylinaAshkenov,
                23456,
                false,
                false,
                "https://cdn.pixabay.com/photo/2018/09/13/18/18/heide-3675445_1280.jpg",
                null
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
                true,
                "https://www.lubera.com/images/600/lotus-blatt-e1481172403391.jpg",
                null
        );

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
                false,
                "https://cdn.pixabay.com/photo/2019/02/25/20/29/houseplant-4020532_1280.jpg",
                null
        );
        plantService.persistPlant(kleingruen);


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
                true,
                "https://cdn.pixabay.com/photo/2011/04/06/15/05/garden-6239_1280.jpg",
                null
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
                false,
                "https://cdn.pixabay.com/photo/2017/04/03/07/30/blue-flower-2197679_1280.jpg",
                null
        );
        plantService.persistPlant(blau);



        Chat chatTest = new Chat();
        chatTest.setRecipientOfChat(alessiaSedelnikov);
        chatTest.setSenderOfChat(jenniferKaisner);
        chatTest.setChatPlant(hahn);
        chatTest.setRecipientAccept(false);
        chatTest.setSenderAccept(false);
        chatService.updateChat(chatTest);

        Message message = new Message();
        message.setContent("Hallo Alessia, ich würde gerne das Hahnenfussgewächs von dir kaufen. Ich wohne ganz in der Nähe.");
        message.setRecipient(alessiaSedelnikov);
        message.setSender(jenniferKaisner);
        message.setAssociatedChat(chatTest);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        message.setTimestamp(timestamp.toLocalDateTime());
        messageService.updateMessage(message);
        chatService.updateChat(chatTest);

        Message message2 = new Message();
        message2.setContent("Hallo Jennifer, das freut mich! Wann möchtest du die Pflanze abholen?");
        message2.setRecipient(jenniferKaisner);
        message2.setSender(alessiaSedelnikov);
        message2.setAssociatedChat(chatTest);
        Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
        message2.setTimestamp(timestamp2.toLocalDateTime());
        messageService.updateMessage(message2);
        chatService.updateChat(chatTest);

        Message message3 = new Message();
        message3.setContent("Samstag Nachmittag?");
        message3.setRecipient(alessiaSedelnikov);
        message3.setSender(jenniferKaisner);
        message3.setAssociatedChat(chatTest);
        Timestamp timestamp3 = new Timestamp(System.currentTimeMillis());
        message3.setTimestamp(timestamp.toLocalDateTime());
        messageService.updateMessage(message3);
        chatService.updateChat(chatTest);

        Message message4 = new Message();
        message4.setContent("Ja. Komm gerne gegen 15 Uhr vorbei. Adresse ist die Beispielstraße 1 71283 Beispielstadt.");
        message4.setRecipient(jenniferKaisner);
        message4.setSender(alessiaSedelnikov);
        message4.setAssociatedChat(chatTest);
        Timestamp timestamp4 = new Timestamp(System.currentTimeMillis());
        message4.setTimestamp(timestamp4.toLocalDateTime());
        messageService.updateMessage(message4);
        chatService.updateChat(chatTest);

        Message message5 = new Message();
        message5.setContent("Alles klar, dann bis Samstag!");
        message5.setRecipient(alessiaSedelnikov);
        message5.setSender(jenniferKaisner);
        message5.setAssociatedChat(chatTest);
        Timestamp timestamp5 = new Timestamp(System.currentTimeMillis());
        message5.setTimestamp(timestamp.toLocalDateTime());
        messageService.updateMessage(message5);
        chatService.updateChat(chatTest);



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