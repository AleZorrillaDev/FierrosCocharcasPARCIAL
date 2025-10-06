package com.example.fierroscocharcas.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fierroscocharcas.R;
import com.example.fierroscocharcas.ui.payment.PaymentActivity;
import com.example.fierroscocharcas.utils.SessionManager;

public class LoginActivity extends AppCompatActivity {

    private static final int REQUEST_PAYMENT = 200;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_login);

        session = new SessionManager(this);

        EditText edtEmail = findViewById(R.id.edtEmail);
        EditText edtPassword = findViewById(R.id.edtPassword);

        findViewById(R.id.btnLogin).setOnClickListener(v -> {
            String email = edtEmail.getText().toString();
            String password = edtPassword.getText().toString();
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }
            session.saveUser("Cliente Fierros", email);
            Toast.makeText(this, "Inicio de sesión correcto", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, PaymentActivity.class);
            startActivityForResult(intent, REQUEST_PAYMENT);
        });

        findViewById(R.id.txtRegister).setOnClickListener(v -> {
            Toast.makeText(this, "Ir a registro", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PAYMENT && resultCode == RESULT_OK) {
            setResult(RESULT_OK);
            finish();
        }
    }
}
