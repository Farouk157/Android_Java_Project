package com.example.foodplanner.mealdetails.view;

import com.example.foodplanner.model.Meal;

public interface MealDetailsView {
    void showMealDetails(Meal meal);
    void showError(String message);
}
