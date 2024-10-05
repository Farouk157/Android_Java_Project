package com.example.foodplanner.mealsbycategory.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.foodplanner.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.model.Meal;

import java.util.List;

public class MealsByCategoryAdapter extends RecyclerView.Adapter<MealsByCategoryAdapter.MealViewHolder> {

    private final List<Meal> meals;
    private final Context context;
    private final OnMealClickListener onMealClickListener;

    // Interface to handle meal click events
    public interface OnMealClickListener {
        void onMealClick(Meal meal);
    }

    public MealsByCategoryAdapter(List<Meal> meals, Context context, OnMealClickListener listener) {
        this.meals = meals;
        this.context = context;
        this.onMealClickListener = listener;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the meal item layout
        View view = LayoutInflater.from(context).inflate(R.layout.item_meal, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        // Bind meal data to the ViewHolder
        Meal meal = meals.get(position);
        holder.bind(meal);
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    // ViewHolder class for meals
    public class MealViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mealImageView;
        private final TextView mealNameTextView;

        public MealViewHolder(@NonNull View itemView) {
            super(itemView);

            mealImageView = itemView.findViewById(R.id.iv_fav_meal_image);
            mealNameTextView = itemView.findViewById(R.id.tv_fav_meal_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onMealClickListener.onMealClick(meals.get(position));
                    }
                }
            });
        }

        public void bind(Meal meal) {
            // Set meal name
            mealNameTextView.setText(meal.getStrMeal());

            // Load meal image using Glide
            Glide.with(context)
                    .load(meal.getStrMealThumb())
                    .into(mealImageView);
        }

    }
}
