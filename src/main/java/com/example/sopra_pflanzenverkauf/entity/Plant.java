package com.example.sopra_pflanzenverkauf.entity;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
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
 * The Entity "Pflanze".
 */
@Entity
@Table(name = "Pflanze")
public class Plant {

    @Id
    @GeneratedValue
    @Column(name = "PflanzenID")
    private Integer plantId;

    @Column(name = "Name")
    private String plantname;

    @Column(name="Lateinischer Name")
    private String latinName;

    @Column(name = "Größe in cm")
    private Integer plantSize;

    @Column(name = "Preis")
    private double price;

    @Column(name = "Beschreibung der Pflanze")
    @Lob
    private String plantDescription;

    @Column(name = "Bild1")
    @Lob
    private byte[] image;

    @Column(name = "Bild2")
    @Lob
    private byte[] imageTwo;

    @Column(name = "Bild3")
    @Lob
    private byte[] imageThree;

    @Column(name = "Pflegetipps")
    @Lob
    private String careTips;

    @Column(name = "Verkauft")
    private boolean sold = false;

    @Column(name = "Postleitzahl")
    private String zipCode;

    @Column(name = "Booster")
    private boolean booster = false;

    @Column(name = "Übertopf")
    private boolean planter = false;

    @ManyToOne
    @JoinColumn(name = "Kategorie")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "Verkäufer")
    private User seller;

    @ManyToOne
    @JoinColumn(name = "Verkäufer (Pflanze verkauft")
    private User sellerWhenSold = null;

    @ManyToOne
    @JoinColumn(name = "Käufer")
    private User buyer;

    @OneToMany (mappedBy = "chatPlant")
    private java.util.List<Chat> chatsOfPlant = new ArrayList<>();

    
    /**
     * Instantiates a new Plant.
     */
    public Plant() {
        // empty constructor for Hibernate
    }
    /**
     * Konstruktor für Test Daten für Pflanze die verkauft ist
     *
     * @param plantname Name
     * @param plantSize Größe in cm
     * @param price Preis
     * @param plantDescription Beschreibung
     * @param careTips Pflegetipps
     * @param category Kategorie (outdoor oder indoor)
     * @param seller Vekäufer
     * @param zipCode PLZ
     * @param buyer Käufer
     * @param sold Verkauft oder nicht boolean
     */
    public Plant(
            String plantname,
            Integer plantSize,
            double price,
            String plantDescription,
            String careTips,
            Category category,
            User seller,
            String zipCode,
            User buyer,
            Boolean sold,
            Boolean planter
    ) {
        this.plantname = plantname;
        this.plantSize = plantSize;
        this.price = price;
        this.plantDescription = plantDescription;
        this.careTips = careTips;
        this.category = category;
        this.seller = seller;
        this.zipCode = zipCode;
        this.buyer = buyer;
        this.sold = sold;
        this.planter = planter;
    }

    /**
     * Konstruktor für Test Daten für Pflanze die noch nicht verkauft ist
     *
     * @param plantname
     * @param plantSize
     * @param price
     * @param plantDescription
     * @param careTips
     * @param category
     * @param seller
     * @param zipCode
     * @param sold
     */
    public Plant(
            String plantname,
            Integer plantSize,
            double price,
            String plantDescription,
            String careTips,
            Category category,
            User seller,
            String zipCode,
            Boolean sold,
            Boolean planter,
            byte[] image
    ) {
        this.plantname = plantname;
        this.plantSize = plantSize;
        this.price = price;
        this.plantDescription = plantDescription;
        this.careTips = careTips;
        this.category = category;
        this.seller = seller;
        this.zipCode = zipCode;
        this.sold = sold;
        this.planter = planter;
        this.image = image;
    }



    public void setBooster(Boolean booster){
        this.booster = booster;
    }

    public Boolean getBooster(){
        return booster;
    }

    public void setImage(byte [] image) {
        this.image = image;
    }
    public byte[] getImage(){
        return image;
    }

    public Integer getPlantId() {
        return plantId;
    }
    public void setPlantId(Integer plantId) {
        this.plantId = plantId;
    }

    public String getPlantname() {
        return plantname;
    }
    public void setPlantname(String name) {
        this.plantname = name;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public Integer getPlantSize() {
        return plantSize;
    }
    public void setPlantSize(Integer plantSize) {
        this.plantSize = plantSize;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String getPlantDescription() {
        return plantDescription;
    }
    public void setPlantDescription(String plantDescription) {
        this.plantDescription = plantDescription;
    }

    public String getCareTips() {
        return careTips;
    }
    public void setCareTips(String careTips) {
        this.careTips = careTips;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Boolean getSold(){
        return sold;
    }
    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getSellerWhenSold() {
        return sellerWhenSold;
    }

    public void setSellerWhenSold(User sellerWhenSold) {
        this.sellerWhenSold = sellerWhenSold;
    }

    public List<Chat> getChatsOfPlant() {
        return chatsOfPlant;
    }

    public void setChatsOfPlant(List<Chat> chatsOfPlant) {
        this.chatsOfPlant = chatsOfPlant;
    }

    public boolean getPlanter() {
        return planter;
    }

    public void setPlanter(boolean planter) {
        this.planter = planter;
    }

    public String getImageDataBase64() {
        String image = Base64.encodeBase64String(this.image);
        System.out.println(image);
        return image;
    }

    public byte[] getImageTwo() {
        return imageTwo;
    }

    public void setImageTwo(byte[] imageTwo) {
        this.imageTwo = imageTwo;
    }

    public String getImageTwoDataBase64() {
        String image = Base64.encodeBase64String(this.imageTwo);
        System.out.println(image);
        return image;
    }

    public byte[] getImageThree() {
        return imageThree;
    }

    public void setImageThree(byte[] imageThree) {
        this.imageThree = imageThree;
    }

    public String getImageThreeDataBase64() {
        String image = Base64.encodeBase64String(this.imageThree);
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
