package com.professionalbuddies.pb_revised;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by janiceambrose on 5/25/17.
 */

public class CustomizeProfile extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName;
    private EditText editTextProfession;
    private EditText editTextUniversity;
    private EditText editTextEducation;
    private EditText editTextAge;
    private TextView textViewPicUpload;
    private Button buttonSaveChanges;
    private Button buttonReturnToProfile;
    private ImageButton imageButtonProfilePic;

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_profile);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextProfession = (EditText) findViewById(R.id.editTextProfession);
        editTextUniversity = (EditText) findViewById(R.id.editTextUniversity);
        editTextEducation = (EditText) findViewById(R.id.editTextEducation);
        editTextAge = (EditText) findViewById(R.id.editTextAge);
        textViewPicUpload = (TextView) findViewById(R.id.textViewPicUpload);
        buttonSaveChanges = (Button) findViewById(R.id.buttonSaveChanges);
        buttonReturnToProfile = (Button) findViewById(R.id.buttonReturnToProfile);
        imageButtonProfilePic = (ImageButton) findViewById(R.id.imageButtonProfilePic);
        firebaseAuth = firebaseAuth.getInstance();

        buttonSaveChanges.setOnClickListener(this);
        buttonReturnToProfile.setOnClickListener(this);
        }

    private void saveUserInfo() {
        String name = editTextName.getText().toString().trim();
        String profession = editTextProfession.getText().toString().trim();
        String university = editTextUniversity.getText().toString().trim();
        String education = editTextEducation.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();

        UserInformation thisUser = new UserInformation(name, profession, university, education, age);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).setValue(thisUser);

        Toast.makeText(this, "Profile Update.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        if (view == buttonSaveChanges) {
            saveUserInfo();
            finish();
            startActivity(new Intent(this, ProfileActivity.class));
        }

        if (view == buttonReturnToProfile) {
            finish();
            startActivity(new Intent(this, ProfileActivity.class));
        }
    }
}
