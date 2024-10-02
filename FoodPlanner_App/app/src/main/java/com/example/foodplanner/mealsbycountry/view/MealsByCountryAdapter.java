package com.example.foodplanner.mealsbycountry.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.mealofday.model.Meal;
import com.example.foodplanner.mealsbycategory.view.MealsByCategoryAdapter;

import java.util.List;

public class MealsByCountryAdapter extends RecyclerView.Adapter<MealsByCountryAdapter.MealViewHolder> {

    private final List<Meal> meals;
    private final Context context;
    private final MealsByCountryAdapter.OnMealClickListener onMealClickListener;

    // Interface to handle meal click events
    public interface OnMealClickListener{
        void onMealClick(Meal meal);
    }

    public MealsByCountryAdapter(List<Meal> meals, Context context, MealsByCountryAdapter.OnMealClickListener listener) {
        this.meals = meals;
        this.context = context;
        this.onMealClickListener = listener;
    }

    @NonNull
    @Override
    public MealsByCountryAdapter.MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_meal, parent, false);
        return new MealsByCountryAdapter.MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealsByCountryAdapter.MealViewHolder holder, int position) {
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

            mealImageView = itemView.findViewById(R.id.iv_meal_image);
            mealNameTextView = itemView.findViewById(R.id.tv_meal_name);

            // Set click listener for the meal item
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

