package com.professionalbuddies.pb_revised;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    //private TextView textViewUserEmail;
    private Button buttonLogout;
    private Button buttonCustomizeProfile;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        UserInformation thisUser = new UserInformation();

        databaseReference = FirebaseDatabase.getInstance().getReference();



        //textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        //textViewUserEmail.setText("Welcome " +user.getEmail());
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonCustomizeProfile = (Button) findViewById(R.id.buttonCustomizeProfile);

        buttonLogout.setOnClickListener(this);
        buttonCustomizeProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonLogout) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        if (view == buttonCustomizeProfile) {
            finish();
            startActivity(new Intent(this, CustomizeProfile.class));
        }
    }
}
