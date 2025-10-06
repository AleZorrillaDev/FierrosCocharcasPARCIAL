package com.example.fierroscocharcas.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.fierroscocharcas.R;
import com.example.fierroscocharcas.ui.cart.CartActivity;
import com.google.android.material.button.MaterialButton;

public class CartFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cart, container, false);

        MaterialButton btnOpen = v.findViewById(R.id.btnOpenCart);
        btnOpen.setOnClickListener(view ->
                startActivity(new Intent(requireContext(), CartActivity.class)));

        return v;
    }
}
