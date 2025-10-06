package com.example.fierroscocharcas.ui.cart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fierroscocharcas.R;
import com.example.fierroscocharcas.data.local.entities.ProductEntity;
import com.example.fierroscocharcas.ui.auth.LoginActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CartActivity extends AppCompatActivity {

    private CartAdapter adapter;
    private TextView txtTotal;
    private RecyclerView rv;
    private static final int REQUEST_LOGIN = 100;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_cart);

        txtTotal = findViewById(R.id.txtTotal);
        rv = findViewById(R.id.rvCart);
        rv.setLayoutManager(new LinearLayoutManager(this));

        loadCart();

        findViewById(R.id.btnCheckout).setOnClickListener(v -> {
            if (CartManager.get().isEmpty()) {
                Toast.makeText(this, "Tu carrito está vacío", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(CartActivity.this, LoginActivity.class);
                startActivityForResult(intent, REQUEST_LOGIN);
            }
        });
    }

    private void loadCart() {
        Map<Integer, Integer> quantities = CartManager.get().getCartMap();
        List<ProductEntity> uniqueProducts = new ArrayList<>(CartManager.get().getUniqueProducts());

        adapter = new CartAdapter(uniqueProducts, quantities, p -> {
            CartManager.get().remove(p.getId());            // antes: p.id
            refresh();
            Toast.makeText(this, "Eliminado: " + p.getName(), Toast.LENGTH_SHORT).show(); // antes: p.name
        });


        rv.setAdapter(adapter);
        updateTotal();
    }

    private void refresh() { loadCart(); }

    private void updateTotal() {
        double total = CartManager.get().getTotal();
        txtTotal.setText("Total: S/ " + String.format("%.2f", total));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LOGIN && resultCode == RESULT_OK) {
            Toast.makeText(this, "Compra realizada con éxito", Toast.LENGTH_LONG).show();
            CartManager.get().clear();
            refresh();
        }
    }
}
