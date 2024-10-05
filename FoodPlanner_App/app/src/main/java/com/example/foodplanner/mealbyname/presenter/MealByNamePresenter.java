package com.example.foodplanner.mealbyname.presenter;

import com.example.foodplanner.model.Meal;
import com.example.foodplanner.network.NetworkCallBack;
import com.example.foodplanner.model.*;
import com.example.foodplanner.mealbyname.view.*;
import java.util.List;

public class MealByNamePresenter implements NetworkCallBack<Meal>{

    private final Repository repository;
    private final MealByNameView view;

    public MealByNamePresenter(MealByNameView view, Repository repository) {
        this.repository = repository;
        this.view = view;
    }

    public void fetchMealByName(String mealName) {
        repository.fetchMealsByName(mealName, this);
    }

    @Override
    public void onSuccess(Meal data) {
        // Pass the fetched meal data to the view to update the UI
        view.displayMeal(data);
    }

    @Override
    public void onFailure(String errorMessage) {
        // Pass the error message to the view to display an error message
        view.showError(errorMessage);
    }
}
