package com.slutprojeeram.slutprojee.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Art implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String category;
    private String price;
    private String description;
    private int quantity;

    @Transient
    private MultipartFile artImage;

    @OneToMany(mappedBy = "art")
    @JsonIgnore
    private List<ArtToCartItem> artToCartItemList;

    public List<ArtToCartItem> getArtToCartItemList() {
        return artToCartItemList;
    }

    public void setArtToCartItemList(List<ArtToCartItem> artToCartItemList) {
        this.artToCartItemList = artToCartItemList;
    }

    public MultipartFile getArtImage() {
        return artImage;
    }

    public void setArtImage(MultipartFile artImage) {
        this.artImage = artImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
