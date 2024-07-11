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
    private ChatService chatService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private CaretipService caretipService;
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
        jenniferKaisner.setPassword("12345678");
        jenniferKaisner.setFirstName("Jennifer");
        jenniferKaisner.setLastName("Kaisner");
        jenniferKaisner.setEmail("Jennifer.Kaisner@mail.com");
        jenniferKaisner.setPlz("72108");
        jenniferKaisner.setBuyingLevel(level1);
        jenniferKaisner.setSellingLevel(level1);
        //jenniferKaisner.setPicturePath("https://media.rimondo.net/1363699/conversions/4e431791-5f31-4f3e-9d2f-61eef8ffd846-400.webp?v=1637496632");
        jenniferKaisner.setImage(jenniferKaisner.imageInBytes("src/main/resources/static/images/profilpictures/ProfilbildJenni.jpg"));
        jenniferKaisner.setRoles(userRoles);
        jenniferKaisner.setPlantCoinCount(9);
        userService.persistUser(jenniferKaisner);


        User aylinaAshkenov = new User();
        aylinaAshkenov.setUsername("AylinaA");
        aylinaAshkenov.setPassword("12345678");
        aylinaAshkenov.setFirstName("Aylina");
        aylinaAshkenov.setLastName("Ashkenov");
        aylinaAshkenov.setEmail("aylinaaa.akv@mail.com");
        aylinaAshkenov.setPlz("70199");
        aylinaAshkenov.setBuyingLevel(level0);
        aylinaAshkenov.setSellingLevel(level1);
        //aylinaAshkenov.setPicturePath("https://i.pinimg.com/236x/35/b1/61/35b1618bc1108909ac81f289f0814699.jpg");
        aylinaAshkenov.setImage(aylinaAshkenov.imageInBytes("src/main/resources/static/images/profilpictures/ProfilbildAylina.jpg"));
        aylinaAshkenov.setRoles(userRoles);
        userService.persistUser(aylinaAshkenov);

        User alessiaSedelnikov = new User();
        alessiaSedelnikov.setUsername("AlessiaS");
        alessiaSedelnikov.setPassword("12345678");
        alessiaSedelnikov.setFirstName("Alessia");
        alessiaSedelnikov.setLastName("Sedelnikov");
        alessiaSedelnikov.setEmail("alessia.sedelnikov@mail.com");
        alessiaSedelnikov.setPlz("71283");
        alessiaSedelnikov.setBuyingLevel(level1);
        alessiaSedelnikov.setSellingLevel(level1);
        //alessiaSedelnikov.setPicturePath("https://i.pinimg.com/236x/94/fe/45/94fe45409a5fb7909b655e0987798216.jpg");
        alessiaSedelnikov.setImage(alessiaSedelnikov.imageInBytes("src/main/resources/static/images/profilpictures/Alessia PB.jpg"));
        alessiaSedelnikov.setRoles(userRoles);
        alessiaSedelnikov.setPlantCoinCount(100);
        userService.persistUser(alessiaSedelnikov);

        User markoPetric = new User();
        markoPetric.setUsername("MarkoP");
        markoPetric.setPassword("12345678");
        markoPetric.setFirstName("Marko");
        markoPetric.setLastName("Petric");
        markoPetric.setEmail("marko.petric@mail.com");
        markoPetric.setPlz("70128");
        markoPetric.setBuyingLevel(level0);
        markoPetric.setSellingLevel(level1);
        //markoPetric.setPicturePath("https://i.pinimg.com/474x/81/47/f6/8147f66671e37ef12238c49e42346953.jpg");
        markoPetric.setImage(markoPetric.imageInBytes("src/main/resources/static/images/profilpictures/ProfilbildMarko.jpg"));
        markoPetric.setRoles(userRoles);
        userService.persistUser(markoPetric);

        User franziskaHornung = new User();
        franziskaHornung.setUsername("FranziH");
        franziskaHornung.setPassword("12345678");
        franziskaHornung.setFirstName("Franziska");
        franziskaHornung.setLastName("Hornung");
        franziskaHornung.setEmail("franziskaHornung@mail.com");
        franziskaHornung.setPlz("78292");
        franziskaHornung.setBuyingLevel(level0);
        franziskaHornung.setSellingLevel(level1);
        //franziskaHornung.setPicturePath("https://i.pinimg.com/236x/0a/ea/c8/0aeac8c1c7a0cebb97a01a26794498ac.jpg");
        franziskaHornung.setImage(franziskaHornung.imageInBytes("src/main/resources/static/images/profilpictures/ProfilbildFranzi.jpg"));
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
                "70323",
                false,
                true,
                null
        );
        //https://cdn.pixabay.com/photo/2016/05/24/17/42/lotus-1412858_1280.jpg
        melanostachy.setImage(melanostachy.imageInBytes("src/main/resources/static/images/plantPictures/SalixMelanostachys.jpg"));
        plantService.persistPlant(melanostachy);

        Plant kaffeestrauch = new Plant(
                "Kaffeestrauch",
                50,
                10,
                "Der Kaffeestrauch ist besonders, weil er mir jeden Morgen beim Kaffee trinken Gesellschaft geleistet hat. Da ich jetzt eine Partnerin gefunden habe, bin ich bereit meine Pflanze einem anderem Single zu überlassen",
                "Hin und wieder mal gießen. Etwas Kaffe mit ins Wasser geben",
                zimmerpflanze,
                null,
                "12345",
                alessiaSedelnikov,
                true,
                false
        );
        //https://www.blumen-brueder.de/Content/files/1886/Coffea-arabica-600x800-proportionalsmallest.webp
        kaffeestrauch.setImage(kaffeestrauch.imageInBytes("src/main/resources/static/images/plantPictures/Kaffeestrauch.jpg"));
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
                "56789",
                false,
                true,
                null
        );
        allium.setLatinName(null);
        //https://gaerten.uni-hohenheim.de/fileadmin/_processed_/csm_pfl-quartier2allium_03_e929dc3231.jpg
        allium.setImage(allium.imageInBytes("src/main/resources/static/images/plantPictures/Allium.jpg"));
        allium.setImageTwo(allium.imageInBytes("src/main/resources/static/images/plantPictures/Allium2.jpg"));
        //https://cdstar.shh.mpg.de/bitstreams/EAEA0-19C1-DD89-718D-0/full.jpg
        allium.setImageThree(allium.imageInBytes("src/main/resources/static/images/plantPictures/Allium3.jpg"));

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
                "70321",
                alessiaSedelnikov,
                true,
                false
        );
        kniphofia.setLatinName(null);
        //https://gaerten.uni-hohenheim.de/fileadmin/_processed_/csm_pfl-quartier3kniphofia_03_0734dfd3c0.jpg
        kniphofia.setImage(kniphofia.imageInBytes("src/main/resources/static/images/plantPictures/Kniphofia.jpg"));
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
                "74231",
                false,
                true,
                null

        );
        prunus.setLatinName(null);
        //https://gaerten.uni-hohenheim.de/fileadmin/_processed_/csm_Prunus_subhirtella_autumnalis_811bfd6a1f.jpg
        prunus.setImage(prunus.imageInBytes("src/main/resources/static/images/plantPictures/PrunusSubhirtella.jpg"));
        //https://en.wikipedia.org/wiki/Prunus_%C3%97_subhirtella
        prunus.setImageTwo(prunus.imageInBytes("src/main/resources/static/images/plantPictures/PrunusSubhirtella2.jpg"));
        prunus.setImageThree(prunus.imageInBytes("src/main/resources/static/images/plantPictures/PrunusSubhirtella3.jpg"));
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
                "71234",
                false,
                false,
                null

        );
        occidentalis.setLatinName(null);
        //https://gaerten.uni-hohenheim.de/fileadmin/_processed_/csm_Cephalanthus_occidentalis_2c068fa11a.jpg
        occidentalis.setImage(occidentalis.imageInBytes("src/main/resources/static/images/plantPictures/Cephalanthus.jpg"));
        plantService.persistPlant(occidentalis);

        Plant hahn = new Plant(
                "Hahnenfussgewächs",
                25,
                180,
                "Sieht ein bisschen so aus wie eine Kastanie oder Corona ",
                "Sonne und hohe Luftfeuchtigkeit sind notwendig!",
                zimmerpflanze,
                alessiaSedelnikov,
                "70444",
                jenniferKaisner,
                true,
                false

        );
        //https://gaerten.uni-hohenheim.de/typo3temp/_processed_/csm_HD_DE_HOH_DP_2013_-_00301i_d44bb98a17.jpg
        hahn.setImage(hahn.imageInBytes("src/main/resources/static/images/plantPictures/Hahnenfussgewächs.jpg"));
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
                "70355",
                false,
                false,
                null
        );
        titanenwurzel.setLatinName(null);
        //https://www.swr.de/swraktuell/baden-wuerttemberg/stuttgart/1713352797444%2Ctitanenwurz-uni-hohenheim-stuttgart-100~_v-16x9@2dL_-6c42aff4e68b43c7868c3240d3ebfa29867457da.jpg
        titanenwurzel.setImage(titanenwurzel.imageInBytes("src/main/resources/static/images/plantPictures/Titanenwurzel.jpg"));
        //https://img.fotocommunity.com/titanwurz-am-23august-2014-in-der-flora-in-koeln-3c83a409-b085-4ff2-85a3-1ea9bec91c6d.jpg?height=1080
        titanenwurzel.setImageTwo(titanenwurzel.imageInBytes("src/main/resources/static/images/plantPictures/Titanenwurzel2.jpg"));
        //https://img.fotocommunity.com/titanenwurz-bluete-2013-leipzig-4-3dd14647-bc26-4d56-8320-1bfa7b28b093.jpg?height=1080
        titanenwurzel.setImageThree(titanenwurzel.imageInBytes("src/main/resources/static/images/plantPictures/Titanenwurzel3.jpg"));
        plantService.persistPlant(titanenwurzel);


        Plant heidekraut = new Plant(
                "Heidekraut Erika",
                15,
                10,
                "Du kommst aus Heidenheim und hast noch keine Heidkraut? Hol dir diese Pflanze!",
                "Hin und wieder mal gießen. Ganz viel Sonne!",
                zimmerpflanze,
                aylinaAshkenov,
                "70367",
                false,
                false,
                null
        );
        heidekraut.setLatinName(null);
        //https://cdn.pixabay.com/photo/2018/09/13/18/18/heide-3675445_1280.jpg
        heidekraut.setImage(heidekraut.imageInBytes("src/main/resources/static/images/plantPictures/Heidekraut.jpg"));
        plantService.persistPlant(heidekraut);

        Plant lotus = new Plant(
                "Lotus grüne Pflanze",
                25,
                20,
                "Jetzt wo ich auf Diät bin und keine Lotuskekse mehr essen kann, muss ich mich auch von der lotus Pflanze verabschieden, da sie mich zu sehr an die Leckerei erinnert mit ihrem Namen",
                "Jede woche mit 100 ml bewässern",
                zimmerpflanze,
                aylinaAshkenov,
                "70321",
                false,
                true,
                null
        );
        lotus.setLatinName(null);
        //https://www.lubera.com/images/600/lotus-blatt-e1481172403391.jpg
        lotus.setImage(lotus.imageInBytes("src/main/resources/static/images/plantPictures/Lotus.jpg"));
        plantService.persistPlant(lotus);

        Plant kleingruen = new Plant(
                "Grüne Pflanze",
                5,
                5,
                "Erlich kein plan was das für eine Pflanze ist",
                "Jede woche mit 500 ml bewässern",
                zimmerpflanze,
                markoPetric,
                "45678",
                false,
                false,
                null
        );
        kleingruen.setLatinName(null);
        //https://cdn.pixabay.com/photo/2019/02/25/20/29/houseplant-4020532_1280.jpg
        kleingruen.setImage(kleingruen.imageInBytes("src/main/resources/static/images/plantPictures/KleinePflanze.jpg"));
        plantService.persistPlant(kleingruen);


        Plant jessica = new Plant(
                "Jessica",
                55,
                4,
                "Ich habe eine neue lieblingspflanze gekauft und brauch die jetzt nicht mehr",
                "Wasser, Sonne und Liebe",
                outdoorpflanze,
                jenniferKaisner,
                "70321",
                false,
                true,
                null
        );
        jessica.setLatinName(null);
        //https://cdn.pixabay.com/photo/2011/04/06/15/05/garden-6239_1280.jpg
        jessica.setImage(jessica.imageInBytes("src/main/resources/static/images/plantPictures/Jessica.jpg"));
        plantService.persistPlant(jessica);

        Plant blau = new Plant(
                "Blaues Baby",
                2,
                40,
                "Hast du Blau mit Tau, wirst du zu Blau mit Frau.... Eine Dichter und Denker Pflanze",
                "Wasser, Sonne und Liebe",
                outdoorpflanze,
                aylinaAshkenov,
                "12345",
                false,
                false,
                null
        );
        blau.setLatinName(null);
        //https://cdn.pixabay.com/photo/2017/04/03/07/30/blue-flower-2197679_1280.jpg
        blau.setImage(blau.imageInBytes("src/main/resources/static/images/plantPictures/Blau.jpg"));
        plantService.persistPlant(blau);

        Plant sonnenblumensamen = new Plant(
                "Sonnenblumensamen",
                1,
                10,
                "Sonnenblumen sind sehr schöne Pflanzen. Mit diesen Samen kannst du deine Sonnenblume selbst einpflanzen wo auch immer du möchtest.",
                "Viel gießen nach dem Einsetzen in die Erde.",
                samen,
                franziskaHornung,
                "71696",
                false,
                false,
                null
        );
        //https://d2gg9evh47fn9z.cloudfront.net/1600px_COLOURBOX6162159.jpg
        sonnenblumensamen.setImage(sonnenblumensamen.imageInBytes("src/main/resources/static/images/plantPictures/Sonnenblumensamen.jpg"));
        plantService.persistPlant(sonnenblumensamen);

        Plant kürbissamen = new Plant(
                "Kürbissamen",
                1,
                9,
                "Habe Kürbissuppe gemacht und ziemlich viele Samen übrig. Möchte euch auch die Möglichkeit bieten, selbst Kürbisse anbauen zu können.",
                "Viel gießen nach dem Einsetzen in die Erde.",
                samen,
                franziskaHornung,
                "71696",
                false,
                false,
                null
        );
        //https://www.plantopedia.de/wp-content/uploads/2019/10/hokkaido-kuerbis-samen-pb.jpg
        kürbissamen.setImage(kürbissamen.imageInBytes("src/main/resources/static/images/plantPictures/Kürbissamen.jpg"));
        https://www.saatgut-dillmann.de/media/5e/34/00/1646305387/2441_B040KuerbisHokkaidoUchikiKuriBio.jpg
        kürbissamen.setImageTwo(kleingruen.imageInBytes("src/main/resources/static/images/plantPictures/Kürbissamen2.jpg"));
        plantService.persistPlant(kürbissamen);


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

        chatTest.setSenderAccept(true);
        chatTest.setRecipientAccept(true);
        chatService.updateChat(chatTest);

    // create caretips-----------------------------------------------------------------------------------------------

        //https://www.mein-schoener-garten.de/gartenpraxis/indoor/zimmerpflanzen-giessen-39259
        Caretip gießenIndoor = new Caretip();
        gießenIndoor.setCategory(zimmerpflanze);
        gießenIndoor.setTitle("Zimmerpflanze gießen");
        gießenIndoor.setCaretip("Zimmerpflanzen möchten im Idealfall so versorgt werden wie an ihrem Naturstandort. Bei starker Sonneneinstrahlung und höheren Temperaturen steigt der Wasserbedarf. In einem lufttrockenen Raum muss mehr gegossen werden als in einem luftfeuchten Raum.");
        caretipService.persistCaretip(gießenIndoor);

        //https://pflanzling.de/zimmerpflanzen-pflegen/erde-fuer-zimmerpflanzen/
        Caretip erde = new Caretip();
        erde.setCategory(zimmerpflanze);
        erde.setTitle("Passende Erde für die Zimmerpflanze");
        erde.setCaretip("Zimmerpflanzenerde ist der Nährboden deiner Pflanze. Gute Erde erkennt man dran, dass sie luftig und locker ist. Ist die Erde bereits beim Kauf klumpig, sehr grob oder sogar feucht, handelt es sich um minderwertige Erde, welche man eher meiden sollte.");
        caretipService.persistCaretip(erde);

        //https://www.gartengemeinschaft.de/blumentopf-groessen-und-materialien/
        Caretip blumentopfZimmerpflanze = new Caretip();
        blumentopfZimmerpflanze.setCategory(zimmerpflanze);
        blumentopfZimmerpflanze.setTitle("Richtige Größe vom Blumentopf");
        blumentopfZimmerpflanze.setCaretip("Das Größenverhältnis zwischen Blumentopf und Pflanze muss stimmen. Deshalb sollte der Blumentopf zweimal so tief und groß wie der Wurzelballen der Pflanze sein. ");
        caretipService.persistCaretip(blumentopfZimmerpflanze);

        //https://gartnwissn.de/garten-richtig-giessen-wann-wie-viel-wie-oft/
        Caretip gießen = new Caretip();
        gießen.setCategory(outdoorpflanze);
        gießen.setTitle("Garten richtig gießen");
        gießen.setCaretip("Den Garten wenn möglich immer frühmorgens vor Sonnenaufgang zwischen 4 und 6 Uhr gießen.");
        caretipService.persistCaretip(gießen);

        //https://www.gartennatur.com/substrat-erde-boden
        Caretip bodenbeschaffenheit = new Caretip();
        bodenbeschaffenheit.setCategory(outdoorpflanze);
        bodenbeschaffenheit.setTitle("Richtiger Boden für deine Pflanze");
        bodenbeschaffenheit.setCaretip(" Fette Böden mit einem hohen Lehmanteil können Wasser gut speichern und für die Pflanzen verfügbar machen. Sandige Böden hingegen haben eine geringere Speicherfähigkeit und werden von Pflanzen bevorzugt, die es eher trockener mögen.");
        caretipService.persistCaretip(bodenbeschaffenheit);

        //https://www.gartengemeinschaft.de/blumentopf-groessen-und-materialien/
        Caretip blumentopf = new Caretip();
        blumentopf.setCategory(outdoorpflanze);
        blumentopf.setTitle("Richtige Größe vom Blumentopf");
        blumentopf.setCaretip("Das Größenverhältnis zwischen Blumentopf und Pflanze muss stimmen. Deshalb sollte der Blumentopf zweimal so tief und groß wie der Wurzelballen der Pflanze sein. ");
        caretipService.persistCaretip(blumentopf);

        //https://www.mein-schoener-garten.de/themen/aussaat
       Caretip aussaat = new Caretip();
       aussaat.setCategory(samen);
       aussaat.setTitle("Richtiger Aussaatzeitpunkt");
       aussaat.setCaretip("Zimmerpflanzen nicht vor März aussäen. Der Grund hierfür ist, dass es zuvor meist schlichtweg tagsüber noch zu dunkel ist. Bei der Direktsaat im Beet hängt der Aussaat-Termin von der Winterhärte und dem Keimverhalten der jeweiligen Pflanzen ab. ");
       caretipService.persistCaretip(aussaat);

       Caretip guteBedingungen = new Caretip();
       guteBedingungen.setCategory(samen);
       guteBedingungen.setTitle("Gute Keimbedingungen");
       guteBedingungen.setCaretip("Damit die ausgesäten Samen gut keimen können, benötigen sie ausreichend Wärme und eine hohe Luftfeuchtigkeit. Im Zimmer leiden die Keimlinge oft unter einer zu trockenen Zimmerluft – hier ist eine Abdeckung der Samenkörner besonders wichtig.");
       caretipService.persistCaretip(guteBedingungen);
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