package com.example.sopra_pflanzenverkauf.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Pflegetipp")
public class Caretip {
    @Id
    @GeneratedValue
    @Column(name = "PflegetippID")
    private Integer caretipId;

    @Column(name = "Titel")
    private String title;

    @Column(name = "Pflegetipp")
    @Lob
    private String caretip;

    @ManyToOne
    @JoinColumn(name = "Kategorie")
    private Category category;

    public Caretip (){}

    public Integer getCaretipId() {
        return caretipId;
    }

    public void setCaretipId(Integer caretipId) {
        this.caretipId = caretipId;
    }

    public String getCaretip() {
        return caretip;
    }

    public void setCaretip(String caretip) {
        this.caretip = caretip;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
