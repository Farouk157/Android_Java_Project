package com.example.foodplanner.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.Plan;

import java.util.List;

public class MealLocalDataSourceImp implements MealLocalDataSource {

    private MealDAO mealDAO;
    private PlanDAO planDAO;
    private static MealLocalDataSourceImp mealLocalDataSource = null;
    private LiveData<List<Meal>> savedMeals;

    private MealLocalDataSourceImp(Context context) {
        AppDataBase appDataBase = AppDataBase.getInstance(context.getApplicationContext());
        mealDAO = appDataBase.mealDAO();
        planDAO = appDataBase.planDAO(); // PlanDAO initialization
        savedMeals = mealDAO.getAllMeals();
    }

    public static MealLocalDataSourceImp getInstance(Context context) {
        if (mealLocalDataSource == null) {
            mealLocalDataSource = new MealLocalDataSourceImp(context);
        }
        return mealLocalDataSource;
    }

    @Override
    public void insertMeal(Meal meal) {
        new Thread() {
            @Override
            public void run() {
                mealDAO.insertMeal(meal);
            }
        }.start();
    }

    @Override
    public void removeMeal(Meal meal) {
        new Thread() {
            @Override
            public void run() {
                mealDAO.deleteMeal(meal);
            }
        }.start();
    }

    @Override
    public void insertPlan(Plan plan){
        new Thread(){
            @Override
            public void run(){
                planDAO.insertPlan(plan);
            }
        }.start();
    }

    @Override
    public void deletePlan(Plan plan){
        new Thread(){
            @Override
            public void run(){
                planDAO.deletePlan(plan);
            }
        }.start();
    }

    @Override
    public LiveData<List<Meal>> getAllMeals() {
        return savedMeals;
    }

    @Override
    public LiveData<List<Plan>> getMealsByDate(String date) {
        return planDAO.getMealsByDate(date);
    }

    @Override
    public LiveData<List<Plan>> getScheduledMeals() {
        return planDAO.getScheduledMeals();
    }

    @Override
    public LiveData<List<Plan>> getMealsByType(String type) {
        return planDAO.getMealsByType(type);
    }
}
