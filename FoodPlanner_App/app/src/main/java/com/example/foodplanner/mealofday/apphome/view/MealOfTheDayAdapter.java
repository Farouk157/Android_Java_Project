package com.example.foodplanner.mealofday.apphome.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.mealofday.apphome.model.Meal;
import com.bumptech.glide.Glide;

import java.util.List;

public class MealOfTheDayAdapter extends RecyclerView.Adapter<MealOfTheDayAdapter.MealViewHolder> {

    private List<Meal> meals;

    public MealOfTheDayAdapter(List<Meal> meals) {
        this.meals = meals;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meal, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.mealName.setText(meal.getStrMeal());
        Glide.with(holder.itemView.getContext())
                .load(meal.getStrMealThumb())
                .into(holder.mealImage);
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    static class MealViewHolder extends RecyclerView.ViewHolder {
        TextView mealName;
        ImageView mealImage;

        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            mealName = itemView.findViewById(R.id.tv_meal_name);
            mealImage = itemView.findViewById(R.id.iv_meal_image);
        }
    }
}