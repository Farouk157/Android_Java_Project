package com.example.foodplanner.calendar.presenter;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.Repository;
import com.example.foodplanner.model.Plan;
import java.util.List;

public class RetreiveScheduledMealsPresenter {
    private final Context context;
    private Repository repository;

    public RetreiveScheduledMealsPresenter(Context context, Repository repository){
        this.context = context;
        this.repository = repository;
    }

    public LiveData<List<Plan>> getScheduledMeals(String type){
        LiveData<List<Plan>> meals;
        meals = repository.getPlansByType(type);
        return meals;
    }

}
