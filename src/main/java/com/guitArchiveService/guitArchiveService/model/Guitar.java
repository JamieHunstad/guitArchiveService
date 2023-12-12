package com.guitArchiveService.guitArchiveService.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Guitar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private long id;
    private String nickname;
    private String make;
    private String model;
    private String year;
    private String color;
    private String description;
    private String imageURL;


    public long getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }
    public String getNickname() {
        return nickname;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getDescription() {
        return description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
