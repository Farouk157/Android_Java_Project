package com.example.foodplanner.mealsbycategory.presenter;

import com.example.foodplanner.mealsbycategory.view.MealsByCategoryView;
import com.example.foodplanner.mealofday.model.Meal;
import com.example.foodplanner.network.*;

import java.util.List;

public class MealsByCategoryPresenter {

    private final MealsByCategoryView view;
    private final MealsRemoteDataSource dataSource;

    public MealsByCategoryPresenter(MealsByCategoryView view, MealsRemoteDataSource dataSource) {
        this.view = view;
        this.dataSource = dataSource;
    }

    public void fetchMealsForCategory(String categoryId) {
        dataSource.fetchMealsByCategory(categoryId, new NetworkCallBack<List<Meal>>() {
            @Override
            public void onSuccess(List<Meal> meals) {
                if (meals != null) {
                    view.displayMealsByCategory(meals);
                } else {
                    view.showError("No meals found for this category.");
                }
            }

            @Override
            public void onFailure(String errMsg) {
                view.showError(errMsg);
            }
        });
    }
}

