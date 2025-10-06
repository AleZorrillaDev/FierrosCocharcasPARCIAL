package com.example.fierroscocharcas.ui.auth;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fierroscocharcas.R;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_register);

        findViewById(R.id.btnRegister).setOnClickListener(v -> {
            Toast.makeText(this, "Usuario registrado (simulado)", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
