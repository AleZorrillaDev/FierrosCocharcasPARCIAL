package com.example.fierroscocharcas.data.repo;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.fierroscocharcas.data.local.AppDatabase;
import com.example.fierroscocharcas.data.local.ProductDao;
import com.example.fierroscocharcas.data.local.entities.ProductEntity;

import java.util.List;

public class ProductRepository {

    private final ProductDao dao;
    private final LiveData<List<ProductEntity>> all;

    public ProductRepository(Context ctx) {
        AppDatabase db = AppDatabase.getInstance(ctx);
        dao = db.productDao();
        all = dao.getAll();
    }

    public LiveData<List<ProductEntity>> getAll() { return all; }

    public void insert(ProductEntity p) { AppDatabase.executor().execute(() -> dao.insert(p)); }

    public void update(ProductEntity p) { AppDatabase.executor().execute(() -> dao.update(p)); }

    public void delete(ProductEntity p) { AppDatabase.executor().execute(() -> dao.delete(p)); }

    public void clear() { AppDatabase.executor().execute(dao::clear); }
}
