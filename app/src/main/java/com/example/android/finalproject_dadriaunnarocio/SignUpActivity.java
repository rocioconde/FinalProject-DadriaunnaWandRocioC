package com.example.android.finalproject_dadriaunnarocio;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {


    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference userRef = database.getReference("user");
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseAuth.AuthStateListener authListener;

    private EditText studentName;
    private EditText studentEmail;
    private EditText studentPassword;
    private EditText studentGrade;
    private EditText studentAge;
    private EditText favFood;
    private EditText studentSchool;
    private CheckBox isVegetarian;
    private CheckBox reducedLunch50;
    private CheckBox reducedLunch100;
    private ArrayList<Student> student = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        studentName = (EditText) findViewById(R.id.username);
        studentEmail = (EditText) findViewById(R.id.student_email);
        studentPassword = (EditText) findViewById(R.id.student_password);
        studentGrade = (EditText) findViewById(R.id.user_grade);
        studentAge = (EditText) findViewById(R.id.user_age);
        studentSchool = (EditText) findViewById(R.id.school);
        isVegetarian = (CheckBox) findViewById(R.id.is_vegetarian);
        favFood = (EditText) findViewById(R.id.favorite_food);
        reducedLunch50 = (CheckBox) findViewById(R.id.reduced_lunch_50);
        reducedLunch100 = (CheckBox) findViewById(R.id.reduced_lunch_100);


        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                    userRef = database.getReference(user.getUid());
                    userRef.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            student.add(dataSnapshot.getValue(Student.class));


                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                            Toast.makeText(SignUpActivity.this, dataSnapshot.getValue(SignUpActivity.class) + " has changed", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {
                            Toast.makeText(SignUpActivity.this, dataSnapshot.getValue(SignUpActivity.class) + " was removed", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });

                }
            }


    }


    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        auth.removeAuthStateListener(authListener);
    }

    public void signUp(View view) {

        FirebaseUser user = auth.getCurrentUser();
        DatabaseReference userRef = database.getReference(user.getUid());
        userRef.push().setValue(studentName.getText().toString());
        userRef.push().setValue(studentGrade.getText().toString());
        userRef.push().setValue(studentAge.getText().toString());
        userRef.push().setValue(studentSchool.getText().toString());
        userRef.push().setValue(favFood.getText().toString());
        boolean isVegetarian1;
        isVegetarian1 = isVegetarian.isChecked();
        String isVegetarian2 = String.valueOf(isVegetarian1);
        userRef.push().setValue(isVegetarian2);
        boolean hasReducedLunch50;
        hasReducedLunch50 = reducedLunch50.isChecked();
        String doesHaveReducedLunch50 = String.valueOf(hasReducedLunch50);
        userRef.push().setValue(doesHaveReducedLunch50);

        boolean hasReducedLunch100;
        hasReducedLunch100 = reducedLunch100.isChecked();
        String doesHaveReducedLunch100 = String.valueOf(hasReducedLunch100);
        userRef.push().setValue(doesHaveReducedLunch100);
    }

//    private void displayUserInfo() {
//        String text = "";
//        for (Student s : student)
//            text += s + "\n";
//        displayText.setText(text);
//
//    }

//    public void signOut(View view) {
//        auth.signOut();
//        student.clear();
//        displayText.setText("");
//
//
//    }

    }

