package com.myapps.majorprojectversion1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Register extends AppCompatActivity {


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private TextInputEditText email, username, password, phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.etEmail);
        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        phone = findViewById(R.id.etPhone);
        Button register = findViewById(R.id.btnRegister);
        Button backToLogin = findViewById(R.id.btnBackToLogin);

        backToLogin.setOnClickListener(view -> startActivity(new Intent(Register.this, Login.class)));

        register.setOnClickListener(view -> {

            firebaseDatabase = FirebaseDatabase.getInstance("https://majorprojectversion1-default-rtdb.asia-southeast1.firebasedatabase.app/");
            databaseReference = firebaseDatabase.getReference("users");

            String regEmail = Objects.requireNonNull(email.getText()).toString();
            String regUsername = Objects.requireNonNull(username.getText()).toString();
            String regPassword = Objects.requireNonNull(password.getText()).toString();
            String regPhone = Objects.requireNonNull(phone.getText()).toString();

            UserHelperClass userHelperClass = new UserHelperClass(regEmail, regUsername, regPassword, regPhone);
            databaseReference.child(regUsername).setValue(userHelperClass);
            Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show();
        });


    }
}