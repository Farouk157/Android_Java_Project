package com.example.foodplanner.mealsbycountry.presenter;

import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.NetworkCallBack;
import com.example.foodplanner.mealsbycountry.view.*;

import java.util.List;

public class MealsByCountryPresenter implements NetworkCallBack<List<Meal>>{
    private final MealsByCountryView view;
    private final Repository repository;

    public MealsByCountryPresenter(MealsByCountryView view, Repository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void getMealsByCountry(String countryId){
        repository.fetchMealsForCountry(countryId, this);
    }

    @Override
    public void onSuccess(List<Meal> data) {
        if (data != null) {
            view.displayMealsByCountry(data);
        } else {
            view.showError("No meals found for this country.");
        }
    }

    @Override
    public void onFailure(String errorMessage) {
        view.showError(errorMessage);
    }
}
