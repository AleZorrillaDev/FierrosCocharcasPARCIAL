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
import com.example.fierroscocharcas.ui.catalog.CatalogActivity;
import com.google.android.material.button.MaterialButton;

public class CatalogFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_catalog, container, false);

        MaterialButton btnOpen = v.findViewById(R.id.btnOpenCatalog);
        btnOpen.setOnClickListener(view ->
                startActivity(new Intent(requireContext(), CatalogActivity.class)));

        return v;
    }
}
