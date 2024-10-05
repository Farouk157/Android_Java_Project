package com.example.foodplanner.calendar.presenter;

import android.content.Context;

import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.Plan;
import com.example.foodplanner.model.Repository;


public class MealTimePresenter {
    private final Context context;
    private Repository repository;

    public MealTimePresenter(Context context, Repository repository){
        this.context = context;
        this.repository = repository;
    }

    public void addMealToPlan(Meal meal, String date, String mealType){
        Plan plan = new Plan(meal, date, mealType);
        repository.insertPlan(plan);
    }

}
