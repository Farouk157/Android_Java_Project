package com.example.foodplanner.mealdetails.view;

import com.example.foodplanner.mealofday.model.Meal;
import com.example.foodplanner.mealdetails.model.*;

import java.util.List;

public interface MealDetailsView {
    void showMealDetails(Meal meal);
    void showError(String message);
//    void showIngredientsDetails(List<Ingredient> ingredients); // Add this method
}
