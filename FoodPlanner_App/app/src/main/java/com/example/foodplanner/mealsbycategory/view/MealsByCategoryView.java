package com.example.foodplanner.mealsbycategory.view;

import com.example.foodplanner.mealofday.model.Meal;

import java.util.List;

public interface MealsByCategoryView {
    // Called by the presenter when meals are fetched successfully
    void displayMealsByCategory(List<Meal> meals);

    // Called by the presenter to show an error message
    void showError(String message);
}
