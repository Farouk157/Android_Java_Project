package com.example.foodplanner.model;

import android.net.ConnectivityManager;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.network.*;
import com.example.foodplanner.database.*;

import java.util.List;

public class Repository {
    private MealsRemoteDataSource  remoteDataSource;
    private MealLocalDataSource mealLocalDataSource;
    private static Repository repo = null;


    private Repository(MealLocalDataSource mealLocalDataSource, MealsRemoteDataSource remoteDataSource){
        this.mealLocalDataSource = mealLocalDataSource;
        this.remoteDataSource= remoteDataSource;
    }

    public static Repository getInstance(MealLocalDataSource mealLocalDataSource,  MealsRemoteDataSource remoteDataSource){
        if(repo == null){
            repo = new Repository(mealLocalDataSource, remoteDataSource);
        }
        return repo;
    }

    public void fetchMeals(NetworkCallBack<List<Meal>> callback){
        remoteDataSource.fetchRandomMeal(callback);
    }

    public void fetchAllCategories(CategoryNetworkCallBack callback){
        remoteDataSource.fetchCategories(callback);
    }

    public void fetchCountries(NetworkCallBack<List<Meal>> callback){
        remoteDataSource.fetchCountries(callback);
    }

    public void fetchIngredients(NetworkCallBack<List<Meal>> callback){
        remoteDataSource.fetchIngredients(callback);
    }

    public void fetchMealsForCategory(String categoryId, NetworkCallBack<List<Meal>> callback) {
        remoteDataSource.fetchMealsByCategory(categoryId, callback);
    }

    public void fetchMealsForCountry(String countryId, NetworkCallBack<List<Meal>> callback) {
        remoteDataSource.fetchMealsByCountry(countryId, callback);
    }

    public void fetchMealsForIngredient(String ingredientId, NetworkCallBack<List<Meal>> callback) {
        remoteDataSource.fetchMealsByIngredient(ingredientId, callback);
    }

    public void fetchMealsByName(String mealName,NetworkCallBack<Meal> callback){
        remoteDataSource.fetchMealByName(mealName, callback);
    }

    public void insertMeal (Meal meal)
    {
        mealLocalDataSource.insertMeal(meal);
    }
    public void deleteMeal (Meal meal)
    {
        mealLocalDataSource.removeMeal(meal);
    }
    public LiveData<List<Meal>> getStoredMeals (){
        return mealLocalDataSource.getAllMeals();
    }

    public void insertPlan(Plan plan){
        mealLocalDataSource.insertPlan(plan);
    }

    public  void deletePlan(Plan plan){
        mealLocalDataSource.deletePlan(plan);
    }

    public LiveData<List<Plan>> getMealsByDate(String date) {
        return mealLocalDataSource.getMealsByDate(date);
    }

    public LiveData<List<Plan>> getScheduledMeals() {
        return mealLocalDataSource.getScheduledMeals();
    }

    public LiveData<List<Plan>> getPlansByType(String type) {
        return mealLocalDataSource.getMealsByType(type);
    }

}
