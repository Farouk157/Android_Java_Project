package com.example.foodplanner.searchoption.ingredients.view;

import java.util.List;

public interface IngredientsView {
    void displayIngredients(List<Ingredient> ingredients);
    void showError(String message);
}
