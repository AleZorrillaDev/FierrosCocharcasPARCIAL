package com.example.fierroscocharcas.ui.catalog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fierroscocharcas.R;
import com.example.fierroscocharcas.data.local.entities.ProductEntity;
import com.google.android.material.card.MaterialCardView;

import java.util.List;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.VH> {

    private List<ProductEntity> data;

    public ProductAdapter(List<ProductEntity> data) {
        this.data = data;
    }

    public void submit(List<ProductEntity> items) {
        this.data = items;
        notifyDataSetChanged();
    }

    static class VH extends RecyclerView.ViewHolder {
        MaterialCardView card;
        TextView name, price, stock;
        VH(View v) {
            super(v);
            card = (MaterialCardView) v;
            name = v.findViewById(R.id.txtName);
            price = v.findViewById(R.id.txtPrice);
            stock = v.findViewById(R.id.txtStock);
        }
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int i) {
        ProductEntity p = data.get(i);
        h.name.setText(p.getName());
        h.price.setText(String.format(Locale.getDefault(), "S/ %.2f", p.getPrice()));
        h.stock.setText("Stock: " + p.getStock());
    }

    @Override
    public int getItemCount() { return data == null ? 0 : data.size(); }
}
