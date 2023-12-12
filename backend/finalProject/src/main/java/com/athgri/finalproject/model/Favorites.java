package com.athgri.finalproject.model;

import java.util.List;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * The Favorites class represents favorites information including
 * an identifier (ID), list of item IDs.
 */
@Entity
public class Favorites implements Serializable {
    @Id
    @Column(nullable = false, updatable = false)
    private Long id;

    private String itemList;

    public Favorites() {
    }

    public Favorites(Long id) {
        this.id = id;
        this.itemList = "";
    }

    public Favorites(Long id, String itemList) {
        this.id = id;
        this.itemList = itemList;
    }

    public Long getId() {
        return id;
    }

    public String getItemList() {
        return itemList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setItemList(String itemList) {
        this.itemList = itemList;
    }
}