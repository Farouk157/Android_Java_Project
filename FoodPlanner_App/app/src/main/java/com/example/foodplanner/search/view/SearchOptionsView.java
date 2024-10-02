package com.example.foodplanner.search.view;

import com.example.foodplanner.mealofday.model.Meal;
import com.example.foodplanner.search.model.Category;
import java.util.List;

public interface SearchOptionsView {
    void displayCategories(List<Category> categories);
    void displayCountries(List<Meal> countries);
    void displayIngredients(List<Meal> ingredients);
    void showError(String message);
}
