package com.example.android.finalproject_dadriaunnarocio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class MealOptions extends AppCompatActivity {

    private ArrayList<Meal> meals;
    private ArrayList<Meal> veggieMeals = new ArrayList<>();
    private MealsAdapter mealAdapter;
    private CheckBox mealCheckbox;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference studentRef = database.getReference("student");
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_options);

        studentRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                student = dataSnapshot.getValue(Student.class);

                initialData();
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_meals);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(MealOptions.this));

                mealCheckbox = (CheckBox) findViewById(R.id.checkbox);

                if (student.isVegetarian()) {
                    getVeggieMeals();
                    mealAdapter = new MealsAdapter(veggieMeals, MealOptions.this);

                } else {

                    mealAdapter = new MealsAdapter(meals, MealOptions.this);
                }
                recyclerView.setAdapter(mealAdapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    private void initialData() {
        meals = new ArrayList<>();
        meals.add(new Meal("Baked chicken fingers, white rice, and caesar salad", 5, 700, false, R.drawable.chickenfinger));
        meals.add(new Meal("Pork chops, mashed potatoes, and roasted carrots", 5, 750, false, R.drawable.porkchops));
        meals.add(new Meal("Red lentils stew, baked potatoes, and banana", 4.30, 560, true, R.drawable.redlentils));
        meals.add(new Meal("Veggie burger, corn on the cob, and fruit salad", 4.90, 690, true, R.drawable.veggieburger));
        meals.add(new Meal("Cheees pizza, caesar salad, and fruit salad", 4.80, 690, true, R.drawable.cheespizza));
        meals.add(new Meal("Tofu chicken nuggets, macaroni and cheese, and watermelon slices", 4.50, 600, true, R.drawable.tofu));
        meals.add(new Meal("Cheese quesadilla, Spanish rice, black beans, and pico de gallo", 4.80, 650, true, R.drawable.quesadilla));
        meals.add(new Meal("Pasta with meatballs and corn on the cob", 5, 680, false, R.drawable.meatballs));
        meals.add(new Meal("Cheeseburger, roasted potatoes, and apple", 5, 680, false, R.drawable.cheeseburger));
        meals.add(new Meal("Pepperoni pizza, caprese salad, and watermelon slices", 5, 700, false, R.drawable.pepperoni));


    }

    public void getVeggieMeals() {

        for (Meal veggieOption : meals)
            if (veggieOption.isVegetarian()) {
                veggieMeals.add(veggieOption);
            }

    }


    public void previewMenu(View view) {

        ArrayList<Meal> selectedMeals = new ArrayList<>();
        if (student.isVegetarian()) {
            for (Meal mealOption : veggieMeals) {


                if (mealOption.isSelected()) {
                    selectedMeals.add(mealOption);
                }
            }
        } else {
            for (Meal mealOption : meals) {
                if (mealOption.isSelected()) {
                    selectedMeals.add(mealOption);
                }
            }
        }


        Intent intentPreviewMenu = new Intent(MealOptions.this, StudentMenu.class);
        intentPreviewMenu.putExtra("selectedMeals", selectedMeals);
        startActivity(intentPreviewMenu);
    }
}
