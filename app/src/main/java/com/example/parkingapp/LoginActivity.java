package com.example.parkingapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText etFullName, etPassword; // Changed from etEmail to etFullName
    Button btnLogin;
    TextView tvGoToRegister;
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Link to the new ID in XML
        etFullName = findViewById(R.id.etFullName);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvGoToRegister = findViewById(R.id.tvGoToRegister);

        DB = new DatabaseHelper(this);

        btnLogin.setOnClickListener(v -> {
            String name = etFullName.getText().toString(); // Get Name
            String pass = etPassword.getText().toString();

            if(name.equals("")||pass.equals(""))
                Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            else{
                // Check login using Name and Password
                String role = DB.checkLogin(name, pass);

                if(role != null){
                    Toast.makeText(LoginActivity.this, "Login Successful! Role: " + role, Toast.LENGTH_SHORT).show();
                    // Dashboard redirection code goes here
                }else{
                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvGoToRegister.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
    }
}