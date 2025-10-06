package com.example.fierroscocharcas.ui.cart;

import com.example.fierroscocharcas.data.local.entities.ProductEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartManager {

    private static CartManager instance;

    // id → producto
    private final Map<Integer, ProductEntity> allProducts = new HashMap<>();
    // id → cantidad
    private final Map<Integer, Integer> cart = new HashMap<>();

    private CartManager() {}

    public static CartManager get() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    // Registra/actualiza un producto conocido por su id
    public void indexProduct(ProductEntity p) {
        if (p != null) {
            allProducts.put(p.getId(), p);
        }
    }

    public void add(int productId) {
        int qty = cart.getOrDefault(productId, 0);
        cart.put(productId, qty + 1);
    }

    public void remove(int productId) {
        if (cart.containsKey(productId)) {
            int qty = cart.get(productId);
            if (qty <= 1) {
                cart.remove(productId);
            } else {
                cart.put(productId, qty - 1);
            }
        }
    }

    public boolean isEmpty() { return cart.isEmpty(); }

    public Map<Integer, Integer> getCartMap() { return new HashMap<>(cart); }

    public List<ProductEntity> getUniqueProducts() {
        List<ProductEntity> items = new ArrayList<>();
        for (Integer id : cart.keySet()) {
            ProductEntity p = allProducts.get(id);
            if (p != null) items.add(p);
        }
        return items;
    }

    public double getTotal() {
        double total = 0;
        for (Map.Entry<Integer, Integer> e : cart.entrySet()) {
            ProductEntity p = allProducts.get(e.getKey());
            if (p != null) total += p.getPrice() * e.getValue();
        }
        return total;
    }

    public void clear() { cart.clear(); }
}
