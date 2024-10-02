package com.example.foodplanner.mealofday.view;

import com.example.foodplanner.mealofday.model.Meal;

import java.util.List;

public interface MealOfTheDayView {
    void displayMeals(List<Meal> meals);
    void showError(String message);
}
