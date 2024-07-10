package com.example.sopra_pflanzenverkauf.entity;

import jakarta.persistence.*;
import org.apache.tomcat.util.codec.binary.Base64;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * The Entity "Benutzer".
 */
@Entity
@Table(name = "Benutzer")
public class User {

    @Id
    @GeneratedValue

    //TODO: Profilbild

    @Column(name = "BenutzerID")
    private Integer userId;

    @Column(name = "Benutzername")
    private String username;

    @Column(name = "Passwort")
    private String password;

    @Column(name = "Vorname")
    private String firstName;

    @Column(name = "Nachname")
    private String lastName;

    @Column(name = "E-mail")
    private String email;

    @Column(name = "Postleitzahl")
    private String plz;

    @Column(name = "aktiviert")
    private boolean enabled = true;

    @Column(name = "Profilbild")
    @Lob
    private byte[] image;

    @Column(name = "Anzahl gekaufter Pflanzen")
    private Integer numberOfPurchasedPlants = 0;

    @Column(name = "Anzahl verkaufter Pflanzen")
    private Integer numberOfSoldPlants = 0;

    @Column(name = "Anzahl PlantCoins")
    private Integer plantCoinCount = 0;

    @ManyToOne
    @JoinColumn(name = "Käuferlevel")
    private Level buyingLevel;

    @ManyToOne
    @JoinColumn(name = "Verkäuferlevel")
    private Level sellingLevel;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "benutzer_rolle", joinColumns = @JoinColumn(name = "BenutzerID"), inverseJoinColumns = @JoinColumn(name = "RollenID"))
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Merkliste", joinColumns = @JoinColumn(name = "BenutzerID"), inverseJoinColumns = @JoinColumn(name = "PflanzenID"))
    private Set<Plant> wishlistPlants;

    @OneToMany (mappedBy = "sender")
    private java.util.List<Message> sentMessages = new ArrayList<>();

    @OneToMany (mappedBy = "recipient")
    private java.util.List<Message> receivedMessages = new ArrayList<>();

    @OneToMany (mappedBy = "senderOfChat")
    private java.util.List<Chat> sentChat = new ArrayList<>();

    @OneToMany (mappedBy = "recipientOfChat")
    private java.util.List<Chat> receivedChat = new ArrayList<>();

    /*
    @OneToMany (mappedBy = "seller")
    private java.util.List<Plant> plantsToSell = new ArrayList<>();

    @OneToMany (mappedBy = "seller")
    private java.util.List<Plant> soldPlantsList = new ArrayList<>()
    */

    @OneToMany (mappedBy = "seller")
    private java.util.List<Plant> plantsToSell = new ArrayList<>();

    @OneToMany (mappedBy = "sellerWhenSold")
    private java.util.List<Plant> soldPlantsList = new ArrayList<>();

    @OneToMany (mappedBy = "buyer")
    private java.util.List<Plant> purchasedPlants = new ArrayList<>();

    /**
     * Instantiates a new User.
     */
    public User() {
        // empty constructor for Hibernate
    }

    public User(String username,  String firstName, String lastName, String email, String plz, String picturePath, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.plz = plz;
        //this.picturePath = picturePath;
        this.password = password;
    }




    ///////////////////////////////////////////////
    // Getter & Setter
    ///////////////////////////////////////////////

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

    public Integer getPlantCoinCount() {
        return plantCoinCount;
    }

    public void setPlantCoinCount(Integer plantCoinCount) {
        this.plantCoinCount = plantCoinCount;
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




    //TODO Wird wahrscheinlich nicht benötigt (Jetzt gerade aber schon wegen Userservice)

    /**
     * Gets roles.
     *
     * @return the roles
     * */
    public Set<Role> getRoles() {
        return roles;
    }

    //TODO Wird wahrscheinlich nicht benötigt (Jetzt gerade aber schon wegen Userservice)

    /**
     * Sets roles.
     *
     * @param roles the roles
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    public java.util.List<Plant> getPlantsToSell() {
        return plantsToSell;
    }

    public void setPlantsToSell(java.util.List<Plant> plantsToSell) {
        this.plantsToSell = plantsToSell;
    }

    public java.util.List<Plant> getPurchasedPlants() {
        return purchasedPlants;
    }

    public void setPurchasedPlants(java.util.List<Plant> boughtPlants) {
        this.purchasedPlants = boughtPlants;
    }

    /**
     * Gets buying Level.
     *
     * @return the buying Level
     */
    public Level getBuyingLevel() {
        return buyingLevel;
    }

    /**
     * Sets buying Level.
     *
     * @param buyingLevel the buying Level
     */
    public void setBuyingLevel(Level buyingLevel) {
        this.buyingLevel = buyingLevel;
    }

    /**
     * Gets sellingLevel.
     *
     * @return the selling Level
     */
    public Level getSellingLevel() {
        return sellingLevel;
    }

    /**
     * Sets sellingLevel.
     *
     * @param sellingLevel the selling Level
     */
    public void setSellingLevel(Level sellingLevel) {
        this.sellingLevel = sellingLevel;
    }

    public Set<Plant> getWishlistPlants() {
        return wishlistPlants;
    }

    public void setWishlistPlants(Set<Plant> wishlistPlants) {
        this.wishlistPlants = wishlistPlants;
    }


    public Integer getNumberOfPurchasedPlants() {
        return numberOfPurchasedPlants;
    }

    public void setNumberOfPurchasedPlants(Integer numberOfPurchasedPlants) {
        this.numberOfPurchasedPlants = numberOfPurchasedPlants;
    }

    public Integer getNumberOfSoldPlants() {
        return numberOfSoldPlants;
    }

    public void setNumberOfSoldPlants(Integer numberOfSoldPlants) {
        this.numberOfSoldPlants = numberOfSoldPlants;
    }

    public java.util.List<Plant> getSoldPlantsList() {
        return soldPlantsList;
    }

    public void setSoldPlantsList(java.util.List<Plant> soldPlantsList) {
        this.soldPlantsList = soldPlantsList;
    }

    public List<Message> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(List<Message> sentMessages) {
        this.sentMessages = sentMessages;
    }

    public List<Message> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(List<Message> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

    public List<Chat> getSentChat() {
        return sentChat;
    }

    public void setSentChat(List<Chat> sentChat) {
        this.sentChat = sentChat;
    }

    public List<Chat> getReceivedChat() {
        return receivedChat;
    }

    public void setReceivedChat(List<Chat> receivedChat) {
        this.receivedChat = receivedChat;
    }

    public List<Chat> getSentAndReceivedChats() {
        List<Chat> chatList = this.getReceivedChat();
        chatList.addAll(this.getSentChat());
        return chatList;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageDataBase64() {
        String image = Base64.encodeBase64String(this.image);
        System.out.println(image);
        return image;
    }

    public byte[] imageInBytes (String pathname) throws IOException {
        if (pathname == null) {
            throw new NullPointerException();
        }
        File file=new File(pathname);
        BufferedImage originalImage= ImageIO.read(file);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        ImageIO.write(originalImage, "jpg", baos );
        return baos.toByteArray();
    }

}
