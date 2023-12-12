package com.athgri.finalproject.model;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * The Car class represents information about a car including
 * an identifier (ID), brand, model, year, mileage, color, engine CC,
 * engine horsepower (BHP), description, and image URI.
 */
@Entity
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private Long year;
    private Long mileage;
    private String color;
    private Long engineCC;
    private Long engineHP;
    private String description;
    private String imageURI;

    public Car() {}

    public Car(String brand, String model, Long year, Long mileage, String color, Long engineCC, Long engineHP, String description, String imageURI) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.color = color;
        this.engineCC = engineCC;
        this.engineHP = engineHP;
        this.description = description;
        this.imageURI = imageURI;
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Long getYear() {
        return year;
    }

    public Long getMileage() {
        return mileage;
    }

    public String getColor() {
        return color;
    }

    public Long getEngineCC() {
        return engineCC;
    }

    public Long getEngineHP() {
        return engineHP;
    }

    public String getDescription() {
        return description;
    }

    public String getImageURI() {
        return imageURI;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setEngineCC(Long engineCC) {
        this.engineCC = engineCC;
    }

    public void setEngineHP(Long engineHP) {
        this.engineHP = engineHP;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }

    public String toString() {
        String carInfo = "";

        carInfo += "ID: " + getId() + "\n";
        carInfo += "Brand: " + getBrand() + "\n";
        carInfo += "Model: " + getModel() + "\n";
        carInfo += "Year: " + getYear() + "\n";
        carInfo += "Mileage: " + getMileage() + "\n";
        carInfo += "Color: " + getColor() + "\n";
        carInfo += "Engine CC: " + getEngineCC() + "\n";
        carInfo += "Engine BHP: " + getEngineHP() + "\n";
        carInfo += "Description: " + getDescription() + "\n";
        carInfo += "Image URI: " + getImageURI() + "\n";

        return carInfo;
    }
}