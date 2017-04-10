package com.example.android.finalproject_dadriaunnarocio;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference userRef = database.getReference("user");
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseAuth.AuthStateListener authListener;

    private EditText username;
    private EditText userAge;
    private EditText favFood;
    private TextView displayText;
    private ArrayList<String> newUser = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        userAge = (EditText) findViewById(R.id.user_age);
        favFood = (EditText) findViewById(R.id.favorite_food);
        displayText = (TextView) findViewById(R.id.display_text);


        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null)
                    startActivity(new Intent(MainActivity.this, LogInActivity.class));
                else {
                    userRef = database.getReference(user.getUid());
                    userRef.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            newUser.add(dataSnapshot.getValue(String.class));
                            displayUserInfo();

                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                            Toast.makeText(MainActivity.this, dataSnapshot.getValue(MainActivity.class) + " has changed", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {
                            Toast.makeText(MainActivity.this, dataSnapshot.getValue(MainActivity.class) + " was removed", Toast.LENGTH_SHORT).show();
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


        };

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

    public void sendSomething(View view) {

        FirebaseUser user = auth.getCurrentUser();
        DatabaseReference userRef = database.getReference(user.getUid());
        userRef.push().setValue(username.getText().toString());
        userRef.push().setValue(userAge.getText().toString());
        userRef.push().setValue(favFood.getText().toString());
    }

    private void displayUserInfo() {
        String text = "";
        for (String s : newUser)
            text += s + "\n";
        displayText.setText(text);

    }

    public void signOut(View view) {
        auth.signOut();
        newUser.clear();
        displayText.setText("");


    }

    }

