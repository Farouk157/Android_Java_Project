package com.example.foodplanner.mealofday.apphome.view;

import com.example.foodplanner.mealofday.apphome.model.*;

import java.util.List;

public interface MealOfTheDayView {
    void displayMeals(List<Meal> meals);
    void showError(String message);
}
