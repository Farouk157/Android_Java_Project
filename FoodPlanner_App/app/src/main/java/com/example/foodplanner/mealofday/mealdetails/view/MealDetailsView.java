package com.example.foodplanner.mealofday.mealdetails.view;

import com.example.foodplanner.mealofday.apphome.model.Meal;

public interface MealDetailsView {
    void showMealDetails(Meal meal);
    void showError(String message);
}
