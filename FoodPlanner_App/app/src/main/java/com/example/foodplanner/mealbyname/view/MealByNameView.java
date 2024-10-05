package com.example.foodplanner.mealbyname.view;

import com.example.foodplanner.model.*;

public interface MealByNameView {
    void displayMeal(Meal meal);
    void showError(String errorMessage);
}
