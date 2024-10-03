package com.example.foodplanner.mealsbyingredient.view;

import com.example.foodplanner.mealofday.model.Meal;

import java.util.List;

public interface MealsByIngredientView {
    void displayMealsByIngredient(List<Meal> meals);
    void showError(String message);
}
