package com.example.android.finalproject_dadriaunnarocio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentProfileActivity extends AppCompatActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private DatabaseReference studentRef = database.getReference(auth.getCurrentUser().getUid() + "/profile");
    private FirebaseAuth.AuthStateListener authListener;
    private DatabaseReference postRef = database.getReference(auth.getCurrentUser().getUid() + "/post");

    private ImageView studentPhoto;
    private TextView studentName;
    private TextView studentAge;
    private TextView studentGrade;
    private TextView studentSchool;
    private TextView studentFavFood;
    private TextView isVegetarian;
    private ImageView newPostPhoto;
    private TextView newPostDescription;
    private RecyclerView studentPostRecyclerView;
    private PostAdapter postAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        postRef = database.getReference(auth.getCurrentUser().getUid() + "/post");


        studentPostRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_posts);
        studentPostRecyclerView.setHasFixedSize(true);
        studentPostRecyclerView.setLayoutManager(new LinearLayoutManager(StudentProfileActivity.this));
        postAdapter = new PostAdapter(postRef);
        studentPostRecyclerView.setAdapter(postAdapter);









        studentPhoto = (ImageView) findViewById(R.id.student_photo);
        studentName = (TextView) findViewById(R.id.student_name);
        studentAge = (TextView) findViewById(R.id.student_age);
        studentGrade = (TextView) findViewById(R.id.student_grade);
        studentSchool = (TextView) findViewById(R.id.school_name);
        studentFavFood = (TextView) findViewById(R.id.fav_food);
        isVegetarian = (TextView) findViewById(R.id.is_vegetarian);


        studentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Student student = dataSnapshot.getValue(Student.class);
                studentName.setText(student.getFullName());
                studentAge.setText(String.valueOf(student.getAge()) + " years old/ ");
                studentGrade.setText(String.valueOf("Grade " + student.getGrade()));
                studentSchool.setText(student.getSchool());
                studentFavFood.setText("Favorite food: " + student.getFavFood());
                if (student.isVegetarian()) {
                    isVegetarian.setText("Vegetarian: true");
                } else {
                    isVegetarian.setText("Vegetarian: false");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.log_out:
                auth.signOut();
                startActivity(new Intent(this, SplashActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    public void selectMeals(View view) {

        Intent intentMeals = new Intent(this, MealOptions.class);
        startActivity(intentMeals);

    }

    public void addNewPost(View view) {

        Intent intentPosts = new Intent(this, AddNewPost.class);
        startActivity(intentPosts);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        postAdapter.cleanup(); // Stop listening if the activity is destroyed
    }

}
