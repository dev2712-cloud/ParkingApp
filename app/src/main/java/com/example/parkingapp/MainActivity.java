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

    // UPDATED: Using DatabaseHelper
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etPhone = findViewById(R.id.etPhoneNumber);
        btnRegister = findViewById(R.id.btnRegister);
        roleRadioGroup = findViewById(R.id.roleRadioGroup);
        tvGoToLogin = findViewById(R.id.tvGoToLogin);

        // UPDATED: Initialize DatabaseHelper
        DB = new DatabaseHelper(this);

        btnRegister.setOnClickListener(v -> {
            String user = etEmail.getText().toString();
            String pass = etPassword.getText().toString();
            String name = etFullName.getText().toString();
            String phone = etPhone.getText().toString();

            if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(name)) {
                Toast.makeText(MainActivity.this, "All fields required", Toast.LENGTH_SHORT).show();
                return;
            }

            String role = "Driver";
            int selectedId = roleRadioGroup.getCheckedRadioButtonId();
            if (selectedId == R.id.rbOwner) role = "Owner";
            else if (selectedId == R.id.rbAdmin) role = "Admin";

            Boolean checkUser = DB.checkEmail(user);
            if(!checkUser){
                Boolean insert = DB.insertData(user, pass, name, phone, role);
                if(insert){
                    Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(MainActivity.this, "User already exists! Please Sign In", Toast.LENGTH_SHORT).show();
            }
        });

        tvGoToLogin.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        });
    }
}