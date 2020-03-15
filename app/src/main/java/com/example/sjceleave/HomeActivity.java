package com.example.sjceleave;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {
    Button btnlogout;
    //FirebaseAuth mFirebaseAuth;
    FloatingActionButton floatbtn;
    //private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent j = getIntent();
        final String email = j.getStringExtra("email");

        btnlogout = findViewById(R.id.button);
        floatbtn = findViewById(R.id.fabtn);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intoMain = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intoMain);
            }
        });

        floatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intoForm = new Intent (HomeActivity.this, FormActivity.class);
                intoForm.putExtra("email",email);
                startActivity(intoForm);
            }
        });
    }
}