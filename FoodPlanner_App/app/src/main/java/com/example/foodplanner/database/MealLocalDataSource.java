package com.example.foodplanner.database;
import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.Plan;
import java.util.List;

public interface MealLocalDataSource {
    void insertMeal (Meal meal);
    void removeMeal (Meal meal);
    LiveData<List<Meal>> getAllMeals ();
    void insertPlan(Plan plan);
    void deletePlan(Plan plan);
    LiveData<List<Plan>> getMealsByDate(String date);
    LiveData<List<Plan>> getScheduledMeals();
    LiveData<List<Plan>> getMealsByType(String type);
}
