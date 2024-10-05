package com.example.foodplanner.favoritemeal.view;

import com.example.foodplanner.model.Meal;

import java.util.List;

public interface FavoriteMealsView {
    void displayMeals(List<Meal> meals);
    void showError(String message);
}
