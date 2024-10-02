package com.example.foodplanner.mealsbycountry.presenter;

import com.example.foodplanner.mealofday.model.Meal;
import com.example.foodplanner.network.MealsRemoteDataSource;
import com.example.foodplanner.network.NetworkCallBack;
import com.example.foodplanner.mealsbycountry.view.*;

import java.util.List;

public class MealsByCountryPresenter {
    private final MealsByCountryView view;
    private final MealsRemoteDataSource dataSource;

    public MealsByCountryPresenter(MealsByCountryView view, MealsRemoteDataSource dataSource) {
        this.view = view;
        this.dataSource = dataSource;
    }

    public void fetchMealsForCountry(String countryId) {
        dataSource.fetchMealsByCountry(countryId, new NetworkCallBack<List<Meal>>() {
            @Override
            public void onSuccess(List<Meal> meals) {
                if (meals != null) {
                    view.displayMealsByCountry(meals);
                } else {
                    view.showError("No meals found for this country.");
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
