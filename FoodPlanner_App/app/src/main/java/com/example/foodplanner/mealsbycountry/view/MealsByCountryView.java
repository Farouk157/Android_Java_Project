package com.example.foodplanner.mealsbycountry.view;

import com.example.foodplanner.mealofday.model.Meal;

import java.util.List;

public interface MealsByCountryView {
    void displayMealsByCountry(List<Meal> meals);
    void showError(String message);
}
