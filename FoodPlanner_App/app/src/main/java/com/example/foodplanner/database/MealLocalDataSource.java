package com.example.foodplanner.database;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.mealofday.model.*;

import java.util.List;

public interface MealLocalDataSource {
    void insertMeal (Meal meal);
    void removeMeal (Meal meal);
    LiveData<List<Meal>> getAllMeals ();
}
