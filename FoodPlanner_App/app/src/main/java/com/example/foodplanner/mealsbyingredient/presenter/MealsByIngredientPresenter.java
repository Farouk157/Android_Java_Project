package com.example.foodplanner.mealsbyingredient.presenter;

import com.example.foodplanner.mealofday.model.Meal;
import com.example.foodplanner.network.MealsRemoteDataSource;
import com.example.foodplanner.network.NetworkCallBack;
import com.example.foodplanner.mealsbyingredient.view.*;
import com.example.foodplanner.mealsbycountry.view.*;
import com.example.foodplanner.mealofday.model.*;

import java.util.List;

public class MealsByIngredientPresenter implements NetworkCallBack<List<Meal>>{
    private final MealsByIngredientView view;
    private final Repository repository;

    public MealsByIngredientPresenter(MealsByIngredientView view,Repository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void getMealsByIngredient(String ingredientId){
        repository.fetchMealsForIngredient(ingredientId, this);
    }

    @Override
    public void onSuccess(List<Meal> data) {
        if (data != null) {
            view.displayMealsByIngredient(data);
        } else {
            view.showError("No meals found for this ingredient.");
        }
    }

    @Override
    public void onFailure(String errorMessage) {
        view.showError(errorMessage);
    }
}
