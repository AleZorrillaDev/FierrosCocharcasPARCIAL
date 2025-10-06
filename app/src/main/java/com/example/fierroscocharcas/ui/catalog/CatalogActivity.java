package com.example.fierroscocharcas.ui.catalog;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fierroscocharcas.R;
import com.example.fierroscocharcas.data.repo.ProductRepository;   // ← IMPORT CORRECTO
import com.example.fierroscocharcas.ui.product.AddProductActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CatalogActivity extends AppCompatActivity {

    private ProductRepository repo;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_catalog);

        repo = new ProductRepository(this);

        RecyclerView rv = findViewById(R.id.rvProducts);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductAdapter(new ArrayList<>());
        rv.setAdapter(adapter);

        // Observa Room en vivo
        repo.getAll().observe(this, list -> adapter.submit(list));

        FloatingActionButton fab = findViewById(R.id.fabAdd);
        fab.setOnClickListener(v ->
                startActivity(new Intent(this, AddProductActivity.class)));
    }
}
