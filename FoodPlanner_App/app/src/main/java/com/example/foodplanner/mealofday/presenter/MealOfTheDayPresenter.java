package com.example.foodplanner.mealofday.presenter;

import com.example.foodplanner.mealofday.model.Meal;
import com.example.foodplanner.mealofday.view.MealOfTheDayView;
import com.example.foodplanner.network.*;
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
