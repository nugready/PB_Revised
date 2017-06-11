package com.professionalbuddies.pb_revised;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase myDatabase;
    private DatabaseReference myRef;

    private static final String TAG = "ProfileActivity";
    private String userId = "";
    private TextView name;
    private TextView profession;
    private TextView age;
    private Button buttonLogout;
    private Button buttonCustomizeProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        myDatabase = FirebaseDatabase.getInstance();
        myRef = myDatabase.getReference();
        FirebaseUser thisUser = firebaseAuth.getCurrentUser();
        userId = thisUser.getUid();
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonCustomizeProfile = (Button) findViewById(R.id.buttonCustomizeProfile);
        name = (TextView) findViewById(R.id.name);
        profession = (TextView) findViewById(R.id.profession);
        age = (TextView) findViewById(R.id.age);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //showData(dataSnapshot);
                    UserInformation theUser = new UserInformation();
                    theUser.setUserName(dataSnapshot.child(userId).getValue(UserInformation.class).getUserName());
                    theUser.setProfession(dataSnapshot.child(userId).getValue(UserInformation.class).getProfession());
                    theUser.setEducation(dataSnapshot.child(userId).getValue(UserInformation.class).getEducation());
                    theUser.setUniversity(dataSnapshot.child(userId).getValue(UserInformation.class).getUniversity());
                    theUser.setAge(dataSnapshot.child(userId).getValue(UserInformation.class).getAge());

                    name.setText(theUser.getUserName());
                    profession.setText(theUser.getProfession());
                    age.setText(theUser.getAge());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        //textViewUserEmail.setText("Welcome " +user.getEmail());

        buttonLogout.setOnClickListener(this);
        buttonCustomizeProfile.setOnClickListener(this);
    }

    private void showData(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            UserInformation theUser = new UserInformation();
            theUser.setUserName(ds.child(userId).getValue(UserInformation.class).getUserName());
            theUser.setProfession(ds.child(userId).getValue(UserInformation.class).getProfession());
            theUser.setEducation(ds.child(userId).getValue(UserInformation.class).getEducation());
            theUser.setUniversity(ds.child(userId).getValue(UserInformation.class).getUniversity());
            theUser.setAge(ds.child(userId).getValue(UserInformation.class).getAge());

            name.setText(theUser.getUserName());
            profession.setText(theUser.getProfession());
            age.setText(theUser.getAge());
        }
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
