package com.example.fierroscocharcas.data.local.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products")
public class ProductEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String name;
    private String description;
    private double price;
    private int stock;
    private String imageResName; // opcional

    public ProductEntity(@NonNull String name, String description, double price, int stock, String imageResName) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.imageResName = imageResName;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @NonNull public String getName() { return name; }
    public void setName(@NonNull String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getImageResName() { return imageResName; }
    public void setImageResName(String imageResName) { this.imageResName = imageResName; }
}
