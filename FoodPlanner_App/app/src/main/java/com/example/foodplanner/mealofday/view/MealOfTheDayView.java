package com.example.foodplanner.mealofday.view;

import com.example.foodplanner.model.Meal;

import java.util.List;

public interface MealOfTheDayView {
    void displayMeals(List<Meal> meals);
    void showError(String message);
}
