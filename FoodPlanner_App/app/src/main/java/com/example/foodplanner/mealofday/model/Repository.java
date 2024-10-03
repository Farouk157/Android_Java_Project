package com.example.foodplanner.mealofday.model;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.network.*;
import com.example.foodplanner.database.*;
import com.example.foodplanner.search.model.Category;

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

}
