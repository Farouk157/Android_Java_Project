package com.example.foodplanner.mealofday.apphome.presenter;

import com.example.foodplanner.mealofday.apphome.view.*;
import com.example.foodplanner.network.*;
import com.example.foodplanner.mealofday.apphome.model.Meal;

import java.util.List;

public class MealOfTheDayPresenter {
    private final MealOfTheDayView view;
    private final MealsRemoteDataSource dataSource;

    public MealOfTheDayPresenter(MealOfTheDayView view, MealsRemoteDataSource dataSource) {
        this.view = view;
        this.dataSource = dataSource;
    }

    public void fetchMeals() {
        dataSource.fetchRandomMeal(new NetworkCallBack<List<Meal>>() {
            @Override
            public void onSuccess(List<Meal> meals) {
                view.displayMeals(meals);
            }

            @Override
            public void onFailure(String errorMessage) {
                view.showError(errorMessage);
            }
        });
    }
}
