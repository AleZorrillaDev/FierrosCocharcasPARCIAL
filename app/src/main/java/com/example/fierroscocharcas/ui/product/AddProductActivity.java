package com.example.fierroscocharcas.ui.product;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fierroscocharcas.R;
import com.example.fierroscocharcas.data.local.entities.ProductEntity;
import com.example.fierroscocharcas.data.repo.ProductRepository; // ← IMPORTA ESTO

public class AddProductActivity extends AppCompatActivity {

    private ProductRepository repo;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_add_product);

        repo = new ProductRepository(this);

        EditText etName = findViewById(R.id.etName);
        EditText etDesc = findViewById(R.id.etDesc);
        EditText etPrice = findViewById(R.id.etPrice);
        EditText etStock = findViewById(R.id.etStock);

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String desc = etDesc.getText().toString().trim();
            String priceStr = etPrice.getText().toString().trim();
            String stockStr = etStock.getText().toString().trim();

            if (name.isEmpty() || priceStr.isEmpty() || stockStr.isEmpty()) {
                Toast.makeText(this, "Completa nombre, precio y stock", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double price = Double.parseDouble(priceStr);
                int stock = Integer.parseInt(stockStr);

                ProductEntity p = new ProductEntity(name, desc, price, stock, "ic_home");
                repo.insert(p);
                Toast.makeText(this, "Guardado", Toast.LENGTH_SHORT).show();
                finish();
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Revisa los números de precio/stock", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
