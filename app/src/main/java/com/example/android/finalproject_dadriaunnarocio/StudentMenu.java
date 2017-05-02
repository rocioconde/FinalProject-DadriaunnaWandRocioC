package com.example.android.finalproject_dadriaunnarocio;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

public class StudentMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_menu);

        Intent intent = getIntent();
        ArrayList<Meal> selectedMeals = (ArrayList<Meal>) intent.getSerializableExtra("selectedMeals");
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
