package com.example.foodplanner.mealofday.search.view;

import com.example.foodplanner.searchoption.categories.model.Category;
import com.example.foodplanner.mealofday.apphome.model.*;
import java.util.List;

public interface SearchOptionsView {
    void displayCategories(List<Category> categories);
    void displayCountries(List<Meal> countries);
    void displayIngredients(List<Meal> ingredients);
    void showError(String message);
}
