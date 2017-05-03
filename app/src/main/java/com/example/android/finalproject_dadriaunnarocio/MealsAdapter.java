package com.example.android.finalproject_dadriaunnarocio;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by ccteuser on 4/24/17.
 */

public class MealsAdapter extends RecyclerView.Adapter<MealViewHolder> {

    private List<Meal> meals;
    private Context context;
    private boolean showCheckBox;

    public MealsAdapter(List<Meal> meals, Context context, boolean showCheckBox) {
        this.meals = meals;
        this.context = context;
        this.showCheckBox = showCheckBox;
    }

    @Override
    public MealViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_meal, parent, false);
        return new MealViewHolder(view, context, showCheckBox);
    }

    @Override
    public void onBindViewHolder(MealViewHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.bind(meal);
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

}
