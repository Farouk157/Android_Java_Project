package com.example.foodplanner.mealsbyingredient.presenter;

import com.example.foodplanner.mealofday.model.Meal;
import com.example.foodplanner.network.MealsRemoteDataSource;
import com.example.foodplanner.network.NetworkCallBack;
import com.example.foodplanner.mealsbyingredient.view.*;

import java.util.List;

public class MealsByIngredientPresenter {
    private final MealsByIngredientView view;
    private final MealsRemoteDataSource dataSource;

    public MealsByIngredientPresenter(MealsByIngredientView view, MealsRemoteDataSource dataSource) {
        this.view = view;
        this.dataSource = dataSource;
    }

    public void fetchMealsForIngredient(String ingredientId) {
        dataSource.fetchMealsByIngredient(ingredientId, new NetworkCallBack<List<Meal>>() {
            @Override
            public void onSuccess(List<Meal> meals) {
                if (meals != null) {
                    view.displayMealsByIngredient(meals);
                } else {
                    view.showError("No meals found for this ingredient.");
                }
            }

            @Override
            public void onFailure(String errMsg)
            {
                view.showError(errMsg);
            }
        });
    }
}
