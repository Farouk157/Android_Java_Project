package com.example.foodplanner.mealsbycategory.view;

import com.example.foodplanner.model.Meal;

import java.util.List;

public interface MealsByCategoryView {
    void displayMealsByCategory(List<Meal> meals);
    void showError(String message);
}
