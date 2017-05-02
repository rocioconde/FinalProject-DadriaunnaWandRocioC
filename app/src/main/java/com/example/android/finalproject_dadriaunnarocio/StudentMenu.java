package com.example.android.finalproject_dadriaunnarocio;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class StudentMenu extends AppCompatActivity {

    private RecyclerView studentMenuRecyclerView;
    private MealsAdapter mealsAdapterForStudentMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_menu);

        ArrayList<Meal> selectedMeals = (ArrayList<Meal>) getIntent().getSerializableExtra(Keys.SELECTED_MEALS);

        studentMenuRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_student_menu);
        studentMenuRecyclerView.setHasFixedSize(true);
        studentMenuRecyclerView.setLayoutManager(new LinearLayoutManager(StudentMenu.this));
        mealsAdapterForStudentMenu = new MealsAdapter(selectedMeals, StudentMenu.this);
        studentMenuRecyclerView.setAdapter(mealsAdapterForStudentMenu);


    }

    public void confirmMenu(View view) {

        Intent intent = new Intent(Intent.ACTION_SENDTO);

        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Your SchoolMenu");
        intent.putExtra(Intent.EXTRA_TEXT, "This is your menu:");

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }
}
