package com.example.foodplanner.mealdetails.presenter;

import com.example.foodplanner.model.Meal;
import com.example.foodplanner.mealdetails.view.MealDetailsView;
import com.example.foodplanner.model.Repository;

public class MealDetailsPresenter{

    private MealDetailsView view;
    private Repository repository;
    public MealDetailsPresenter(MealDetailsView view, Repository repository){
        this.view = view;
        this.repository = repository;
    }

    // Method to display meal details
    public void displayMealDetails(Meal meal) {
        if (meal != null) {
            view.showMealDetails(meal);
        } else {
            view.showError("Meal data is not available.");
        }
    }

    public void addMeal(Meal meal)
    {
        repository.insertMeal(meal);
    }


}
