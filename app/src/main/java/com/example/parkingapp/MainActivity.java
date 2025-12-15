package com.example.parkingapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etFullName, etEmail, etPassword, etPhone;
    Button btnRegister;
    RadioGroup roleRadioGroup;
    TextView tvGoToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register); // This links to your XML

        // 1. Initialize Views (Connect Java to XML)
        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etPhone = findViewById(R.id.etPhoneNumber);
        btnRegister = findViewById(R.id.btnRegister);
        roleRadioGroup = findViewById(R.id.roleRadioGroup);
        tvGoToLogin = findViewById(R.id.tvGoToLogin);

        // 2. Handle Register Button Click
        btnRegister.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                etEmail.setError("Email is required.");
                return;
            }
            if (password.length() < 6) {
                etPassword.setError("Password must be >= 6 characters.");
                return;
            }

            // For now, just show a success message
            Toast.makeText(MainActivity.this, "Register button clicked!", Toast.LENGTH_SHORT).show();

            // TODO: We will add Firebase logic here in the next step
        });

        // 3. Handle "Go to Login" Click
        tvGoToLogin.setOnClickListener(v -> {
            // We will create the LoginActivity next, so just a toast for now
            Toast.makeText(MainActivity.this, "Navigating to Login...", Toast.LENGTH_SHORT).show();
        });
    }
}