package com.example.fierroscocharcas.ui.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fierroscocharcas.R;
import com.example.fierroscocharcas.data.local.entities.ProductEntity;
import com.google.android.material.button.MaterialButton;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.VH> {

    public interface Listener { void onRemove(ProductEntity p); }

    private final List<ProductEntity> products;
    private final Map<Integer, Integer> quantities;
    private final Listener listener;

    public CartAdapter(List<ProductEntity> products, Map<Integer, Integer> quantities, Listener listener) {
        this.products = products;
        this.quantities = quantities;
        this.listener = listener;
    }

    static class VH extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, price, qty;
        MaterialButton remove;

        VH(View v) {
            super(v);
            image = v.findViewById(R.id.cartItemImage);
            name = v.findViewById(R.id.cartItemName);
            price = v.findViewById(R.id.cartItemPrice);
            qty = v.findViewById(R.id.cartItemQty);
            remove = v.findViewById(R.id.btnRemove);
        }
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int i) {
        ProductEntity p = products.get(i);
        int quantity = quantities.getOrDefault(p.getId(), 0);

        h.name.setText(p.getName());
        h.price.setText(String.format(Locale.getDefault(), "S/ %.2f", p.getPrice()));
        h.qty.setText("Cantidad: " + quantity);

        // Imagen placeholder. Si usas imageResName, aquí podrías resolver el drawable.
        h.image.setImageResource(android.R.drawable.ic_menu_gallery);

        h.remove.setOnClickListener(v -> listener.onRemove(p));
    }

    @Override
    public int getItemCount() { return products.size(); }
}
