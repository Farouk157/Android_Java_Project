package com.example.foodplanner.mealsbycategory.presenter;

import com.example.foodplanner.mealsbycategory.view.MealsByCategoryView;
import com.example.foodplanner.mealofday.model.Meal;
import com.example.foodplanner.network.*;

import java.util.List;
import com.example.foodplanner.mealofday.model.*;

public class MealsByCategoryPresenter implements NetworkCallBack<List<Meal>>{

    private final MealsByCategoryView view;
    private final Repository repository;

    public MealsByCategoryPresenter(MealsByCategoryView view, Repository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void getMealsByCategory(String categoryId){
        repository.fetchMealsForCategory(categoryId, this);
    }

    @Override
    public void onSuccess(List<Meal> data) {
        if (data != null) {
            view.displayMealsByCategory(data);
        } else {
            view.showError("No meals found for this category.");
        }
    }

    @Override
    public void onFailure(String errorMessage){
        view.showError(errorMessage);
    }


}

