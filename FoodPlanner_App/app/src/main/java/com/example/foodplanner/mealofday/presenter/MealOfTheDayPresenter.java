package com.example.foodplanner.mealofday.presenter;

import android.net.ConnectivityManager;

import com.example.foodplanner.mealofday.model.Meal;
import com.example.foodplanner.mealofday.view.MealOfTheDayView;
import com.example.foodplanner.network.*;
import java.util.List;
import com.example.foodplanner.mealofday.model.*;

public class MealOfTheDayPresenter implements NetworkCallBack<List<Meal>> {
    private final MealOfTheDayView view;
    private Repository repository;

    public MealOfTheDayPresenter(MealOfTheDayView view, Repository repository) {
        this.view = view;
        this.repository = repository;
    }


    public void getMeals (){
        repository.fetchMeals(this);
    }

    @Override
    public void onSuccess(List<Meal> meals) {
        view.displayMeals(meals);
    }

    @Override
    public void onFailure(String errorMessage) {
        view.showError(errorMessage);
    }

//    public  void addToFav (Meal meal)
//    {
//        repository.insertMeal(meal);
//    }
}
