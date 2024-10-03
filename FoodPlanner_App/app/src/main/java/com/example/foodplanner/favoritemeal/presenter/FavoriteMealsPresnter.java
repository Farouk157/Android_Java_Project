package com.example.foodplanner.favoritemeal.presenter;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.foodplanner.favoritemeal.view.*;
import com.example.foodplanner.mealofday.model.*;

import java.util.List;


public class FavoriteMealsPresnter {
    private FavoriteMealsView favoriteMealsView;
    private Repository repository;

    public FavoriteMealsPresnter(FavoriteMealsView favoriteMealsView, Repository repository) {
        this.favoriteMealsView = favoriteMealsView;
        this.repository = repository;
    }
    public void getFavMeals(LifecycleOwner lifecycleOwner){
        Observer<List<Meal>> observer = new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals){
                favoriteMealsView.displayMeals(meals);
            }
        };
        LiveData<List<Meal>> liveData =repository.getStoredMeals();
        liveData.observe(lifecycleOwner,observer);
    }
    public void removeMealFromFavorite(Meal meal)
    {
        repository.deleteMeal(meal);
    }
}
