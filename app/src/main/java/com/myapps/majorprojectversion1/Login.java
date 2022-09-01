package com.myapps.majorprojectversion1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Login extends AppCompatActivity {

    private TextInputEditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = findViewById(R.id.btnLogin);
        Button register = findViewById(R.id.btnBackToRegister);

        username = findViewById(R.id.etLoginUsername);
        password = findViewById(R.id.etLoginPassword);

        register.setOnClickListener(view -> startActivity(new Intent(Login.this, Register.class)));

        login.setOnClickListener(view -> {
            String loginUsername = Objects.requireNonNull(username.getText()).toString().trim();
            String loginPassword = Objects.requireNonNull(password.getText()).toString().trim();

            DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://majorprojectversion1-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("users");

            Query checkUser = databaseReference.orderByChild("username").equalTo(loginUsername);
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        String passwordFromDb = snapshot.child(loginUsername).child("password").getValue(String.class);

                        if (Objects.requireNonNull(passwordFromDb).equals(loginPassword)) {
                            String nameFromDb = snapshot.child(loginUsername).child("name").getValue(String.class);
                            String usernameFromDb = snapshot.child(loginUsername).child("username").getValue(String.class);
                            String phoneFromDb = snapshot.child(loginUsername).child("phone").getValue(String.class);
                            String emailFromDb = snapshot.child(loginUsername).child("email").getValue(String.class);

                            Intent intent = new Intent(getApplicationContext(), Home.class);
                            intent.putExtra("name" , nameFromDb);
                            intent.putExtra("username", usernameFromDb);
                            intent.putExtra("email", emailFromDb);
                            intent.putExtra("phone", phoneFromDb);

                            startActivity(intent);

                        }else{
                            Toast.makeText(Login.this, "error in password", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(Login.this, "No user exists", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        });
    }
}