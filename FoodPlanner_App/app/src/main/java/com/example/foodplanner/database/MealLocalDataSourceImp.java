package com.example.foodplanner.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.mealofday.model.*;

import java.util.List;

public class MealLocalDataSourceImp implements MealLocalDataSource{

    private MealDAO mealDAO ;
    private static MealLocalDataSourceImp mealLocalDataSource= null ;
    private LiveData<List<Meal>> savedMeals;

    private MealLocalDataSourceImp(Context context) {
        AppDataBase appDataBase = AppDataBase.getInstance(context.getApplicationContext());
        mealDAO = appDataBase.mealDAO();
        savedMeals = mealDAO.getAllMeals();
    }
    public static MealLocalDataSourceImp getInstance(Context context)
    {
        if (mealLocalDataSource == null )
        {
            mealLocalDataSource = new MealLocalDataSourceImp(context);
        }
        return mealLocalDataSource;
    }


    @Override
    public void insertMeal(Meal meal) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                mealDAO.insertMeal(meal);
            }
        }.start();
    }

    @Override
    public void removeMeal(Meal meal) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                mealDAO.deleteMeal(meal);
            }
        }.start();
    }

    @Override
    public LiveData<List<Meal>> getAllMeals() {
        return savedMeals;
    }
}