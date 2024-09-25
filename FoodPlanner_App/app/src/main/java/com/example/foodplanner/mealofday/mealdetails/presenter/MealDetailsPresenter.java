package com.example.foodplanner.mealofday.mealdetails.presenter;

import com.example.foodplanner.mealofday.apphome.model.Meal;
import com.example.foodplanner.mealofday.mealdetails.view.MealDetailsView;

public class MealDetailsPresenter {

    private MealDetailsView view;

    public MealDetailsPresenter(MealDetailsView view) {
        this.view = view;
    }

    // Method to display meal details
    public void displayMealDetails(Meal meal) {
        if (meal != null) {
            view.showMealDetails(meal);
        } else {
            view.showError("Meal data is not available.");
        }
    }
}
