package com.example.fierroscocharcas.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fierroscocharcas.data.local.entities.ProductEntity;

import java.util.List;

@Dao
public interface ProductDao {

    @Query("SELECT * FROM products ORDER BY name ASC")
    LiveData<List<ProductEntity>> getAll();

    @Insert
    void insert(ProductEntity... products);

    @Update
    void update(ProductEntity product);

    @Delete
    void delete(ProductEntity product);

    @Query("DELETE FROM products")
    void clear();
}
