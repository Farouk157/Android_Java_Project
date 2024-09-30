package com.example.foodplanner.mealofday.mealdetails.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.mealofday.apphome.model.Meal;

public class MealDetailsFragment extends Fragment {

    private TextView tvMealName, tvOriginCountry, tvInstructions;
    private ImageView ivMealImage;

    public static MealDetailsFragment newInstance(Meal meal) {
        MealDetailsFragment fragment = new MealDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable("meal", meal);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal_details, container, false);

        // Initialize views
        tvMealName = view.findViewById(R.id.tv_meal_name);
        ivMealImage = view.findViewById(R.id.iv_meal_image);
        tvOriginCountry = view.findViewById(R.id.tv_origin_country);
        tvInstructions = view.findViewById(R.id.tv_instructions);

        // Retrieve the meal object from arguments
        if (getArguments() != null) {
            Meal meal = (Meal) getArguments().getSerializable("meal");
            if (meal != null) {
                displayMealDetails(meal);
            } else {
                // Handle case where meal is null
                tvMealName.setText(R.string.meal_not_found);
            }
        }

        return view;
    }

    private void displayMealDetails(Meal meal) {
        // Populate UI with meal details
        tvMealName.setText(meal.getStrMeal() != null ? meal.getStrMeal() : "N/A");
        tvOriginCountry.setText(meal.getStrArea() != null ? meal.getStrArea() : "Unknown");
        tvInstructions.setText(meal.getStrInstructions() != null ? meal.getStrInstructions() : "No instructions available");

        // Load image using Glide, handling case if URL is null or empty
        String imageUrl = meal.getStrMealThumb();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(requireContext())
                    .load(imageUrl)
                    .into(ivMealImage);
        } else {
            ivMealImage.setImageResource(R.drawable.placeholder); // Use a placeholder if image is missing
        }
    }
}
